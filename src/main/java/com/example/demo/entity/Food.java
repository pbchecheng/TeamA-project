package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "foodname")
	private String foodName;
	@Column(name = "category_id")
	private Integer categoryId;
	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Category category;
	private Integer quantity;
	@Column(name = "count_id")
	private Integer countId;
	@OneToOne
	@JoinColumn(name = "count_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Count count;
	private String memo;
	@Column(name = "timelimit")
	private LocalDate timeLimit;
	@Column(name = "place_id")
	private Integer placeId;
	@Column(name = "user_id")
	private Integer userId;

	public Food() {

	}

	public Food(String foodName, int categoryId, int quantity, int countId, String memo, LocalDate timeLimit) {
		this.foodName = foodName;
		this.categoryId = categoryId;
		this.quantity = quantity;
		this.countId = countId;
		this.memo = memo;
		this.timeLimit = timeLimit;
	}

	public Integer getId() {
		return id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return category.getName();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCountId() {
		return countId;
	}

	public void setCountId(Integer countId) {
		this.countId = countId;
	}

	public String getCountName() {
		return count.getName();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDate getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(LocalDate timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}