package com.example.Kallas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Kallas.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.name LIKE LOWER(CONCAT ('%',:name, '%'))")
    Page<User> findByName(@Param("name") String name, Pageable pageable);

}
