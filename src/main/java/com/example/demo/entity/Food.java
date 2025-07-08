package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String foodname;
	@Column(name = "category_id")
	private Integer categoryId;
	private Integer quantity;
	private String memo;
	private LocalDate timelimit;

	public Food() {

	}

	public Food(String foodname, int categoryId, int quantity, String memo, LocalDate timelimit) {
		this.foodname = foodname;
		this.categoryId = categoryId;
		this.quantity = quantity;
		this.memo = memo;
		this.timelimit = timelimit;
	}

	public Integer getId() {
		return id;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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