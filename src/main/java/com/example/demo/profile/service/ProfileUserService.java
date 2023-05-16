package com.example.demo.profile.service;

import com.example.demo.appuser.AppUser;
import com.example.demo.profile.entity.ProfileUserEntity;
import com.example.demo.profile.repository.ProfileUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileUserService {

    private final ProfileUserRepository profileUserRepository;

    public ProfileUserService(ProfileUserRepository profileUserRepository) {
        this.profileUserRepository = profileUserRepository;
    }

    public String saveProfileImage(AppUser appUser, byte[] imageBytes, String imageName) throws IOException {
        String userFolder = "images/" + appUser.getId() + "/";
        String imagePath = userFolder + imageName;

        // Create the user's folder if it doesn't exist
        File folder = new File(userFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Files.write(Paths.get(imagePath), imageBytes);
        ProfileUserEntity profileUserEntity = new ProfileUserEntity(appUser, imagePath);
        profileUserRepository.save(profileUserEntity);
        return imagePath;
    }

    @Transactional(readOnly = true)
    public byte[] getProfileImageById(Long id) throws IOException {
        Optional<ProfileUserEntity> optionalImage = profileUserRepository.findById(id);
        if (optionalImage.isEmpty()) {
            return null;
        }
        ProfileUserEntity profileImage = optionalImage.get();
        String imagePath = profileImage.getImagePath();
        return Files.readAllBytes(Paths.get(imagePath));
    }
}


