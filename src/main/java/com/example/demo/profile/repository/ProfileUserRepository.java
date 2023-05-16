package com.example.demo.profile.repository;

import com.example.demo.appuser.AppUser;
import com.example.demo.profile.entity.ProfileUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileUserRepository extends JpaRepository<ProfileUserEntity, Long> {
  //  Optional<ProfileUserEntity> findByAppUser(AppUser appUser);
    List<ProfileUserEntity> findAllByAppUserId(Long appUserID);
  List<ProfileUserEntity> findByAppUser(AppUser appUser);
 // Optional<ProfileUserEntity> findByAppUser(AppUser appUser);
}
