package ru.skillbox.smv647.DiplomaProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.TagsResponse;
import ru.skillbox.smv647.DiplomaProject.model.Tag;
import ru.skillbox.smv647.DiplomaProject.repositories.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagsResponse getTagsResponse() {
        List<Tag> tagList = tagRepository.findAll();
        return new TagsResponse()
                .setTags(
                        tagList.stream()
                                .map(tag -> new TagsResponse.TagResponse()
                                        .setName(tag.getName())
                                        .setWeight(1f)
                                ).collect(Collectors.toList())
                );
    }
}
