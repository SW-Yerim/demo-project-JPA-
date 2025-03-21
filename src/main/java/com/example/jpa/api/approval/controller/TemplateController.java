package com.example.jpa.api.approval.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.api.approval.dto.TemplateInsertRequestDTO;
import com.example.jpa.api.approval.dto.TemplateResponseDTO;
import com.example.jpa.api.approval.service.TemplateService;
import com.example.jpa.api.common.dto.CompanyResponseDTO;
import com.example.jpa.api.common.service.CompanyService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final TemplateService templateService;

    private final CompanyService companyService;

    // 단일 양식 가져오기
    @GetMapping("/{temId}")
    public String getTemplate(@PathVariable Integer temId, Model model) {

        logger.info("[GET] getTemplate >>>>>>>>>");

        TemplateResponseDTO template = templateService.getTemplate(temId);
        model.addAttribute("template", template);

        return "template_detail";
    }

    // 양식 리스트 가져오기
    @GetMapping("/list")
    public String getTemList(Model model) {

        logger.info("[GET] getTemList >>>>>>>>>");

        List<TemplateResponseDTO> temList = templateService.getTemList();
        model.addAttribute("temList", temList);

        logger.info("[GET] getTemList temList ={} >>>>>>>>>", temList);

        return "template_list";
    }

    @GetMapping("/save")
    public String saveForm(Model model) {

        // 회사 리스트 가져오기
        List<CompanyResponseDTO> companyList = companyService.getCompList();

        // 부서 리스트 가져오기

        return "save";
    }

    // 양식 등록
    @PostMapping("/save")
    public String postMethodName(@ModelAttribute TemplateInsertRequestDTO request) {

        return "/list";
    }

    // // 양식 수정
    // @PutMapping("/tem")
    // public ResponseEntity<TemplateResponseDTO> putTem(@RequestBody
    // TemplateUpdateRequestDTO request) {
    // }

    // // 양식 임시 삭제
    // @PatchMapping("/tem")
    // public ResponseEntity<TemplateResponseDTO> patchTem(@RequestBody
    // TemplateUpdateRequestDTO request) {
    // }

    // // 양식 삭제
    // @DeleteMapping("/tem")
    // public ResponseEntity<Void> deleteTem(@RequestParam Integer temId) {
    // }

}
