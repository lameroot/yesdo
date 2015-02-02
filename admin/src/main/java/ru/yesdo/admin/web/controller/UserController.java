package ru.yesdo.admin.web.controller;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yesdo.admin.web.domain.UserForm;
import ru.yesdo.admin.web.security.SecurityHelpers;
import ru.yesdo.model.Permission;
import ru.yesdo.model.User;
import ru.yesdo.repository.UserRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dsvdsv on 03.01.15.
 */
@Controller
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.GET, params = "!filter")
	public Collection<User> all() {
		List<User> users = userRepository.findByMerchant(currentUser().getMerchant());
		return ImmutableList.copyOf(users);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User addUser(@Valid @RequestBody UserForm userForm) {
		User user = new User();
		copyProperties(userForm, user);
		user.setLogin(userForm.getLogin());
		user.setPasswordHash(passwordEncoder.encode(userForm.getPassword()));
		return userRepository.save(user);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public User updateUser(@PathVariable Long id, @RequestBody UserForm userForm) {
		User user = userRepository.findOne(id);
		user.getPermissions().clear();
		copyProperties(userForm, user);

		return userRepository.save(user);
	}

	private void copyProperties(UserForm userForm, User user) {
//		if (userForm.getEmail() != null) {
//			user.setEmail(userForm.getEmail());
//		}
//		if (userForm.getName() != null) {
//			user.setName(userForm.getName());
//		}
//		for (Long roleId : userForm.getRoleIds()) {
//			Role role = roleRepository.findOne(roleId);
//			user.getRoles().add(role);
//		}
	}
	@RequestMapping(value = "current", method = RequestMethod.GET)
	public User currentUser() {
		return SecurityHelpers.currentEnhancedUser().user();
	}

	@RequestMapping(value = "permissions", method = RequestMethod.GET)
	public Collection<Object[]> permissions() {
		return EnumSet.allOf(Permission.class).stream()
				.map(x-> new Object[]{x.name(), x.name()})
				.collect(Collectors.toList());
	}
}
