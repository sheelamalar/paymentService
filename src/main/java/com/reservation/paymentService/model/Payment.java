package com.reservation.paymentService.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {

	@Id
	@Column(name="payment_number")
	private int paymentNumber;
	
	@Column(name="booking_number ")
	private int bookingNumber;
	
	@Column(name="payment_date ")
	private Date paymentDate;
	
	public int getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public int getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
