package com.rbtsb.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.rbtsb.entities.Account;

/**
 * Account JPA Repository
 * 
 * @author Kent
 *
 */
public interface AccountRepository extends BaseRepository<Account> {
	
	/**
	 * Find Account By Username
	 * 
	 * @param username
	 * @return
	 */
	@Query("select a from Account a where a.deleted = false and a.username = ?1")
	Account findByUsername(String username);
	
	/**
	 * Find Account with Parameters
	 * 
	 * @param username
	 * @param roleName
	 * @param pageable
	 * @return
	 */
	Page<Account> findByUsernameContainingIgnoreCaseOrRoleNameContainingIgnoreCase(String username, String roleName, Pageable pageable);
	
	/**
	 * Find Account By Email
	 * 
	 * @param email
	 * @return
	 */
	Account findByEmail(String email);
	
}
