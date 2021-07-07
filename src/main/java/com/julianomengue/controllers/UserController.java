package com.julianomengue.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.julianomengue.classes.User;
import com.julianomengue.repositories.UserRepository;
import com.julianomengue.services.JavaEmail;
import com.julianomengue.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@GetMapping()
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "users/login";
	}

	@RequestMapping("/checkUser")
	public String checkUser(Model model, HttpServletRequest request, HttpServletResponse response, User user) {
		String messageLoginError = "";
		Cookie cookie = null;
		if (this.userService.findOne(user.getEmail(), user.getPassword()).getEmail() != null
				&& this.userService.findOne(user.getEmail(), user.getPassword()).getPassword() != null) {
			this.userService.save(this.userService.findOne(user.getEmail(), user.getPassword()));
			cookie = new Cookie("email", user.getEmail());
			cookie.setSecure(false);
			cookie.setHttpOnly(false);
			cookie.setMaxAge(7 * 24 * 60 * 60);
			response.addCookie(cookie);
			model.addAttribute("email", user.getEmail());
			return "redirect:/games";

		} else {
			messageLoginError = user.getEmail() + " is not registered or password don't macht!";
			model.addAttribute("messageLoginError", messageLoginError);
			return "users/login";
		}

	}

	@RequestMapping("/registerUser")
	public String registerUser(Model model, User user) {
		String messageSucess = "";
		String messageError = "";
		user.setEmail(user.getEmail().toLowerCase());
		if (this.userService.findOne(user.getEmail()).getEmail() == null) {
			String password = JavaEmail.getJavaMailSender(user);
			user.setPassword(passwordEncoder().encode(password));
			this.userService.insert(user);
			messageSucess = "Password at " + user.getEmail() + " sendet";
		} else {
			messageError = user.getEmail() + " account is already registered.";
		}

		model.addAttribute("messageError", messageError);
		model.addAttribute("messageSucess", messageSucess);
		this.login(model);
		return "users/login";
	}

	@RequestMapping("/passwordForgot")
	public String passwordForgot(Model model, User user) {
		String messagePasswordSucess = "";
		String messagePasswordError = "";
		user.setEmail(user.getEmail().toLowerCase());
		if (this.userService.findOne(user.getEmail()).getEmail() != null) {
			User u = this.userService.getCurrentUser(user.getEmail());
			String password = JavaEmail.emailRepeatPassword(u);
			u.setPassword(passwordEncoder().encode(password));
			this.userService.save(u);
			messagePasswordSucess = "New password sendet to " + u.getEmail();
		} else {
			messagePasswordError = user.getEmail() + " is not registered.";
		}
		model.addAttribute("messagePasswordSucess", messagePasswordSucess);
		model.addAttribute("messagePasswordError", messagePasswordError);
		return "users/login";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletResponse response) {
		Cookie cookie = new Cookie("email", null);
		cookie.setSecure(false);
		cookie.setHttpOnly(false);
		cookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(cookie);
		return "redirect:/";
	}

}
