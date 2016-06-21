/*
 * SignInService.java Copyright (C) 2013 Universidad de Sevilla The use of this
 * project is hereby constrained to the conditions of the TDG Licence, a copy of
 * which you may download from http://www.tdg-seville.info/License.html
 */

package es.us.lsi.dp.services;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import es.us.lsi.dp.domain.BaseActor;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.repositories.UserAccountRepository;

@Service
@Transactional
public class SignInService implements UserDetailsService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userRepository;

	// Business methods -------------------------------------------------------
	
	//Teníamos problemas con este método, ya que provocaba que al registrar un usuario,
	//marcara la tranzacción como rollBackOnly.
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Assert.notNull(username);

		UserDetails result;

		result = userRepository.findByUsername(username);
		if (result == null)
			throw new UsernameNotFoundException(username);

		// WARNING: The following sentences prevent lazy initialisation
		// problems!
		Assert.notNull(result.getAuthorities());
		result.getAuthorities().size();

		return result;

	}

	//Método destinado a sustituir el anterior
	public boolean isUsernameInUse(final String username){
		UserAccount userAccount;
		boolean result = false;
		
		userAccount = userRepository.findByUsername(username);
		if(userAccount != null)
			result = true;
		
		return result;
	}
	
	
	public static UserAccount getPrincipal() {
		UserAccount result;
		SecurityContext context;
		Authentication authentication;
		Object principal;

		// If the asserts in this method fail, then you're
		// likely to have your Tomcat's working directory
		// corrupt. Please, clear your browser's cache, stop
		// Tomcat, update your Maven's project configuration,
		// clean your project, clean Tomcat's working directory,
		// republish your project, and start it over.

		context = SecurityContextHolder.getContext();
		Assert.notNull(context);
		authentication = context.getAuthentication();
		Assert.notNull(authentication);
		principal = authentication.getPrincipal();
		Assert.isTrue(principal instanceof UserAccount);
		result = (UserAccount) principal;
		Assert.notNull(result);
		Assert.isTrue(result.getId() != 0);

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseActor> T getPrincipalAsActor(final Class<T> actorClass) {
		T result;
		UserAccount userAccount;
		Iterator<? extends BaseActor> iterator;

		Assert.notNull(actorClass);

		result = null;
		userAccount = getPrincipal();
		iterator = userAccount.getActors().iterator();
		while (result == null && iterator.hasNext()) {
			BaseActor actor;

			actor = iterator.next();
			if (actor.getClass().equals(actorClass))
				result = (T) actor;
		}

		return result;
	}

	/**
	 * Checks whether the user is authenticated or not
	 * 
	 * @return true if the user is logged in
	 */
	public static boolean check() {
		boolean result;
		SecurityContext context;
		Authentication authentication;

		context = SecurityContextHolder.getContext();
		authentication = context.getAuthentication();

		result = !(authentication instanceof AnonymousAuthenticationToken);

		return result;
	}

	public static boolean checkAuthority(final String role) {
		boolean result;
		boolean authenticated;

		result = false;
		authenticated = check();

		if (authenticated) {
			UserAccount userAccount;
			SimpleGrantedAuthority authority;
			Collection<? extends GrantedAuthority> authorities;

			userAccount = getPrincipal();
			authorities = userAccount.getAuthorities();
			authority = new SimpleGrantedAuthority(role);

			result = authorities.contains(authority);
		}

		return result;
	}

	public static UserAccount getPrincipalOrNull() {
		UserAccount result;

		result = SignInService.check() ? SignInService.getPrincipal() : null;

		return result;
	}
}
