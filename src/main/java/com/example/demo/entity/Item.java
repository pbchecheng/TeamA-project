package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_food")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_id")
	private Integer categoryId;
	private String categoryname;
	private String foodname;
	private Integer quantity;
	private String memo;
	private LocalDate timelimit;

	public Item() {

	}

	public Item(String foodname, int categoryId, int quantity, String memo, LocalDate timelimit) {
		this.foodname = foodname;
		this.categoryId = categoryId;
		this.quantity = quantity;
		this.memo = memo;
		this.timelimit = timelimit;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDate getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(LocalDate timelimit) {
		this.timelimit = timelimit;
	}

}
