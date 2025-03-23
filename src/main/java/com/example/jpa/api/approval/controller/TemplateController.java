package com.example.jpa.api.approval.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa.api.approval.dto.TemplateInsertRequestDTO;
import com.example.jpa.api.approval.dto.TemplateResponseDTO;
import com.example.jpa.api.approval.dto.TemplateUpdateRequestDTO;
import com.example.jpa.api.approval.service.TemplateService;
import com.example.jpa.api.common.dto.CompanyResponseDTO;
import com.example.jpa.api.common.dto.DepartmentResponseDTO;
import com.example.jpa.api.common.service.CompanyService;
import com.example.jpa.api.common.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final TemplateService templateService;

    private final CompanyService companyService;

    private final DepartmentService departmentService;

    // 단일 양식 가져오기
    @GetMapping("/{temId}")
    public String getTemplate(@PathVariable("temId") Integer temId, Model model) {

        logger.info("[GET] getTemplate >>>>>>>>>");

        TemplateResponseDTO template = templateService.getTemplate(temId);
        model.addAttribute("template", template);

        return "template-detail";
    }

    // 양식 리스트 가져오기
    @GetMapping("/list")
    public String getTemList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {

        logger.info("[GET] getTemList >>>>>>>>>");

        List<TemplateResponseDTO> temList = templateService.getTemList(keyword);
        model.addAttribute("temList", temList);
        model.addAttribute("keyword", keyword);

        return "template-list";
    }

    // 양식 등록 페이지 이동
    @GetMapping("/insert")
    public String insertForm(@RequestParam(value = "temId", required = false) Integer temId, Model model) {

        logger.info("[GET] insertForm >>>>>>>>>");

        // 회사 리스트 가져오기
        List<CompanyResponseDTO> compList = companyService.getCompList();

        // 부서 리스트 가져오기
        List<DepartmentResponseDTO> deptList = departmentService.getDeptList();

        model.addAttribute("compList", compList);
        model.addAttribute("deptList", deptList);

        return "template-insert";
    }

    // 양식 등록
    @PostMapping("/insert")
    public String postTemplate(@ModelAttribute TemplateInsertRequestDTO request) {

        logger.info("[POST] postTemplate >>>>>>>>>");

        templateService.postTemlate(request);

        return "redirect:/api/template/list";
    }

    // 양식 수정 페이지 이동
    @GetMapping("/update")
    public String updateForm(@RequestParam(value = "temId", required = false) Integer temId, Model model) {

        logger.info("[GET] updateForm >>>>>>>>>");

        // 회사 리스트 가져오기
        List<CompanyResponseDTO> compList = companyService.getCompList();

        // 부서 리스트 가져오기
        List<DepartmentResponseDTO> deptList = departmentService.getDeptList();

        model.addAttribute("compList", compList);
        model.addAttribute("deptList", deptList);

        // 양식 내용 가져오기
        TemplateResponseDTO template = new TemplateResponseDTO();

        if (temId != null) {
            template = templateService.getTemplate(temId);
        }

        model.addAttribute("template", template);

        logger.info("[GET] template template.compId = {} >>>>>>>>>", template.getCompId());
        logger.info("[GET] template template.deptId = {} >>>>>>>>>", template.getDeptId());

        return "template-update";
    }

    // 양식 수정
    @PutMapping("/update")
    public String putTemplate(@ModelAttribute TemplateUpdateRequestDTO request) {

        logger.info("[PUT] putTemplate >>>>>>>>>");

        templateService.putTemplate(request);

        return "redirect:/api/template/list";
    }

    // 양식 미사용 처리
    @PatchMapping("/update")
    public String patchTemplate(@RequestParam("temId") Integer temId) {

        logger.info("[PATCH] putTemplate >>>>>>>>>");

        templateService.patchTemplate(temId);

        return "redirect:/api/template/list";
    }

    // 양식 삭제
    @DeleteMapping("/delete")
    public String deleteTem(@RequestParam("temId") Integer temId) {

        logger.info("[PATCH] putTemplate >>>>>>>>>");

        templateService.deleteTemplate(temId);

        return "redirect:/api/template/list";
    }

}
