package com.example.demo.profile.controller;

import com.example.demo.appuser.AppUser;
import com.example.demo.profile.Base64Utils;
import com.example.demo.profile.entity.ProfileUserEntity;
import com.example.demo.profile.service.ProfileUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ProfileUserController {
    private final ProfileUserService profileUserService;

    @PostMapping("/users/profile-image")
    public ResponseEntity<String> uploadProfileImage(@AuthenticationPrincipal AppUser appUser, @RequestParam("image") MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        String imagePath = profileUserService.saveProfileImage(appUser, imageBytes, file.getOriginalFilename());
        return new ResponseEntity<>("Image uploaded successfully! Image path: " + imagePath, HttpStatus.OK);
    }

    @GetMapping("/users/profile-image/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<byte[]> getProfileImageById(@PathVariable Long id) throws IOException {
        byte[] imageBytes = profileUserService.getProfileImageById(id);
        if (imageBytes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }


}

