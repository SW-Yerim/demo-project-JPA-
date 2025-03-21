package com.example.jpa.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.api.common.entity.DepartmentEntity;
import com.example.jpa.api.common.entity.DepartmentPkEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, DepartmentPkEntity> {

}
