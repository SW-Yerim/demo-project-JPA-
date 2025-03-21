package com.example.jpa.api.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class CompanyEntity {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : AUTO INCREMENT
    @Column(name = "comp_id")
    private Integer compId; // 회사 고유 아이디

    @Column(nullable = false, length = 100)
    private String compNm; // 회사 이름

    @Column(nullable = false)
    private Integer compStatus; // 회사 상태 ( 0:사용중 / 1:삭제 )

}
