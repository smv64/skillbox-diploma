package ru.skillbox.smv647.DiplomaProject.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TAGS")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "post")
    private Set<Post> posts;
}
