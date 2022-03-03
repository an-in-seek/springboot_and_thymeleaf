package com.web.seek.controller;

import com.web.seek.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 작성 폼 데이터 조회
     * @param id
     * @param model
     * @return
     */
    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("board", boardService.findBoardById(id));
        return "/board/form";
    }

    /**
     * 게시글 리스트 조회
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "/board/list";
    }
}
