package com.chami.service;

import com.chami.repository.BoardRepository;
import com.chami.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /** 게시글 전체 조회 */
    public List<BoardVo> selectBoardList(String type) throws Exception {
        return boardRepository.selectBoardList(type);
    }
    /** 게시글 상세 조회 */
    public BoardVo selectBoardDetail(int boardId) throws Exception {
        return boardRepository.selectBoardDetail(boardId);
    }
    /** 게시글 등록 */
    /** 게시글 삭제 */
    public int deleteBoardDetail(int boardId) throws Exception {
        return boardRepository.deleteBoardDetail(boardId);
    }
}
