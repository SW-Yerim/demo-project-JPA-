package com.example.jpa.api.approval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jpa.api.approval.entity.TemplateEntity;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Integer> {

    @Query("SELECT t FROM TemplateEntity t " +
            "JOIN FETCH t.company " +
            "JOIN FETCH t.department " +
            "WHERE t.temId = :temId")
    TemplateEntity findTemplate(@Param("temId") Integer temId);

}
