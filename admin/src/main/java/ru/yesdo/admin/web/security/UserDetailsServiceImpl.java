package ru.yesdo.admin.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yesdo.model.Permission;
import ru.yesdo.model.User;
import ru.yesdo.repository.UserRepository;

import java.util.*;

/**
 * Created by dsvdsv on 02.01.15.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByLogin(username);

		if (users.isEmpty()) {
			logger.debug("Query returned no results for user '" + username + "'");
			userNotFoundError(username);
		}

		if (users.size() > 1) {
			userDuplicationError(username);
		}

		User user = users.get(0);

		Set<GrantedAuthority> dbAuthsSet = new HashSet<>();

		dbAuthsSet.addAll(loadUserAuthorities(user));
		dbAuthsSet.addAll(loadGroupAuthorities(user));

		List<GrantedAuthority> dbAuths = new ArrayList<>(dbAuthsSet);

		return createUserDetails(user, dbAuths);
	}

	private Collection<? extends GrantedAuthority> loadGroupAuthorities(User user) {
		return Collections.emptyList();
	}

	private Collection<? extends GrantedAuthority> loadUserAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		for (Permission permission : user.getPermissions()) {
			authorities.add(new SimpleGrantedAuthority(permission.name().toUpperCase()));
		}
		return authorities;
	}

	protected EnhancedUser createUserDetails(
			User ua,
			List<GrantedAuthority> combinedAuthorities
	) {
		final Date currDate = new Date();
		boolean accountLocked = false; //ua.getBlockedUtil() != null && ua.getBlockedUtil().after(currDate);
		boolean accountExpired = false; // Boolean.TRUE.equals(ua.getForcePassword()) || (ua.getPasswordExpiration() != null && ua.getPasswordExpiration().before(currDate));
		return new EnhancedUser(
				ua,
				ua.getLogin(),
				ua.getPasswordHash(),
				true,//ua.getEnabled(),
				!accountExpired, true, !accountLocked,
				combinedAuthorities
		);
	}

	protected void noGratedAuthorityError(String username) {
		throw new UsernameNotFoundException(String.format("User %s has no GrantedAuthority", username));
	}

	protected void userDuplicationError(String username, Throwable throwable) throws DuplicateUserException {
		throw new DuplicateUserException("More than one user found with name '" + username + "'", throwable);
	}

	protected void userDuplicationError(String username) throws DuplicateUserException {
		userDuplicationError(username, null);
	}

	protected void userNotFoundError(String username) {
		throw new UsernameNotFoundException(String.format("Username %s not found", username));
	}
}
