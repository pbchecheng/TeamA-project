package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/shokumane")
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		List<User> users = userRepository.findByEmailAndPassword(email, password);

		if (users.size() == 0) {
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		} else {
			account.setName(name);

			return "redirect:/shokumane/items";
		}

		// if User is not null, 
		//		return "redirect:/shokumane/items";

	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String add(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "password_confirm", defaultValue = "") String password_confirm,
			Model model) {
		List<String> errorList = new ArrayList<>();

		if (name.length() == 0) {
			errorList.add("名前は必須です");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		List<User> buf = userRepository.findByEmail(email);
		// bufに何かデータが入っていれば、emailはでーたべーすに登録済み
		// List<Customer> bufの長さが０ではなければ、emailはデータベースに登録済み
		// buf.size()!=0 でなければ、
		if (email.length() != 0 && buf.size() != 0) {
			errorList.add("登録済みのメールアドレスです");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		if (password_confirm.length() == 0) {
			errorList.add("確認パスワードは必須です");
		}

		if (!password_confirm.equals(password) && password.length() != 0 && password_confirm.length() != 0) {
			errorList.add("パスワードと確認パスワードは一致していません");
		}

		if (errorList.size() > 0) {
			model.addAttribute("error", errorList);
			return "signup";
		}

		User user = new User(name, email, password);
		userRepository.save(user);
		return "login";
	}

}
