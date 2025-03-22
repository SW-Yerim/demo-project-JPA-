package com.example.jpa.api.common.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.api.common.dto.CompanyResponseDTO;
import com.example.jpa.api.common.service.CompanyService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompanyService companyService;

    // 단일 회사 가져오기
    @GetMapping("/{compId}")
    public String getCompany(@PathVariable Integer compId, Model model) {

        logger.info("[GET] getCompany >>>>>>>>>");

        CompanyResponseDTO company = companyService.getCompany(compId);
        model.addAttribute("company", company);

        return "company-detail";
    }

    // 회사 리스트 가져오기
    @GetMapping("/list")
    public String getCompList(Model model) {

        logger.info("[GET] getCompList >>>>>>>>>");

        List<CompanyResponseDTO> company = companyService.getCompList();
        model.addAttribute("companyList", company);

        return "company-list";
    }

}
