package ru.yesdo.admin.web.domain;

import org.hibernate.validator.constraints.NotEmpty;
import ru.yesdo.model.Permission;

import javax.validation.constraints.NotNull;

/**
 * User: Dikansky
 * Date: 29.07.2014
 */
public class UserForm {

	@NotEmpty
	private String login;
	@NotEmpty
	private String password;

	@NotNull
	private Permission[] permissions;

	@NotNull
	private Long merchantId;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Permission[] getPermissions() {
		return permissions;
	}

	public void setPermissions(Permission[] permissions) {
		this.permissions = permissions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
