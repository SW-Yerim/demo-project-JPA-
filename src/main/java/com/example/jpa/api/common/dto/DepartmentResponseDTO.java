package com.example.jpa.api.common.dto;

import com.example.jpa.api.common.entity.DepartmentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseDTO {

    private Integer compId; // 회사
    private Integer deptId; // 부서
    private String deptNm; // 부서 이름
    private Integer deptStatus; // 회사 상태 ( 0:사용중 / 1:삭제 )

    // Entity -> DTO 변환 생성자 추가
    public static DepartmentResponseDTO toDto(DepartmentEntity entity) {
        return DepartmentResponseDTO.builder()
                .compId(entity.getDepartmentPkEntity().getCompId())
                .deptId(entity.getDepartmentPkEntity().getDeptId())
                .deptNm(entity.getDeptNm())
                .deptStatus(entity.getDeptStatus())
                .build();
    }

}
