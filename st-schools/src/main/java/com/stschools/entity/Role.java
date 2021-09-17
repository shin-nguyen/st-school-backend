package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "tbl_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<User> user;
}
