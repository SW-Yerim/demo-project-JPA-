package com.example.jpa.api.common.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UsersEntity {

    @Column(name = "comp_id", nullable = false)
    private Integer compId; // 소속 회사

    @ManyToOne
    @JoinColumn(name = "comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "dept_id", nullable = false)
    private Integer deptId; // 소속 부서

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false),
            @JoinColumn(name = "dept_id", referencedColumnName = "dept_id", insertable = false, updatable = false)
    })
    private DepartmentEntity department;

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : AUTO INCREMENT
    @Column(name = "user_id")
    private Integer userId; // 사용자 아이디

    @Column(name = "user_nm", nullable = false, length = 100)
    private String userNm; // 사용자 이름

    @Column(nullable = false, length = 255)
    private String userPw; // 사용자 비밀번호

    @Column(nullable = false)
    private Integer rankId; // 사용자 직급

    @Column(nullable = false, length = 100)
    private String userEmail; // 사용자 이메일

    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime userDt; // 사용자 생성일

    @Column(nullable = false)
    private Integer userRole; // 사용자 권한 ( 0:일반사용자 / 1:관리자 )

    @Column(nullable = false)
    private Integer userStatus; // 사용자 재직상태 ( 0:재직 / 1:휴직 / 2:퇴직 )

}
