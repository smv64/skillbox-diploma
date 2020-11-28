package ru.skillbox.smv647.DiplomaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.smv647.DiplomaProject.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
