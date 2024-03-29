package com.labformjava.labformjava.repository;

import com.labformjava.labformjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserRepository extends JpaRepository<User, Long> {
    @Procedure("get_login_by_token")
    String getLoginByToken(String login);

    User findByLogin(String login);

    User findByToken(String token);

}
