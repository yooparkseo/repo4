package com.chami.repository;

import com.chami.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    List<BoardVo> selectBoardList(String type) throws Exception;
    BoardVo selectBoardDetail(int boardId) throws Exception;
    int deleteBoardDetail(int boardId) throws Exception;
}
