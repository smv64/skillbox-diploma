package ru.skillbox.smv647.DiplomaProject.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.smv647.DiplomaProject.model.PostWithCounts;
import ru.skillbox.smv647.DiplomaProject.model.enums.PostModerationStatusEnum;

import java.time.LocalDateTime;

@Repository
public interface PostWithCountsRepository extends JpaRepository<PostWithCounts, Integer> {

    Page<PostWithCounts> findByModerationStatusAndActiveAndTimeBefore(
            PostModerationStatusEnum statusEnum,
            Byte active,
            LocalDateTime time,
            Pageable pageable
    );

    Page<PostWithCounts> findByTitleContainingAndActiveAndTimeBefore(
            String querySearch,
            Byte active,
            LocalDateTime time,
            Pageable pageable
    );

    Page<PostWithCounts> findByModerationStatusAndActiveAndTimeGreaterThanEqualAndTimeLessThan(
            PostModerationStatusEnum statusEnum,
            Byte active,
            LocalDateTime timeStart,
            LocalDateTime timeEnd,
            Pageable pageable
    );

    Page<PostWithCounts> findByTagsNameAndModerationStatusAndActiveAndTimeBefore(
            String tagName,
            PostModerationStatusEnum statusEnum,
            Byte active,
            LocalDateTime time,
            Pageable pageable
    );
}
