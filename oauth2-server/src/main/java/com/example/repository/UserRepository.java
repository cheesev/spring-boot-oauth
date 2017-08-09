package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value= "select distinct u from User u inner join fetch u.userRoles where u.username = :username")
    User findOneByUsername(@Param("username") String username);
}
