package ru.yesdo.admin.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: Dikansky
 * Date: 29.07.2014
 */
public class UserForm {

	@NotEmpty
	private String login;
	@NotEmpty
	private String password;

	private String[] permissions;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
