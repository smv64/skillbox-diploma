package ru.skillbox.smv647.DiplomaProject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Data @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_moderator", nullable = false)
    private Byte moderator;

    @Column(name = "reg_time", nullable = false)
    private LocalDateTime regTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "photo", length = Integer.MAX_VALUE)
    private String photo;
}
