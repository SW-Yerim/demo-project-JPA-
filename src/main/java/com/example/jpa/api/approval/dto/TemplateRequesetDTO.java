package com.example.jpa.api.approval.dto;

import java.time.LocalDateTime;

import com.example.jpa.api.approval.entity.TemplateEntity;
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
public class TemplateRequesetDTO extends TemplateDTO {

    private Integer searchType; // 검색 타입
    private String searchKeyword; // 검색 단어
    private Integer pageNum; // 페이지 번호
    private Integer pageSize; // 페이지 출력 갯수

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDt; // 양식 생성일

    // Entity -> DTO 변환 생성자 추가
    public static TemplateRequesetDTO fromEntity(TemplateEntity entity) {
        return TemplateRequesetDTO.builder()
                .compId(entity.getCompId())
                .deptId(entity.getDeptId())
                .temId(entity.getTemId())
                .temNm(entity.getTemNm())
                .temDescription(entity.getTemDescription())
                .temContents(entity.getTemContents())
                .temStatus(entity.getTemStatus())
                .createDt(entity.getCreateDt())
                .build();
    }

}
