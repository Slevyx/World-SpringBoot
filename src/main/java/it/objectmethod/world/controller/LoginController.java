package it.objectmethod.world.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@PostMapping("/login")
	public String login(@RequestParam(name = "username", required = false) String username, ModelMap map, HttpSession session) {
		String forwardTo = "City";
		if(username == null || username.isBlank()) {
			forwardTo = "Login";
			map.addAttribute("error", "Username cannot be empty.");
		}
		else {
			session.setAttribute("loggedUser", username);
		}
		return forwardTo;
	}
	
	@GetMapping("/login")
	public String landToLoginPage() {
		return "Login";
	}
}
