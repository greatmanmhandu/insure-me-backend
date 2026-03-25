package com.vozhe.jwt.dashboard_and_mobile.repository;

import com.vozhe.jwt.dashboard_and_mobile.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String name);

    User findByUsername(String userName);

    Optional<User> findUserByAgentNumber(String agentNumber);

    Optional<List<User>> findAllByRoles(String admin);

//    @Modifying
//    @Query("UPDATE user u SET u.password = :password WHERE u.username = :username")
//    void updatePasswordByUsername(@Param("username") String username, @Param("password") String password);
}
