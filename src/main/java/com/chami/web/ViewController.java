package com.chami.web;

import com.chami.repository.UserRepository;
import com.chami.service.KakaoAPI;
import com.chami.vo.UserVo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);
    @Resource
    private UserRepository userRepository;
    @Autowired
    private KakaoAPI kakaoAPI;

    @GetMapping("/layout")
    public String layout(Model model) throws Exception {
        // 모든 사용자 조회
        List<UserVo> users = userRepository.findByAllUser();
        LOGGER.info("users: {}", users);
        model.addAttribute("users", users);
        return "layout/layout";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/map")
    public String mapPage(Model model) throws Exception {
        model.addAttribute("apiKey", kakaoAPI.getKakaoApiKey());
        return "map";
    }

    @GetMapping("/cop_info")
    public String cop_info() {
        return "cop_info";
    }

    @GetMapping("/cta")
    public String cta() {
        return "cta";
    }

    @GetMapping("/mileage")
    public String mileage() {
        return "mileage";
    }

}
