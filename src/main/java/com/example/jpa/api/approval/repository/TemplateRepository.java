package com.example.jpa.api.approval.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jpa.api.approval.entity.TemplateEntity;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Integer> {

    // @Query("SELECT t FROM TemplateEntity t " +
    // "JOIN FETCH t.company " +
    // "JOIN FETCH t.department " +
    // "WHERE t.temId = :temId")
    // TemplateEntity findTemplate(@Param("temId") Integer temId);

    // 단일 양식 가져오기
    TemplateEntity findByTemId(Integer temId);

    // 양식 리스트 이름으로 검색
    List<TemplateEntity> findByTemNmContaining(String keyword, Sort sort);

}
