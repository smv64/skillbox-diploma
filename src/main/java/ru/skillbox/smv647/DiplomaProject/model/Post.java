package ru.skillbox.smv647.DiplomaProject.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.skillbox.smv647.DiplomaProject.model.enums.PostModerationStatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    @Enumerated(EnumType.STRING)
    private PostModerationStatusEnum moderationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private User moderator;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "TAG2POST",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<Tag> tags = new HashSet<>();
}
