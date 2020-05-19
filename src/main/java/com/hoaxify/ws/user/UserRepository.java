package com.hoaxify.ws.user;
/*
 * Created by Oray Kurt
 * Date: 19-May-20
 * Time: 12:03 AM
 */

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
