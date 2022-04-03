package com.ebook.repositories;

import com.ebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "select * from users where id in" +
            "(select user_id from users_roles where role_id"+
            " in (select id from roles where name = :role))"+
            " order by id", nativeQuery = true)
    List<User> findAllByRole(@Param("role") String role);
}
