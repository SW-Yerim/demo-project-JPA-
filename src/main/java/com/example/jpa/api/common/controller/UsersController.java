package com.example.jpa.api.common.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.api.common.dto.UsersResponseDTO;
import com.example.jpa.api.common.service.UsersService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsersController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UsersService usersService;

    // 단일 사용자 가져오기
    @GetMapping("/{userId}")
    public String getUser(@PathVariable Integer userId, Model model) {

        logger.info("[GET] getUser >>>>>>>>>");

        UsersResponseDTO user = usersService.getUser(userId);
        model.addAttribute("user", user);

        return "user_detail";
    }

    // 사용자자 리스트 가져오기
    @GetMapping("/list")
    public String getUserList(Model model) {

        logger.info("[GET] getUserList >>>>>>>>>");

        List<UsersResponseDTO> userList = usersService.getUserList();
        model.addAttribute("userList", userList);

        return "user_list";
    }

}
