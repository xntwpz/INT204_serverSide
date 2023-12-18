package dev.bestzige.simplerbacjavaservlet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)"),
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findAllWithSearch", query = "SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(:search)"),
        @NamedQuery(name = "User.countAll", query = "SELECT COUNT(u) FROM User u"),
        @NamedQuery(name = "User.countAllWithSearch", query = "SELECT COUNT(u) FROM User u WHERE LOWER(u.username) LIKE LOWER(:search)")
})
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;
}
