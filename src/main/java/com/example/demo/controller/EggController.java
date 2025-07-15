package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shokumane")
public class EggController {
	private List<String> list = new ArrayList<>();

	public EggController() {
		list.add("食べ物を無駄しないように。");
		list.add("ごはんと太陽ふともう、普通に生きてるな。");
		list.add("食べるために生きるな。生きるために食べよ。");
		list.add("おいしいに慣れても、おいしいは飽きない。");
		list.add("いただきます。ごちそうさまです。");
		list.add("新しい料理の発見は、新しい星の発見よりも人類を幸福にする。");
		list.add("空腹では隣人を愛せない。");
		list.add("全員が美味しいという料理はこの世に存在しない。");
	}

	//	 "/", "/login", "/logout", "/signup "/items", "/add", ",
	//	@GetMapping({ "/edit" })
	//	public String showPage(Model model, HttpServletRequest req) {
	//		model.addAttribute("text", getRandom());
	//		return req.getServletPath().substring(1);
	//	}

	@PostMapping("/refresh")
	public String refresh(Model model,
			@RequestHeader(value = "Referer", required = false) String referer,
			RedirectAttributes redirectAttributes) {

		String randomText = getRandom();
		redirectAttributes.addFlashAttribute("text", randomText);
		model.addAttribute("text", getRandom());
		if (referer != null)
			return "redirect:" + URI.create(referer).getPath();
		else
			return "redirect:/";
	}

	private String getRandom() {
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
	}

}
