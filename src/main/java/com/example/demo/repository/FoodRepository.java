package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

	List<Food> findByUserIdOrderById(int id);

	List<Food> findByUserIdAndCategoryId(int id, Integer categoryId);

	List<Food> findByUserIdOrderByTimeLimitAsc(int id);

	List<Food> findByUserIdAndCategoryIdOrderByTimeLimitAsc(int id, Integer categoryId);

	List<Food> findByUserIdOrderByIdDesc(int id);

	List<Food> findByUserIdAndCategoryIdOrderByIdDesc(int id, Integer categoryId);

}
