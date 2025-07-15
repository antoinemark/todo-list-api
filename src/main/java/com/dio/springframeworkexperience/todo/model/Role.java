package com.dio.springframeworkexperience.todo.model; // Adjust package if different

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@EqualsAndHashCode(of = {"name"}) // Generate equals/hashCode based ONLY on name
@ToString(exclude = {"users"}) // Exclude circular refs
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Ex: ROLE_ADMIN, ROLE_USER

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}