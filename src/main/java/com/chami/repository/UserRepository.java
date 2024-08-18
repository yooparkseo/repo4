package com.chami.repository;

import com.chami.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserRepository {
    List<UserVo> findByAllUser() throws Exception;
    UserVo findByUsername(String username) throws Exception;
    int insertUser(UserVo user) throws Exception;
    int updateUser(UserVo user) throws Exception;
}
