package com.chami.web;

import com.chami.service.KakaoAPI;
import com.chami.repository.UserRepository;
import com.chami.vo.UserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 화면단 조회
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private static final int MAX_INACTIVE_INTERVAL = 60*30; // 초단위, 30분
    private static final String MASTER_SECRET_KEY = "MASTER_SECRET"; // 마스터키
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    // json 파싱
    Gson gson = new Gson();

    @Resource
    private UserRepository userRepository;
    @Autowired
    private KakaoAPI kakaoAPI;


    @GetMapping("/login")
    public String loginPage(HttpSession session) throws Exception {
        // 로그인 안 한 상태 - 마스터 키 오픈
        registerMasterSecret(session);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) throws Exception {

        final String masterSecret = (String) session.getAttribute(MASTER_SECRET_KEY);

        try {
            UserVo user = userRepository.findByUsername(username);
            if (user == null) {
                LOGGER.info("사용자 이름이 존재하지 않습니다: {}", username);
                model.addAttribute("error", "존재하지 않는 사용자 이름입니다.");
                return "login";
            }
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL); // 30분
            } else if (masterSecret != null && masterSecret.equals(password)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL); // 30분
                LOGGER.warn("LOGGED-WITH-MASTER-SECERT: {}", user.getUsername());
            } else {
                LOGGER.info("비밀번호 불일치로 로그인 실패");
                model.addAttribute("error", "비밀번호가 올바르지 않습니다.");
                return "login";
            }

        } catch (Exception e) {
            LOGGER.error("error: {}", e);
            model.addAttribute("error", "로그인 중 에러가 발생하였습니다. 다시 시도해주세요.");
            return "login";
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        // 세션 초기화
        session.invalidate();
    }

    /** 마스터비밀번호를 세션에 등록 (없다면) */
    private void registerMasterSecret(HttpSession session) {
        if (null == session.getAttribute(MASTER_SECRET_KEY)) {
            final String S = Faker.instance().pokemon().name();
            session.setAttribute(MASTER_SECRET_KEY, S);
            LOGGER.info("MASTER-SECRET (new): {}", S);
        } else {
            LOGGER.info("MASTER-SECRET (found): {}",
                    session.getAttribute(MASTER_SECRET_KEY));
        }
    }

    @GetMapping("/signup")
    public String signupPage(Model model) throws Exception {
        List<UserVo> users = userRepository.findByAllUser();
        model.addAttribute("users", gson.toJson(users));
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserVo user, Model model) throws Exception {
        LOGGER.info("User details - before : {}", user);
        int result = 0;
        try {
            // TODO: 시큐리티 적용 후 수정 필요
            user.setRole("ROLE_USER");
            user.setUse_yn('Y');
            result = userRepository.insertUser(user);
            if (result > 0) {
                LOGGER.info("User inserted successfully");
                LOGGER.info("저장 완료, User details - after : {}", user);
            } else {
                LOGGER.warn("No rows affected");
            }
        } catch (Exception e) {
            LOGGER.error("error: {}", e);
            model.addAttribute("error", "회원가입 중 에러가 발생하였습니다. 다시 시도해주세요.");
            return "signup";
        }
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session) throws Exception {
        UserVo user = (UserVo) session.getAttribute("user");
        LOGGER.info("user: {}", user);
        model.addAttribute("user", user);
        return "mypage";
    }
    @GetMapping("/updateUser")
    public String updateUserPage(Model model, HttpSession session) throws Exception {
        List<UserVo> users = userRepository.findByAllUser();
        UserVo user = (UserVo) session.getAttribute("user");
        // vo를 json으로 변환
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("users", gson.toJson(users));
        model.addAttribute("user", user);
        return "updateUser";
    }
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute UserVo user, Model model, HttpSession session) throws Exception {
        LOGGER.info("User details - before : {}", user);
        // 세션에 있던 기존 유저 정보
        UserVo oldUser = (UserVo) session.getAttribute("user");
        int result = 0;
        try {
            user.setUserId(oldUser.getUserId()); // id로 찾아서 수정
            user.setRole(oldUser.getRole());
            user.setUse_yn(oldUser.getUse_yn());
            // 새로 받은 user 정보로 업데이트
            result = userRepository.updateUser(user);
            if (result > 0) {
                LOGGER.info("User updates successfully");
                LOGGER.info("저장 완료, User details - after : {}", user);
                session.setAttribute("user", user);
            } else {
                LOGGER.warn("No rows affected");
            }
        } catch (Exception e) {
            LOGGER.error("error: {}", e);
            model.addAttribute("error", "회원 정보 수정 중 에러가 발생하였습니다. 다시 시도해주세요.");
            return "/updateUser";
        }
        return "redirect:/mypage";
    }

}
