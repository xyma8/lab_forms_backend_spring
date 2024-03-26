package com.labformjava.labformjava.repository;

import com.labformjava.labformjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserRepository extends JpaRepository<User, Long> {
    @Procedure("get_hash_password_by_login")
    String hashPassword(String login);

    @Procedure("get_token_by_login")
    String getToken(String login);
}
