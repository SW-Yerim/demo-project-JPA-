package com.example.jpa.api.common.dto;

import com.example.jpa.api.common.entity.CompanyEntity;

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
public class CompanyResponseDTO {

    private Integer compId; // 회사
    private String compNm; // 회사 이름
    private Integer compStatus; // 회사 상태 ( 0:사용중 / 1:삭제 )

    // Entity -> DTO 변환 생성자 추가
    public static CompanyResponseDTO toDto(CompanyEntity entity) {
        return CompanyResponseDTO.builder()
                .compId(entity.getCompId())
                .compNm(entity.getCompNm())
                .compStatus(entity.getCompStatus())
                .build();
    }

}
