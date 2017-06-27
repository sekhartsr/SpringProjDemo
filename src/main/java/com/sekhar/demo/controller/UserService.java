package com.rbtsb.config;

import com.rbtsb.entities.*;
import com.rbtsb.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * @author sekhar
 *
 */
public class UserService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private ActionRepository actionRepository;

	@Inject
	private PasswordEncoder passwordEncoder;

	/**
	 * Create Default Master data and Users
	 */
	@PostConstruct
	protected void initialize() throws Exception {
//		// Create sample roles
		Role roleAdmin = roleRepository.findByName("R_SUPER_ADMIN");
		if (roleRepository.findByName("R_SUPER_ADMIN") == null) {
			logger.debug("Creating default admin role ...");
			roleAdmin = new Role("Super Admin", "R_SUPER_ADMIN", "This is a role for super administrator.");
			roleAdmin.setIsRestricted(true);
			roleRepository.save(roleAdmin);
		}
//		// Create default users
		if (accountRepository.findByUsername("admin") == null) {
			logger.debug("Creating default admin account ...");
			accountRepository.save(new Account("admin", passwordEncoder.encode("admin"), "kent@rbtsb.com", "Kent",
					roleRepository.findByName("R_SUPER_ADMIN"), true));
		}

	
	
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}

	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, createAuthority(account));
	}

	private User createUser(Account account) {
		return new User(account.getUsername(), account.getPassword(), createAuthority(account));
	}

	private List<GrantedAuthority> createAuthority(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		Role role = account.getRole();
		if (role != null) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));

			List<Module> modules = moduleRepository.findByRoleId(role.getId());
			for (Module module : modules)
				authorities.add(new SimpleGrantedAuthority(module.getName()));

			List<Permission> permissions = permissionRepository.findByRoleId(role.getId());
			for (Permission permission : permissions)
				authorities.add(new SimpleGrantedAuthority(permission.getName()));

			List<Action> actions = actionRepository.findByRoleId(role.getId());
			for (Action action : actions)
				authorities.add(new SimpleGrantedAuthority(action.getName()));

			return authorities;
		}

		return null;
	}
}
