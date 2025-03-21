package com.example.jpa.api.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.api.board.dto.BoardDTO;
import com.example.jpa.api.board.service.BoardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        logger.info(" >>>>>>>>> save");
        logger.info(" >>>>>>>>> save boardDTO = {}", boardDTO);

        boardService.save(boardDTO);

        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        logger.info(" >>>>>>>>> findAll");

        // 리스트 가져오기
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);

        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        logger.info(" >>>>>>>>> findById");
        logger.info(" >>>>>>>>> findById id = {}", id);

        // 조회수 증가
        boardService.updateHits(id);

        // 세부 내용 가져오기
        BoardDTO boardDTO = boardService.findById(id);
        logger.info(" >>>>>>>>> findById boardDTO = {}", boardDTO);
        model.addAttribute("board", boardDTO);

        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        logger.info(" >>>>>>>>> updateForm");
        logger.info(" >>>>>>>>> updateForm id = {}", id);

        BoardDTO boardDTO = boardService.findById(id);
        logger.info(" >>>>>>>>> updateForm boardDTO = {}", boardDTO);
        model.addAttribute("boardUpdate", boardDTO);

        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        logger.info(" >>>>>>>>> update");

        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);

        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        logger.info(" >>>>>>>>> deleteForm");

        boardService.delete(id);

        return "redirect:/board/";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        logger.info(" >> paging");

        // pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        logger.info(" >> paging boardEntities = {}", boardList);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1
                : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "paging";
    }

}
