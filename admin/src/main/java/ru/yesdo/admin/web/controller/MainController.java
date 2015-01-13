package ru.yesdo.admin.web.controller;

import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yesdo.admin.web.security.EnhancedUser;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by dsvdsv on 01.01.15.
 */
@Controller
public class MainController {
	@RequestMapping(value = "index")
	public String index(Model model, HttpSession session, Principal principal ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !(authentication.getPrincipal() instanceof EnhancedUser)) {
			model.addAttribute("loggedIn", false);
		} else {
			EnhancedUser user = (EnhancedUser) authentication.getPrincipal();
			model.addAttribute("id", user.user().getId());
			model.addAttribute("login", user.user().getLogin());
			model.addAttribute("permissions", Collections2.transform(authentication.getAuthorities(), Functions.toStringFunction()));
			model.addAttribute("loggedIn", true);
		}
		return "index";
	}
}
