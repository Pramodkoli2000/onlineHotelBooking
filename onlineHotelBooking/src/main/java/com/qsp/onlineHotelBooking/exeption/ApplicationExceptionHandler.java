package com.qsp.onlineHotelBooking.exeption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.onlineHotelBooking.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(CustomerIdNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Customer with given id not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(HotelIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(HotelIdNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Hotel with given id not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(HotelEmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(HotelEmailNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Hotel with given email not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(HotelDataNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(HotelDataNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Customer with given data  not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(RoomNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Room with given data  not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CustomerPhoneNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePhoneNotFoundException(CustomerPhoneNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Customer with given phone not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CustomerNoDataAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(CustomerNoDataAvailableException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Customer with given data  not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CustomerEmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(CustomerEmailNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Customer with given email not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> objectErrors = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		for (ObjectError objectError : objectErrors) {
			FieldError error = (FieldError) objectError;
			String fieldName = error.getField();
			String message = error.getDefaultMessage();

			map.put(fieldName, message);

		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}
