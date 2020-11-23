package ru.skillbox.smv647.DiplomaProject.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST_COMMENTS")
@Data
@Accessors(chain = true)
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private PostComment parent;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;
}
