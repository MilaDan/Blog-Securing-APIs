package com.chuwa.blog.repository.security;

import com.chuwa.blog.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author b1go
 * @date 6/26/22 3:57 PM
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param email
     * @return Optional
     */
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String username, String email);

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String user);

    Boolean existsByEmail(String email);
}
