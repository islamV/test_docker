package com.example.project.entities;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "officer")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @PrePersist @PreUpdate
    private void normalize() {
        if (email != null) email = email.trim().toLowerCase();
        if (fullName != null) fullName = fullName.trim();
    }

    @Override
    public String toString() {
        return "User(id=" + getId() + ", email=" + email + ")";
    }
}
