package ru.skillbox.smv647.DiplomaProject.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.smv647.DiplomaProject.model.Post;
import ru.skillbox.smv647.DiplomaProject.model.enums.PostModerationStatusEnum;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("Select p from Post p " +
            "where moderationStatus = :statusEnum and active = :active " +
            "and time <= current_timestamp")
    Page<Post> findByModerationStatusAndActiveNative(
            @Param("statusEnum") PostModerationStatusEnum statusEnum,
            @Param("active") Byte active,
            Pageable pageable
    );
}
