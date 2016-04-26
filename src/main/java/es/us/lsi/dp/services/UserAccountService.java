/*
 * UserAccountService.java Copyright (C) 2013 Universidad de Sevilla The use of
 * this project is hereby constrained to the conditions of the TDG Licence, a
 * copy of which you may download from http://www.tdg-seville.info/License.html
 */

package es.us.lsi.dp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import es.us.lsi.dp.domain.BaseActor;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.repositories.UserAccountRepository;
import es.us.lsi.dp.utilities.PasswordEncoder;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	/**
	 * 
	 * Instantiates a UserAccount
	 * 
	 * @return a UserAccount with default values
	 */
	public UserAccount create(final BaseActor actor) {
		UserAccount userAccount;

		userAccount = new UserAccount();

		return userAccount;
	}

	/**
	 * Persists a UserAccount
	 * 
	 * @param userAccount
	 *            a UserAccount to be persisted
	 * @return the saved UserAccount
	 */
	public UserAccount save(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		UserAccount result;
		boolean creating;

		creating = userAccount.getId() == 0;

		if (creating)
			hashPassword(userAccount);

		result = userAccountRepository.save(userAccount);

		return result;
	}

	/**
	 * Retrieves a UserAccount by its Actor from the persistence layer
	 * 
	 * @param actor
	 *            the owner of the account
	 * @return the given actor's UserAccount
	 */
	public UserAccount findByActor(final BaseActor actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = userAccountRepository.findByActorId(actor.getId());

		return result;
	}

	public UserAccount findByUsername(String username) {
		Assert.notNull(username);

		UserAccount result;

		result = userAccountRepository.findByUsername(username);

		return result;
	}

	// Private methods --------------------------------------------------------

	/**
	 * Hashes the password of the provided UserAccount
	 * 
	 * @param userAccount
	 *            account which password will be hashed
	 */
	/**
	 * @param userAccount
	 */
	private void hashPassword(final UserAccount userAccount) {
		String hashedPassword;

		hashedPassword = PasswordEncoder.encode(userAccount.getPassword());

		userAccount.setPassword(hashedPassword);
	}

	// Other business methods -------------------------------------------------

}