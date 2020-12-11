package ru.skillbox.smv647.DiplomaProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.TagsResponse;
import ru.skillbox.smv647.DiplomaProject.model.Post;
import ru.skillbox.smv647.DiplomaProject.model.Tag;
import ru.skillbox.smv647.DiplomaProject.repositories.TagRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final PostService postService;

    public TagsResponse getTagsResponse(String query) {
        Collection<Post> posts = postService.findAllVisiblePostsWithTags(query);
        Map<String, Integer> tagsCount = new HashMap<>();
        Integer maxCount = 0;
        for (Post post : posts) {
            for (Tag tag : post.getTags()) {
                Integer count = tagsCount.getOrDefault(tag.getName(), 0) + 1;
                if (count > maxCount) {
                    maxCount = count;
                }
                tagsCount.put(tag.getName(), count);
            }
        }
        List<TagsResponse.TagResponse> tagResponses = new ArrayList<>();
        for (String tagName : tagsCount.keySet()) {
            tagResponses.add(
                    new TagsResponse.TagResponse()
                            .setName(tagName)
                            .setWeight(1 / (maxCount.floatValue() / tagsCount.get(tagName)))
            );
        }

        return new TagsResponse()
                .setTags(tagResponses);
    }
}
