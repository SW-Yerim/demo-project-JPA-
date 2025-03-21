package com.example.jpa.api.approval.entity;

import java.time.LocalDateTime;

import com.example.jpa.api.approval.dto.TemplateInsertRequestDTO;
import com.example.jpa.api.common.entity.CompanyEntity;
import com.example.jpa.api.common.entity.DepartmentEntity;
import com.example.jpa.api.common.entity.UsersEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "templates")
public class TemplateEntity {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : AUTO INCREMENT
    @Column
    private Integer temId; // 양식 아이디

    @Column(name = "comp_id", nullable = false)
    private Integer compId; // 소속 회사

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "dept_id", nullable = false)
    private Integer deptId; // 소속 부서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false),
            @JoinColumn(name = "dept_id", referencedColumnName = "dept_id", insertable = false, updatable = false)
    })
    private DepartmentEntity department;

    @Column(nullable = false, length = 100)
    private String temNm; // 양식 이름

    @Column(columnDefinition = "TEXT")
    private String temDescription; // 양식 설명

    @Column(nullable = false, columnDefinition = "TEXT")
    private String temContents; // 양식 내용

    @Column(nullable = false)
    private Integer temStatus; // 상태 (0: 사용, 1: 삭제)

    @Column(name = "create_id", nullable = false)
    private Integer createId; // 생성자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UsersEntity createNm; // 생성자 이름

    @Column(name = "create_comp_id", nullable = false)
    private Integer createCompId; // 생성자 소속 회사

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false)
    private CompanyEntity createCompany;

    @Column(name = "create_dept_id", nullable = false)
    private Integer createDeptId; // 생성자 소속 부서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "create_comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false),
            @JoinColumn(name = "create_dept_id", referencedColumnName = "dept_id", insertable = false, updatable = false)
    })
    private DepartmentEntity createDepartment;

    @Column(updatable = false)
    private LocalDateTime createDt; // 생성일 (기본값: CURRENT_TIMESTAMP)

    @Column(name = "update_id", nullable = true)
    private Integer updateId; // 수정자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_id", referencedColumnName = "user_id", insertable = false, updatable = false, nullable = true)
    private UsersEntity updateNm; // 수정자 이름

    @Column(name = "update_comp_id", nullable = true)
    private Integer updateCompId; // 수정자 소속 회사

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false, nullable = true)
    private CompanyEntity updateCompany; // 수정자 소속 회사 이름

    @Column(name = "update_dept_id", nullable = true)
    private Integer updateDeptId; // 수정자 소속 부서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "update_comp_id", referencedColumnName = "comp_id", insertable = false, updatable = false, nullable = true),
            @JoinColumn(name = "update_dept_id", referencedColumnName = "dept_id", insertable = false, updatable = false, nullable = true)
    })
    private DepartmentEntity updateDepartment; // 수정자 소속 부서 이름

    @Column(nullable = true)
    private LocalDateTime updateDt; // 수정일 (기본값: CURRENT_TIMESTAMP)

    @PrePersist
    protected void onCreate() {
        this.createDt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDt = LocalDateTime.now();
    }

    // DTO -> Entity 변환 생성자 추가
    public static TemplateEntity toEntity(TemplateInsertRequestDTO dto) {
        TemplateEntity templateEntity = new TemplateEntity();

        templateEntity.setCompId(dto.getCompId());
        templateEntity.setDeptId(dto.getDeptId());
        templateEntity.setTemNm(dto.getTemNm());
        templateEntity.setTemDescription(dto.getTemDescription());
        templateEntity.setTemContents(dto.getTemContents());
        templateEntity.setTemStatus(dto.getTemStatus());
        templateEntity.setCreateId(dto.getCreateId());
        templateEntity.setCreateCompId(dto.getCreateCompId());
        templateEntity.setCreateDeptId(dto.getCreateDeptId());

        return templateEntity;
    }

}
