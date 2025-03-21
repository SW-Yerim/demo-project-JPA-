package com.example.jpa.api.approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateInsertRequestDTO extends TemplateDTO {

    private Integer createId; // 양식 생성자 아이디
    private Integer createCompId; // 양식 생성자 소속 회사
    private Integer createDeptId; // 양식 생성자 소속 부서

}
