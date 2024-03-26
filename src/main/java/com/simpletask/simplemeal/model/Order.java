package com.simpletask.simplemeal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "order_item")
	@JsonIgnore
	@OneToMany(targetEntity = OrderItem.class, mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;

	@Column(name = "date")
	private Date date;

	@Column(name = "total_price")
	private double totalPrice;

	public Order() {
	}

	public Order(int id, List<OrderItem> orderItems, Date date, double totalPrice) {
		this.id = id;
		this.orderItems = orderItems;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public Order(List<OrderItem> orderItems, Date date, double totalPrice) {
		this.orderItems = orderItems;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
