package com.example.jpa.api.common.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.api.common.dto.DepartmentResponseDTO;
import com.example.jpa.api.common.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DepartmentService departmentService;

    // 단일 부서 가져오기
    @GetMapping("/{compId}/{deptId}")
    public String getDepartment(@PathVariable Integer compId, @PathVariable Integer deptId, Model model) {

        logger.info("[GET] getDepartment >>>>>>>>>");

        DepartmentResponseDTO department = departmentService.getDepartment(compId, deptId);
        model.addAttribute("department", department);

        return "department-detail";
    }

    // q부서 리스트 가져오기
    @GetMapping("/list")
    public String getDeptList(Model model) {

        logger.info("[GET] getDeptList >>>>>>>>>");

        List<DepartmentResponseDTO> department = departmentService.getDeptList();
        model.addAttribute("departmentList", department);

        return "department-list";
    }

}
