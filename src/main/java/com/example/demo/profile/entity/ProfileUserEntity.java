package com.example.demo.profile.entity;

import com.example.demo.appuser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class ProfileUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(nullable = false)
    private String folderPath;

    @Column(nullable = false)
    private String imagePath;
    public ProfileUserEntity(String imagePath){
        this.imagePath = imagePath;
    }
    public ProfileUserEntity(AppUser appUser, String imagePath) {
        this.appUser = appUser;
        this.imagePath = imagePath;
        this.folderPath = imagePath.substring(0, imagePath.lastIndexOf("/") + 1);
    }
}
