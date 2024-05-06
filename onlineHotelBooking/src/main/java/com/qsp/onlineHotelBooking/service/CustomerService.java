package com.qsp.onlineHotelBooking.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.onlineHotelBooking.dao.CustomerDAO;
import com.qsp.onlineHotelBooking.dao.HotelDAO;
import com.qsp.onlineHotelBooking.dto.Customer;
import com.qsp.onlineHotelBooking.dto.Hotel;
import com.qsp.onlineHotelBooking.exeption.CustomerEmailNotFoundException;
import com.qsp.onlineHotelBooking.exeption.CustomerIdNotFoundException;
import com.qsp.onlineHotelBooking.exeption.CustomerNoDataAvailableException;
import com.qsp.onlineHotelBooking.exeption.CustomerPhoneNotFoundException;
import com.qsp.onlineHotelBooking.exeption.HotelDataNotFoundException;
import com.qsp.onlineHotelBooking.exeption.HotelIdNotFoundException;
import com.qsp.onlineHotelBooking.exeption.RoomNotFoundException;
import com.qsp.onlineHotelBooking.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO dao;

	@Autowired
	private HotelDAO daoHotel;

	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {

		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		structure.setMassage("Save Customer Success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveCustomer(customer));

		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> loginCustomer(String email, String password) {
		Customer dbCustomer = dao.loginCustomer(email, password);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (dbCustomer != null) {
			if (dbCustomer.getCustomerPassword().equals(password)) {

				structure.setMassage("Login Success!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbCustomer);
				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
			} else { // InvalidPassword
				structure.setMassage("Login Failed! Incorrect password.");
				structure.setStatus(HttpStatus.UNAUTHORIZED.value());
				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.UNAUTHORIZED);
			}
		}
		throw new CustomerEmailNotFoundException("Customer with the given email " + email + " not found");
	}

	public ResponseEntity<ResponseStructure<Customer>> bookRoom(int hotelId, int customerId, int noOfRoom) {
		Optional<Hotel> opH = dao.bookRoom(hotelId, noOfRoom);
		Optional<Customer> opC = dao.findById(customerId);

		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();

		if (opH.isPresent() && opC.isPresent()) {
			Hotel hotel = opH.get();
			Customer customer = opC.get();
			int availRooms = hotel.getAvailRoom();
			if (availRooms >= noOfRoom) {
				hotel.setAvailRoom(availRooms - noOfRoom);
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				map.put(hotelId, noOfRoom);
				customer.setBookedRoom(map);
				daoHotel.saveHotel(hotel);

				structure.setMassage("Room Booked Successfully!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveCustomer(customer));

				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
			} else {

				structure.setMassage(noOfRoom + " rooms not available!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveCustomer(customer));
			}
		} else {
			throw new CustomerIdNotFoundException("Customer with the given Id " + customerId + " not found");
		}
		throw new CustomerNoDataAvailableException("Customer with the given Data is  not found");
	}

	public ResponseEntity<ResponseStructure<Customer>> cancelRoom(int hotelId, int customerId, int noOfRoom) {
		Optional<Customer> opC = dao.cancelRoom(hotelId, customerId, noOfRoom);
		Optional<Hotel> opH = daoHotel.findById(hotelId);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (opH.isPresent()) {
			if (opC.isPresent()) {

				Customer customer = opC.get();
				Hotel hotel = opH.get();
				HashMap<Integer, Integer> map = customer.getBookedRoom();
				Integer dbNoOfRoom = map.get(hotelId);
				if (dbNoOfRoom != null) {
					if (dbNoOfRoom == noOfRoom) {
						map.remove(hotelId);
						hotel.setAvailRoom(hotel.getAvailRoom() + noOfRoom);
						daoHotel.saveHotel(hotel);

						structure.setMassage("Room Cancelled Successfully 1!");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(dao.saveCustomer(customer));

						return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);

					} else if (dbNoOfRoom > noOfRoom) {
						map.replace(hotelId, (dbNoOfRoom - noOfRoom));
						hotel.setAvailRoom(hotel.getAvailRoom() + noOfRoom);
						daoHotel.saveHotel(hotel);

						structure.setMassage("Room Cancelled Successfully!");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(dao.saveCustomer(customer));

						return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);

					} else {

						System.out.println("Number of cancelation rooms is exceeding number of booked rooms!");
					}

				} else {
					throw new RoomNotFoundException(
							"You haven't booked any room in the hotel with given id:" + hotelId);
				}
			} else {
				throw new CustomerIdNotFoundException("Customer with the given Id " + customerId + " not found");
			}
		}
		throw new HotelIdNotFoundException("Hotel with the given HotelId " + hotelId + " not found");
	}

	public ResponseEntity<ResponseStructure<Customer>> updatePhone(int customerId, long oldPhone, long newPhone) {
		Optional<Customer> op = dao.updatePhone(customerId, oldPhone, newPhone);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (op.isPresent()) {
			Customer customer = op.get();
			long dbPhone = customer.getCustomerPhone();
			if (dbPhone == oldPhone) {
				customer.setCustomerPhone(newPhone);
				structure.setMassage("Update Phone Successfully!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveCustomer(customer));
				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
			} else {

				throw new CustomerPhoneNotFoundException("Customer with give " + oldPhone + "not registered");
			}

		} else {
			throw new CustomerIdNotFoundException("Customer with given" + customerId + "not found");
		}
	}

	public ResponseEntity<ResponseStructure<Customer>> updateAddress(int customerId, String newAddress) {
		Optional<Customer> op = dao.updateAddress(customerId, newAddress);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (op.isPresent()) {
			Customer customer = op.get();
			customer.setAddress(newAddress);
			structure.setMassage("Update Address Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveCustomer(customer));
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		} else {
			throw new CustomerIdNotFoundException("Customer with given id" + customerId + "not found");
		}
	}

	public ResponseEntity<ResponseStructure<Customer>> updatePassword(int customerId, String oldPass, String newPass) {
		Optional<Customer> op = dao.updatePassword(customerId, oldPass, newPass);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (op.isPresent()) {
			Customer customer = op.get();
			String dbPass = customer.getCustomerPassword();
			if (dbPass.equals(oldPass)) {
				customer.setCustomerPassword(newPass);
				structure.setMassage("Update Password Successfully!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveCustomer(customer));
				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);

			} else {
				throw new CustomerNoDataAvailableException("Customer old password is Incorrect!");
			}
		} else {
			throw new CustomerIdNotFoundException("Customer with given id" + customerId + "not found");
		}
	}

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int customerId) {
		Customer customer = dao.deleteCustomer(customerId);
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		if (customer != null) {
			structure.setMassage(" Customer Deleted"
					+ " Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customer);
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		} else {
			throw new CustomerIdNotFoundException("Customer with given id" + customerId + "not found");
		}
	}

//	public Customer updateAadhar(int customerId, long newAadhar) {
//		Optional<Customer> op = dao.updateAadhar(customerId, newAadhar);
//		if (op.isPresent()) {
//			Customer customer = op.get();
//			customer.setCustomerAadhar(newAadhar);
//			dao.saveCustomer(customer);
//			return customer;
//		} else {
//			System.out.println("Enter The Correct Customer Id!");
//			return null;
//		}
//	}
	
	
	
	public void bookRoom(int hotelId, int customerId, int noOfRoom,int roomId, int fromDate, int fromMonth, int fromYear, int toDate,
			int toMonth, int toYear) {
		Optional<Hotel> opH = dao.bookRoom(hotelId, noOfRoom);
		Optional<Customer> opC = dao.findById(customerId);
		System.out.println(fromMonth);//to check
		if (opH.isPresent() && opC.isPresent()) {
			Hotel dbHotel = opH.get();
			Calendar fromCalender=Calendar.getInstance();
			fromCalender.set(fromYear, fromMonth, fromDate);
			Date from = fromCalender.getTime();
			SimpleDateFormat dateFormatFrom=new SimpleDateFormat("dd-MM-yyyy");
			String formattedDateFrom=dateFormatFrom.format(from);
			
	        
	        Calendar toCalender=Calendar.getInstance();
			toCalender.set(toYear, toMonth, toDate);
			Date to = toCalender.getTime();
			SimpleDateFormat dateFormatTo=new SimpleDateFormat("dd-MM-yyyy");
			String formattedDateTo=dateFormatTo.format(to);
	        System.out.println(fromMonth);
	        ArrayList<String> dt = new ArrayList<String>();
	        int f = from.getDate();
			int t = to.getDate();
			
			Calendar c3 = Calendar.getInstance(); 
			for (int i = f; i <= t; i++) {
				System.out.println(fromMonth);
				c3.set(Calendar.DATE, i);
				c3.set(Calendar.MONTH, fromMonth);  
		         
		        c3.set(Calendar.YEAR, fromYear);
		        
		        Date date3 = c3.getTime();
		        System.out.println(date3);
		        SimpleDateFormat dateFormat3=new SimpleDateFormat("dd-MM-yyyy");
				String formattedDate3=dateFormat3.format(date3);
		        System.out.println(formattedDate3);
		        dt.add(formattedDate3);
			}
			HashMap<Integer, List<String>> dbdates=dbHotel.getDates();
			if (dbdates!=null) {
				List<String> updateDbDates= dbdates.get(roomId);
				// to check if room is already booked on same date
				List<String> compare=new ArrayList<String>(updateDbDates);
				compare.retainAll(dt);
				System.out.println(updateDbDates);
				System.out.println(dt);
				System.out.println(compare);
				if (compare.size()==0) {
					updateDbDates.addAll(dt);
					dbdates.put(roomId, updateDbDates);
					dbHotel.setDates(dbdates);
					daoHotel.updateRoomPrice(dbHotel);
					Customer customer = opC.get();
						HashMap<Integer, Integer> dbmap=customer.getBookedRoom(); 
						if (dbmap!=null) {
							dbmap.put(hotelId, noOfRoom);
							customer.setBookedRoom(dbmap);
							
						} else {
							HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
							map.put(hotelId, noOfRoom);
							customer.setBookedRoom(map);
							
						}
						daoHotel.saveHotel(dbHotel);
						dao.saveCustomer(customer);
						System.out.println("Room Booked Successfully!");
				} else {
					System.out.println("Room is already booked on the given dates");
				}
			} else {
				HashMap<Integer, List<String>> dates= new HashMap<Integer, List<String>>();
				dates.put(roomId, dt);
				dbHotel.setDates(dates);
				daoHotel.saveHotel(dbHotel);
			}
			
		} else {
			System.out.println("Please Enter Corrent Id!");
		}
	}

}
