package com.example.demo.controller;

import com.example.demo.dto.music.SongDTO;
import com.example.demo.dto.playlist.PlaylistDTO;
import com.example.demo.service.UserApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPlaylistController {

    private final UserApiService userApiService;

    public UserPlaylistController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @GetMapping("/playlists")
    public String showPlaylists(HttpSession session, Model model) {
        String token = (String) session.getAttribute("JWT_TOKEN");

        if (token == null || token.isEmpty()) {
            return "redirect:/login";
        }

        try {
            List<PlaylistDTO> playlists = userApiService.getMyPlaylists(token);
            model.addAttribute("playlists", playlists);
            return "user/playlists";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching playlists: " + e.getMessage());
            return "user/playlists";
        }
    }

    @GetMapping("/playlists/{id}/songs")
    public String showPlaylistSongs(@PathVariable Long id, HttpSession session, Model model) {
        String token = (String) session.getAttribute("JWT_TOKEN");

        if (token == null || token.isEmpty()) {
            return "redirect:/login";
        }

        try {
            List<SongDTO> songs = userApiService.getPlaylistSongs(id, token);
            model.addAttribute("songs", songs);
            model.addAttribute("pageTitle", "Playlist Songs");
            return "user/songs";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching playlist songs: " + e.getMessage());
            return "user/songs";
        }
    }
}
