package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Count;
import com.example.demo.entity.Food;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CountRepository;
import com.example.demo.repository.FoodRepository;

@Controller
@RequestMapping("/shokumane")
public class ItemController {

	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CountRepository countRepository;

	@Autowired
	FoodRepository foodRepository;

	@GetMapping("/items")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "asc", defaultValue = "false") boolean asc,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<Food> foodList = null;

		if (categoryId == null) {

			foodList = foodRepository.findByUserIdOrderById(account.getId());

		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			foodList = foodRepository.findByUserIdAndCategoryId(account.getId(), categoryId);

		}

		if (asc) {
			foodList.sort(Comparator.comparing(Food::getTimeLimit));
		}

		List<Food> foodList1 = new ArrayList<>();
		List<Food> foodList2 = new ArrayList<>();

		for (Food food : foodList) {
			if (food.getPlaceId() == 1) {
				foodList1.add(food);
			} else {
				foodList2.add(food);
			}

		}
		model.addAttribute("categoryId",categoryId);
		model.addAttribute("foods1", foodList1);
		model.addAttribute("foods2", foodList2);
		model.addAttribute("asc", asc);
		return "items";
	}

	@GetMapping("/add")
	public String add(
			@RequestParam(value = "action", required = false, defaultValue = "0") String action,
			Model model) {
		String place = "";
		if ("1".equals(action)) {
			place = "冷蔵庫";
		} else if ("2".equals(action)) {
			place = "冷凍庫";
		}
		model.addAttribute("place", place);

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<Count> countList = countRepository.findAll();
		model.addAttribute("counts", countList);

		return "add";
	}

	@PostMapping("/add")
	public String register(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "foodName", defaultValue = "") String foodName,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			@RequestParam(name = "countId", defaultValue = "") Integer countId,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit,
			@RequestParam(name = "place", defaultValue = "") String place,
			Model model

	) {
		List<String> errorList = new ArrayList<>();
		if (foodName.length() == 0) {
			errorList.add("食品名は必須です");

		}

		if (quantity == null) {
			errorList.add("数量は必須です");

		}

		if (errorList.size() > 0) {
			model.addAttribute("error", errorList);
			List<Category> categoryList = categoryRepository.findAll();
			model.addAttribute("categories", categoryList);
			List<Count> countList = countRepository.findAll();
			model.addAttribute("counts", countList);
			model.addAttribute("place", place);
			return "add";

		}

		Food food = new Food(foodName, categoryId, quantity, countId, memo, timeLimit);
		food.setUserId(account.getId());
		System.out.println(place);
		if (place.equals("冷凍庫")) {
			food.setPlaceId(2);
		} else {
			food.setPlaceId(1);
		}

		food.setUserId(account.getId());
		foodRepository.save(food);

		return "redirect:/shokumane/items";

	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		List<Count> countList = countRepository.findAll();
		model.addAttribute("counts", countList);

		Food food = foodRepository.findById(id).get();

		model.addAttribute("food", food);

		return "edit";
	}

	@PostMapping("/edit")
	public String update(
			@RequestParam(name = "id", defaultValue = "") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "foodName", defaultValue = "") String foodName,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			@RequestParam(name = "countId", defaultValue = "") Integer countId,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit

	) {

		Food food = foodRepository.findById(id).get();
		food.setFoodName(foodName);
		food.setCategoryId(categoryId);
		food.setQuantity(quantity);
		food.setCountId(countId);
		food.setMemo(memo);
		food.setTimeLimit(timeLimit);
		foodRepository.save(food);

		return "redirect:/shokumane/items";
	}

	@GetMapping("/{id}/consume")
	public String delete(@PathVariable("id") Integer id) {
		foodRepository.deleteById(id);
		return "redirect:/shokumane/items";
	}

}
