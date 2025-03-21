package com.example.jpa.api.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class DepartmentEntity {

    // @Column(nullable = false)
    // private Integer compId; // 회사 아이디

    // @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : AUTO
    // INCREMENT
    // @Column
    // private Integer deptId; // 부서 고유 아이디

    @EmbeddedId
    private DepartmentPkEntity departmentPkEntity;

    @Column(nullable = false, length = 100)
    private String deptNm; // 부서 이름

    @Column(nullable = false)
    private Integer deptStatus; // 부서 상태 ( 0:사용중 / 1:삭제 )

}
