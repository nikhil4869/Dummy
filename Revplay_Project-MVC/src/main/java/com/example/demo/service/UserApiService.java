package com.example.demo.service;

import com.example.demo.dto.user.UserDashboardDTO;
import com.example.demo.dto.music.UserProfileDTO;
import com.example.demo.dto.music.HistoryDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApiService {

    private final RestTemplate restTemplate;
    
    // The backend API is running on 8080.
    private final String BASE_URL = "http://localhost:8080";

    public UserApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches the user dashboard data from the primary backend using the provided JWT token.
     * Maps to the /user/dashboard endpoint on the backend.
     *
     * @param token The JWT token stored in the session.
     * @return UserDashboardDTO carrying stats, username, and recently played songs.
     */
    public UserDashboardDTO getDashboardData(String token) {
        String url = BASE_URL + "/user/dashboard";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDashboardDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                UserDashboardDTO.class
        );

        return response.getBody();
    }

    public UserProfileDTO getProfile(String token) {
        String url = BASE_URL + "/user/profile";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<UserProfileDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, UserProfileDTO.class
        );
        return response.getBody();
    }

    public java.util.List<HistoryDTO> getRecentHistory(String token) {
        String url = BASE_URL + "/history/recent";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<HistoryDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, HistoryDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getAllSongs(String token) {
        String url = BASE_URL + "/songs/all";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> searchSongs(String keyword, String token) {
        String url = BASE_URL + "/search/songs?keyword=" + keyword;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getFavorites(String token) {
        String url = BASE_URL + "/favorites";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.AlbumDTO> getAllAlbums(String token) {
        String url = BASE_URL + "/search/albums/all";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.AlbumDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.AlbumDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<String> getAllGenres(String token) {
        String url = BASE_URL + "/search/genres";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String[].class);
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<Integer> getAllYears(String token) {
        String url = BASE_URL + "/search/years";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Integer[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Integer[].class);
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.ArtistDTO> getAllArtists(String token) {
        String url = BASE_URL + "/search/artists/all";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.ArtistDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.ArtistDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getSongsByGenre(String genre, String token) {
        String url = BASE_URL + "/search/songs/by-genre?genre=" + genre;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getSongsByYear(Integer year, String token) {
        String url = BASE_URL + "/search/year?year=" + year;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getSongsByArtist(Long artistId, String token) {
        String url = BASE_URL + "/search/songs/by-artist/" + artistId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getSongsByAlbum(Long albumId, String token) {
        String url = BASE_URL + "/search/songs/by-album/" + albumId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.playlist.PlaylistDTO> getMyPlaylists(String token) {
        String url = BASE_URL + "/playlists/my";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.playlist.PlaylistDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.playlist.PlaylistDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getPlaylistSongs(Long playlistId, String token) {
        String url = BASE_URL + "/playlists/" + playlistId + "/songs";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }

    public void addFavorite(Long songId, String token) {
        String url = BASE_URL + "/favorites/" + songId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public void removeFavorite(Long songId, String token) {
        String url = BASE_URL + "/favorites/" + songId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
    }

    public void addSongToPlaylist(Long playlistId, Long songId, String token) {
        String url = BASE_URL + "/playlists/" + playlistId + "/songs/" + songId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    public void recordPlay(Long songId, String token) {
        String url = BASE_URL + "/player/play/" + songId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public com.example.demo.dto.music.AlbumDTO getAlbumDetails(Long albumId, String token) {
        String url = BASE_URL + "/albums/" + albumId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.AlbumDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.AlbumDTO.class
        );
        return response.getBody();
    }

    public java.util.List<com.example.demo.dto.music.SongDTO> getAlbumSongs(Long albumId, String token) {
        String url = BASE_URL + "/songs/album/" + albumId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<com.example.demo.dto.music.SongDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, com.example.demo.dto.music.SongDTO[].class
        );
        return response.getBody() != null ? java.util.Arrays.asList(response.getBody()) : new java.util.ArrayList<>();
    }
}
