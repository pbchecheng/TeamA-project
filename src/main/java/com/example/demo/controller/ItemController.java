package com.example.demo.controller;

import java.math.BigInteger;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	public static class MyForm {
		private boolean showText;

		public boolean isShowText() {
			return showText;
		}

		public void setShowText(boolean showText) {
			this.showText = showText;
		}
	}

	@GetMapping("/items")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "asc", defaultValue = "false") boolean asc,
			@RequestParam(name = "show", defaultValue = "false") boolean show,
			@RequestParam(name = "showText", required = false, defaultValue = "false") boolean showText,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<Food> foodList = null;

		if (categoryId == null) {

			foodList = foodRepository.findByUserIdOrderByIdDesc(account.getId());

		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			foodList = foodRepository.findByUserIdAndCategoryIdOrderByIdDesc(account.getId(), categoryId);

		}

		if (asc) {
			foodList.sort(Comparator.comparing(Food::getTimeLimit,
					Comparator.nullsLast(Comparator.naturalOrder())));
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
		MyForm form = new MyForm();
		form.setShowText(showText);
		model.addAttribute("show", show);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("foods1", foodList1);
		model.addAttribute("foods2", foodList2);
		model.addAttribute("asc", asc);
		model.addAttribute("form", form);
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
			@RequestParam(name = "quantity", defaultValue = "", required = false) BigInteger quantity,
			@RequestParam(name = "countId", defaultValue = "") Integer countId,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit,
			@RequestParam(name = "place", defaultValue = "") String place,
			RedirectAttributes redirectAttrs,
			Model model

	) {
		List<String> errorList = new ArrayList<>();
		if (foodName.length() == 0) {
			errorList.add("※食品名は必須です");

		}

		if (quantity == null) {
			errorList.add("※数量は必須です");
		} else {
			BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);
			if (quantity.compareTo(max) > 0) {
				errorList.add("※数量は多すぎです、正しく入力してください");
			}

			if (quantity.compareTo(BigInteger.ZERO) < 0) {
				errorList.add("※数量はマイナスです、正しく入力してください");
			}
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

		Food food = new Food(foodName, categoryId, quantity.intValue(), countId, memo, timeLimit);
		food.setUserId(account.getId());
		if (place.equals("冷凍庫")) {
			food.setPlaceId(2);
		} else {
			food.setPlaceId(1);
		}

		food.setUserId(account.getId());
		foodRepository.save(food);
		redirectAttrs.addFlashAttribute("successMessage", "品物を登録しました！");
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
			@RequestParam(name = "timeLimit", defaultValue = "") LocalDate timeLimit,
			RedirectAttributes redirectAttrs

	) {

		Food food = foodRepository.findById(id).get();
		food.setFoodName(foodName);
		food.setCategoryId(categoryId);
		food.setQuantity(quantity);
		food.setCountId(countId);
		food.setMemo(memo);
		food.setTimeLimit(timeLimit);
		foodRepository.save(food);
		redirectAttrs.addFlashAttribute("successMessage", "品物を更新しました！");

		return "redirect:/shokumane/items";
	}

	@GetMapping("/{id}/consume")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
		foodRepository.deleteById(id);
		redirectAttrs.addFlashAttribute("successMessage", "品物を消費しました！");
		return "redirect:/shokumane/items";
	}

	@PostMapping("/multidelete")
	public String multidelete(@RequestParam("ids") int[] selectedIds, RedirectAttributes redirectAttrs) {

		for (int id : selectedIds) {
			foodRepository.deleteById(id);
		}
		redirectAttrs.addFlashAttribute("successMessage", "品物を消費しました！");
		return "redirect:/shokumane/items";

	}

	@PostMapping("/chillmove")
	public String chillmove(@RequestParam("ids") int[] selectedIds, RedirectAttributes redirectAttrs) {

		for (int id : selectedIds) {

			Food food = foodRepository.findById(id).get();
			food.setPlaceId(1);
			foodRepository.save(food);
		}

		redirectAttrs.addFlashAttribute("successMessage", "品物を冷蔵庫へ移動しました！");
		redirectAttrs.addFlashAttribute("warningMessage", "※早めに食べましょう！");
		return "redirect:/shokumane/items";
	}

	@PostMapping("/freezemove")
	public String freezemove(@RequestParam("ids") int[] selectedIds, RedirectAttributes redirectAttrs) {

		for (int id : selectedIds) {

			Food food = foodRepository.findById(id).get();
			food.setPlaceId(2);
			foodRepository.save(food);
		}

		redirectAttrs.addFlashAttribute("successMessage", "品物を冷凍庫へ移動しました！");
		redirectAttrs.addFlashAttribute("warningMessage", "※ 野菜、卵、飲み物など冷凍庫に入れる時に気をつけてください！\r\n"
				+ "※ 冷凍庫に入れる際は、食材を小分けにして保存しましょう！");
		return "redirect:/shokumane/items";
	}
}
