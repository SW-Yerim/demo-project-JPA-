package com.example.jpa.api.approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateUpdateRequestDTO extends TemplateDTO {

    private Integer updateId; // 양식 수정자 아이디
    private Integer updateCompId; // 양식 수정자 소속 회사
    private Integer updateDeptId; // 양식 수정자 소속 부서

}
