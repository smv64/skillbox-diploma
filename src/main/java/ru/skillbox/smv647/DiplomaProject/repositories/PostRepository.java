package ru.skillbox.smv647.DiplomaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.smv647.DiplomaProject.model.Post;
import ru.skillbox.smv647.DiplomaProject.model.enums.PostModerationStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "Select distinct p from Post p " +
            "join fetch p.tags t " +
            "where " +
            "p.moderationStatus = :statusEnum " +
            "and p.active = :active " +
            "and p.time <= current_timestamp "
    )
    List<Post> findWithTagsByModerationStatusAndActive(
            @Param("statusEnum") PostModerationStatusEnum statusEnum,
            @Param("active") Byte active
    );

    @Query(value = "Select distinct p from Post p " +
            "join fetch p.tags t " +
            "where " +
            "p.moderationStatus = :statusEnum " +
            "and p.active = :active " +
            "and p.time <= current_timestamp " +
            "and t.name like :tagStartWith%"
    )
    List<Post> findWithTagsByModerationStatusAndActiveAndTagsNameLike(
            @Param("statusEnum") PostModerationStatusEnum statusEnum,
            @Param("active") Byte active,
            @Param("tagStartWith") String tagStartWith
    );




    @Query(value = "Select distinct p.time from Post p " +
            "where " +
            "p.moderationStatus = :statusEnum " +
            "and p.active = :active " +
            "and p.time <= current_timestamp "
    )
    List<LocalDateTime> findTimeByModerationStatusAndActive(
            @Param("statusEnum") PostModerationStatusEnum statusEnum,
            @Param("active") Byte active
    );
}
