package ru.skillbox.smv647.DiplomaProject.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST_VOTES")
public class PostVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "value")
    private Byte value;
}
