package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shokumane")
public class ItemController {

	@GetMapping("/items")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		return "items";
	}

	@GetMapping("/add")
	public String add() {
		return "add";
	}

	@PostMapping("/add")
	public String register(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "foodName", defaultValue = "") String foodName,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit

	) {

		// if all are set
		//		return "redirect:/shokumane/items";

		return "add";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id,
			Model model) {
		return "edit";
	}

	@PostMapping("/edit")
	public String update(
			@RequestParam(name = "id", defaultValue = "") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "foodName", defaultValue = "") String foodName,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit

	) {

		return "redirect:/shokumane/items";
	}

	@GetMapping("/{id}/consume")
	public String delete(@PathVariable("id") Integer id) {
		return "redirect:/shokumane/items";
	}

}
