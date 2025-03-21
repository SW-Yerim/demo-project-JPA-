package com.example.jpa.api.approval.dto;

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
public class TemplateDTO {

    private Integer compId; // 양식 소속 회사
    private Integer deptId; // 양식 소속 부서

    private Integer temId; // 양식 아이디
    private String temNm; // 양식 이름
    private String temDescription; // 양식 설명
    private String temContents; // 양식 내용
    private Integer temStatus; // 양식 상태 ( 0:사용중 / 1:삭제 )

}
