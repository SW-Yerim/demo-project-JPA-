package com.example.jpa.api.approval.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.jpa.api.approval.entity.TemplateEntity;
import com.example.jpa.api.common.entity.CompanyEntity;
import com.example.jpa.api.common.entity.DepartmentEntity;
import com.example.jpa.api.common.entity.UsersEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TemplateResponseDTO extends TemplateDTO {

        private String compNm; // 양식 소속 회사 이름
        private String deptNm; // 양식 소속 부서 이름

        // 양식 생성자 관련 컬럼
        private Integer createId; // 양식 생성자 아이디
        private String createNm; // 양식 생성자 아이디 이름
        private Integer createCompId; // 양식 생성자 소속 회사
        private String createCompNm; // 양식 생성자 소속 회사 이름
        private Integer createDeptId; // 양식 생성자 소속 부서
        private String createDeptNm; // 양식 생성자 소속 부서 이름
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createDt; // 양식 생성일

        // 양식 수정자 관련 컬럼
        private Integer updateId; // 양식 수정자 아이디
        private String updateNm; // 양식 수정자 아이디 이름
        private Integer updateCompId; // 양식 수정자 소속 회사
        private String updateCompNm; // 양식 수정자 소속 회사 이름
        private Integer updateDeptId; // 양식 수정자 소속 부서
        private String updateDeptNm; // 양식 수정자 소속 부서 이름
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updateDt; // 양식 생성일

        // Entity -> DTO 변환 생성자 추가
        public static TemplateResponseDTO toDto(TemplateEntity entity) {
                return TemplateResponseDTO.builder()
                                .compId(entity.getCompId())
                                .compNm(entity.getCompany().getCompNm())
                                .deptId(entity.getDeptId())
                                .deptNm(entity.getDepartment().getDeptNm())
                                .temId(entity.getTemId())
                                .temNm(entity.getTemNm())
                                .temDescription(entity.getTemDescription())
                                .temContents(entity.getTemContents())
                                .temStatus(entity.getTemStatus())
                                .createId(entity.getCreateId())
                                .createNm(entity.getCreateNm().getUserNm())
                                .createCompId(entity.getCreateId())
                                .createCompNm(entity.getCreateCompany().getCompNm())
                                .createDeptId(entity.getCreateDeptId())
                                .createDeptNm(entity.getCreateDepartment().getDeptNm())
                                .createDt(entity.getCreateDt())
                                .updateId(entity.getUpdateId())
                                .updateNm(Optional.ofNullable(entity.getUpdateNm())
                                                .map(UsersEntity::getUserNm)
                                                .orElse("-"))
                                .updateCompId(entity.getUpdateId())
                                .updateCompNm(Optional.ofNullable(entity.getUpdateCompany())
                                                .map(CompanyEntity::getCompNm)
                                                .orElse("-"))
                                .updateDeptId(entity.getUpdateDeptId())
                                .updateDeptNm(Optional.ofNullable(entity.getUpdateDepartment())
                                                .map(DepartmentEntity::getDeptNm)
                                                .orElse("-"))
                                .updateDt(entity.getUpdateDt())
                                .build();
        }
}
