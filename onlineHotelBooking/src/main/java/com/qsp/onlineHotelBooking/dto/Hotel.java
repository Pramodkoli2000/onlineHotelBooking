package com.qsp.onlineHotelBooking.dto;

import java.util.HashMap;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hotelId;
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	private String hotelName;
	@NotNull(message = "Location can't be null")
	@NotBlank(message = "Location can't be blank")
	private String hotelLocation;
	@Column(unique = true)
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long hotelPhone;
	@Column(unique = true)
	@NotNull(message = "Email can't be null")
	@NotBlank(message = "Email can't be blank")
	@Email(regexp = "[a-z0-9._+$]+@[a-z]+\\.[a-z]{2,3}", message = "Please enter valid email")
	private String hotelEmail;
	@NotNull(message = "Password can't be null")
	@NotBlank(message = "Password can't be blank")
	private String hotelPassword;

	private int totalRooms;
	private int availRoom;

	private double roomPrice;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Room> rooms;
		
	@JdbcTypeCode(SqlTypes.JSON)
	private HashMap<Integer, List<String>> dates;

}
