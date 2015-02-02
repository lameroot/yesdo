package ru.yesdo.admin.web.controller;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yesdo.admin.web.domain.MerchantForm;
import ru.yesdo.admin.web.domain.UserForm;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.User;
import ru.yesdo.repository.MerchantRepository;

import javax.validation.Valid;
import java.util.Collection;

/**
 * User: Dikansky
 * Date: 02.02.2015
 */
@Controller
@RequestMapping(value = "merchant", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class MerchantController {
	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.GET, params = "!filter")
	public Collection<Merchant> all() {
		return ImmutableList.copyOf(merchantRepository.findAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public Merchant addUser(@Valid @RequestBody MerchantForm merchantForm) {
		Merchant merchant = new Merchant();
		copyProperties(merchantForm, merchant);
		merchant.setName(merchantForm.getName());
		return merchantRepository.save(merchant);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Merchant updateUser(@PathVariable Long id, @RequestBody MerchantForm merchantForm) {
		Merchant merchant = merchantRepository.findOne(id);
		copyProperties(merchantForm, merchant);
		return merchantRepository.save(merchant);
	}

	private void copyProperties(MerchantForm merchantForm, Merchant merchant) {
	}
}
