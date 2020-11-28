package ru.skillbox.smv647.DiplomaProject.model.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostVoteCount {
    private Integer postId;
    private Long count;
}