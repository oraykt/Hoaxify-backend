package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 12:03 AM
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	Page<User> findByUsernameNot(String username, Pageable page);

//	@Transactional
//	void deleteByUsername(String username);

}
