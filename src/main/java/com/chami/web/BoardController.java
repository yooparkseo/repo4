package com.chami.web;

import com.chami.repository.BoardRepository;
import com.chami.vo.BoardVo;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
    // json 파싱
    Gson gson = new Gson();

    @Resource
    private BoardRepository boardRepository;

    @GetMapping("/boardList")
    public String board(@RequestParam String type, Model model) throws Exception {
        LOGGER.info("type={}", type);
        model.addAttribute("type", type);
        model.addAttribute("boardList", boardRepository.selectBoardList(type));
        return "board";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam int boardId, Model model) throws Exception {
        LOGGER.info("boardId={}", boardId);
        BoardVo board = boardRepository.selectBoardDetail(boardId);
        model.addAttribute("board", board);
        return "boardDetail";
    }

    @PostMapping("/deleteBoardDetail")
    public String deleteBoardDetail(@RequestParam int boardId) throws Exception {
        LOGGER.info("boardId={}", boardId);
        int result = 0;
        try {
            result = boardRepository.deleteBoardDetail(boardId);
            if (result > 0) {
                LOGGER.info("Board deleted successfully");
            } else {
                LOGGER.warn("No rows affected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

}
