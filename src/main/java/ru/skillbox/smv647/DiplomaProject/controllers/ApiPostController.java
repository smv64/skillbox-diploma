package ru.skillbox.smv647.DiplomaProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.post.PostsResponse;
import ru.skillbox.smv647.DiplomaProject.services.PostService;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class ApiPostController {

    private final PostService postService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public PostsResponse getPostList(
            @RequestParam(name = "mode", defaultValue = "recent") String mode,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "0") Integer limit

    ) {
        return postService.findAll(mode, offset, limit);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public PostsResponse searchPostByQuery(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "0") Integer limit

    ) {
        return postService.searchByQuery(query, offset, limit);
    }

    @RequestMapping(value = "byDate", method = RequestMethod.GET)
    public PostsResponse searchPostByDate(
            @RequestParam(name = "date", defaultValue = "") String date,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "0") Integer limit

    ) {
        return postService.searchByDate(date, offset, limit);
    }

    @RequestMapping(value = "byTag", method = RequestMethod.GET)
    public PostsResponse searchPostByTagName(
            @RequestParam(name = "tag", defaultValue = "") String tagName,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "0") Integer limit

    ) {
        return postService.searchByTag(tagName, offset, limit);
    }
}
