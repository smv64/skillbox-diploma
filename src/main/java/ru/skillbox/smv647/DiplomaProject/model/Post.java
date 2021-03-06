package ru.skillbox.smv647.DiplomaProject.model;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.skillbox.smv647.DiplomaProject.model.enums.PostModerationStatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "POSTS")
@Data
@Accessors(chain = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_active")
    private Byte active;

    @Column(name = "moderation_status",
            columnDefinition = "ENUM('NEW', 'ACCEPTED', 'DECLINED')")
    private PostModerationStatusEnum moderationStatus;

    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private User moderator;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @ManyToMany
    @JoinTable(
            name = "TAG2POST",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Tag> tags;
}
