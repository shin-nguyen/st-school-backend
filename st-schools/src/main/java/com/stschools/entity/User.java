package com.stschools.entity;

import com.stschools.common.enums.AuthenticationType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 90, nullable = false)
    private String fullName;

    @Column(length = 64)
    private String avatar;

    @Column(nullable = false)
    private LocalDate birthday;

    private String address;
    private String phone;
    private boolean status;

    @Column
    private Date createdTime;
    @Column
    private Date updateTime;

    @PrePersist
    protected void onCreate(){
        this.createdTime = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateTime = new Date();
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", length = 10)
    private AuthenticationType authenticationType;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentBlog> commentBlogs;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<LikeBlog> likeBlogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Blog> blogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password, String name, String avatar, LocalDate birthday, String address, String phone, Boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = name;
        this.avatar = avatar;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }
}
