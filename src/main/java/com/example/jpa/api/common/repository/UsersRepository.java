package com.example.jpa.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jpa.api.common.entity.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

        @Query("SELECT u FROM UsersEntity u " +
                        "JOIN FETCH u.company " +
                        "JOIN FETCH u.department " +
                        "WHERE u.userId = :userId")
        UsersEntity findUser(@Param("userId") Integer userId);

}
