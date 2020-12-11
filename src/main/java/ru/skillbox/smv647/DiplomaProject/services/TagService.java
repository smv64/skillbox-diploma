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
    private final TagRepository tagRepository;

//    public TagsResponse getTagsResponse() {
//        List<Tag> tagList = tagRepository.findAll();
//        return new TagsResponse()
//                .setTags(
//                        tagList.stream()
//                                .map(tag -> new TagsResponse.TagResponse()
//                                        .setName(tag.getName())
//                                        .setWeight(1f)
//                                ).collect(Collectors.toList())
//                );
//    }

    public TagsResponse getTagsResponse(String query) {
        Collection<Post> posts = postService.findAllVisiblePostsWithTags(query);
        Map<String, Integer> tagsCount = new HashMap<>();
        Integer maxCount = 0;
        System.out.println("###### debug ######");
        for (Post post : posts) {
            System.out.println(post);
//            System.out.println("post(" + post.getId() + ") : " + post.getTags().size());
            for (Tag tag : post.getTags()) {
//                System.out.println("\ttag: " + tag.getId() + " " + tag.getName());
                Integer count = tagsCount.getOrDefault(tag.getName(), 0) + 1;
                if (count > maxCount) {
                    maxCount = count;
                }
                tagsCount.put(tag.getName(), count);
            }
        }
        System.out.println(maxCount);
        List<TagsResponse.TagResponse> tagResponses = new ArrayList<>();
        for (String tagName : tagsCount.keySet()) {
            System.out.println(tagName + ": " + tagsCount.get(tagName));
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
