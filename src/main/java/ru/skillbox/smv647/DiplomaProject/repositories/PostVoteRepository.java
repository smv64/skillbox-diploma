package ru.skillbox.smv647.DiplomaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.smv647.DiplomaProject.model.PostVote;

@Repository
public interface PostVoteRepository extends JpaRepository<PostVote, Integer> {

}
