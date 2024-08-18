package com.chami.service;

import com.chami.repository.UserRepository;
import com.chami.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    /** 사용자 전체 조회 */
    List<UserVo> findByAllUser() throws Exception {
        return userRepository.findByAllUser();
    }
    /** 아이디로 사용자 조회 */
    UserVo findByUsername(String username) throws Exception {
        return userRepository.findByUsername(username);
    }
    /** 사용자 등록 */
    int insertUser(UserVo user) throws Exception {
        return userRepository.insertUser(user);
    }
    /** 사용자 수정 */
    int updateUser(UserVo user) throws Exception {
        return userRepository.updateUser(user);
    }

}
