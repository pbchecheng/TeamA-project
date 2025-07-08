package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.example.demo.entity.Food;
import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.ItemRepository;

@Controller
@RequestMapping("/shokumane")
public class ItemController {

	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	FoodRepository foodRepository;

	@GetMapping("/items")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<Item> itemList = null;

		if (categoryId == null) {

			itemList = itemRepository.findByUserIdOrderById(account.getId());

		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByUserIdAndCategoryId(account.getId(), categoryId);
		}
		model.addAttribute("items", itemList);
		return "items";
	}

	@GetMapping("/add")
	public String add(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		return "add";
	}

	@PostMapping("/add")
	public String register(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "foodName", defaultValue = "") String foodName,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit,
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
			return "add";

		}

		Food food = new Food(foodName, categoryId, quantity, memo, timeLimit);
		food.setUserId(account.getId());
		foodRepository.save(food);

		return "redirect:/shokumane/items";

	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);

		return "edit";
	}

	@PostMapping("/edit")
	public String update(
			@RequestParam(name = "id", defaultValue = "") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "foodName", defaultValue = "") String foodName,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit

	) {

		Food food = foodRepository.findById(id).get();
		food.setFoodname(foodName);
		food.setCategoryId(categoryId);
		food.setQuantity(quantity);
		food.setMemo(memo);
		food.setTimelimit(timeLimit);
		foodRepository.save(food);

		return "redirect:/shokumane/items";
	}

	@GetMapping("/{id}/consume")
	public String delete(@PathVariable("id") Integer id) {
		foodRepository.deleteById(id);
		return "redirect:/shokumane/items";
	}

}
