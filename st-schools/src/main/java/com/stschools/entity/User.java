package com.stschools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Role;
import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = true, unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String avatar;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    private String address;
    private String phone;
    private boolean active;

    private String activationCode;
    private String passwordResetCode;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdTime;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
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

    // @Column(name = "authentication_type", length = 10)
    // private AuthenticationType authenticationType;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<CommentBlog> commentBlogs;
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<LikeBlog> likeBlogs;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Blog> blogs;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Order> orders = new ArrayList<>();

    // @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    // @EqualsAndHashCode.Exclude
    // @ToString.Exclude
    // @JoinTable(name = "tbl_user_role",
    //         joinColumns = @JoinColumn(name = "user_id"),
    //         inverseJoinColumns = @JoinColumn(name = "role_id")
    // )

    private AuthProvider provider;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<CommentBlog> commentBlogs;
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<LikeBlog> likeBlogs;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Blog> blogs;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Order> orders;

}
