package com.qsp.onlineHotelBooking.dto;

import java.sql.SQLType;
import java.util.HashMap;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	private String customerName;
	@Column(unique = true)
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long customerPhone;
	@Column(unique = true)
	@NotNull(message = "Email can't be null")
	@NotBlank(message = "Email can't be blank")
	@Email(regexp = "[a-z0-9._+$]+@[a-z]+\\.[a-z]{2,3}", message = "Please enter valid email")
	private String customerEmail;
	@NotNull(message = "Password can't be null")
	@NotBlank(message = "Password can't be blank")
	private String customerPassword;
	@NotNull(message = "Address can't be null")
	@NotBlank(message = "Address can't be blank")
	private String address;

	@Min(value = 100000000000l)
	@Max(value = 999999999999l)
	private long customerAadhar;
	@JdbcTypeCode(SqlTypes.JSON)
	private HashMap<Integer, Integer> bookedRoom;
	

}
