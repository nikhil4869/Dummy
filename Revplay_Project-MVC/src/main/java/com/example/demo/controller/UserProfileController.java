package com.example.demo.controller;

import com.example.demo.dto.music.UserProfileDTO;
import com.example.demo.service.UserApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final UserApiService userApiService;

    public UserProfileController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        String token = (String) session.getAttribute("JWT_TOKEN");

        if (token == null || token.isEmpty()) {
            return "redirect:/login";
        }

        try {
            UserProfileDTO profile = userApiService.getProfile(token);
            model.addAttribute("profile", profile);
            return "user/profile";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching profile: " + e.getMessage());
            return "user/profile";
        }
    }
}
