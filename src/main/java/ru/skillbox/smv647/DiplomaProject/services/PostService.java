package ru.skillbox.smv647.DiplomaProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.CalendarResponse;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.post.PostsResponse;
import ru.skillbox.smv647.DiplomaProject.model.Post;
import ru.skillbox.smv647.DiplomaProject.model.PostWithCounts;
import ru.skillbox.smv647.DiplomaProject.model.enums.PostModerationStatusEnum;
import ru.skillbox.smv647.DiplomaProject.repositories.PostRepository;
import ru.skillbox.smv647.DiplomaProject.repositories.PostWithCountsRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostWithCountsRepository postWithCountsRepository;

    private static final Byte IS_ACTIVE = 1;
    private static final String MODE_RECENT = "recent";
    private static final String MODE_POPULAR = "popular";
    private static final String MODE_BEST = "best";
    private static final String MODE_EARLY = "early";
    private static final String FIELD_TIME = "time";
    private static final String FIELD_COMMENT_COUNT = "commentCount";
    private static final String FIELD_LIKE_COUNT = "likeCount";


    public List<Post> findAllVisiblePostsWithTags(String query) {
        if (query.isEmpty()) {
            return postRepository.findWithTagsByModerationStatusAndActive(
                    PostModerationStatusEnum.ACCEPTED,
                    IS_ACTIVE
            );
        } else {
            return postRepository.findWithTagsByModerationStatusAndActiveAndTagsNameLike(
                    PostModerationStatusEnum.ACCEPTED,
                    IS_ACTIVE,
                    query
            );
        }
    }

    public PostsResponse findAll(String mode, Integer offset, Integer limit) {
        PostsResponse postsResponse = new PostsResponse();
        Sort sort = sortByMode(mode);
        if (sort == null) {
            throw new RuntimeException("invalid mode value: " + mode);
        }
        Pageable pageable = PageRequest.of(offset / limit, limit, sort);
        Page<PostWithCounts> postWithCountsPage = postWithCountsRepository.findByModerationStatusAndActiveAndTimeBefore(
                PostModerationStatusEnum.ACCEPTED,
                IS_ACTIVE,
                LocalDateTime.now(),
                pageable
        );
        List<PostWithCounts> postWithCounts = postWithCountsPage.getContent();
        postsResponse.setCount(postWithCountsPage.getTotalElements())
                .setPosts(
                        postWithCounts.stream()
                                .map(this::convertPostToPostResponseElem)
                                .collect(Collectors.toList())
                );
        return postsResponse;
    }

    public PostsResponse searchByQuery(String query, Integer offset, Integer limit) {
        PostsResponse postsResponse = new PostsResponse();
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<PostWithCounts> postWithCountsPage;
        String querySearch = query.trim();
        if (querySearch.isEmpty()) {
            postWithCountsPage = postWithCountsRepository.findByModerationStatusAndActiveAndTimeBefore(
                    PostModerationStatusEnum.ACCEPTED,
                    IS_ACTIVE,
                    LocalDateTime.now(),
                    pageable
            );
        } else {
            postWithCountsPage = postWithCountsRepository.findByTitleContainingAndActiveAndTimeBefore(
                    querySearch,
                    IS_ACTIVE,
                    LocalDateTime.now(),
                    pageable
            );
        }
        List<PostWithCounts> postWithCounts = postWithCountsPage.getContent();
        postsResponse.setCount(postWithCountsPage.getTotalElements())
                .setPosts(
                        postWithCounts.stream()
                                .map(this::convertPostToPostResponseElem)
                                .collect(Collectors.toList())
                );
        return postsResponse;
    }

    public PostsResponse searchByDate(String date, Integer offset, Integer limit) {
        PostsResponse postsResponse = new PostsResponse();
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<PostWithCounts> postWithCountsPage;
        String dateString = date.trim();
        if (dateString.isEmpty()) {
            return postsResponse.setCount(0L);
        } else {
            LocalDate dateParsed = LocalDate.parse(dateString);
            LocalDateTime dateSearchFrom = dateParsed.atStartOfDay();
            LocalDateTime dateSearchTo = dateSearchFrom.plusDays(1);
            postWithCountsPage = postWithCountsRepository.findByModerationStatusAndActiveAndTimeGreaterThanEqualAndTimeLessThan(
                    PostModerationStatusEnum.ACCEPTED,
                    IS_ACTIVE,
                    dateSearchFrom,
                    dateSearchTo,
                    pageable
            );
        }
        List<PostWithCounts> postWithCounts = postWithCountsPage.getContent();
        postsResponse.setCount(postWithCountsPage.getTotalElements())
                .setPosts(
                        postWithCounts.stream()
                                .map(this::convertPostToPostResponseElem)
                                .collect(Collectors.toList())
                );
        return postsResponse;
    }

    public PostsResponse searchByTag(String tagName, Integer offset, Integer limit) {
        PostsResponse postsResponse = new PostsResponse();
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<PostWithCounts> postWithCountsPage;
        postWithCountsPage = postWithCountsRepository.findByTagsNameAndModerationStatusAndActiveAndTimeBefore(
                tagName,
                PostModerationStatusEnum.ACCEPTED,
                IS_ACTIVE,
                LocalDateTime.now(),
                pageable
        );
        List<PostWithCounts> postWithCounts = postWithCountsPage.getContent();
        postsResponse.setCount(postWithCountsPage.getTotalElements())
                .setPosts(
                        postWithCounts.stream()
                                .map(this::convertPostToPostResponseElem)
                                .collect(Collectors.toList())
                );
        return postsResponse;
    }

    public CalendarResponse getCalendarResponse(Integer searchYear) {
        CalendarResponse calendarResponse = new CalendarResponse();
        Set<Integer> years = calendarResponse.getYears();
        Map<String, Integer> posts = calendarResponse.getPosts();
        if (searchYear == null) searchYear = LocalDateTime.now().getYear();

        List<LocalDateTime> times = postRepository.findTimeByModerationStatusAndActive(
                PostModerationStatusEnum.ACCEPTED,
                IS_ACTIVE
        );


        for (LocalDateTime time : times) {
            Integer yearPublication = time.getYear();
            years.add(yearPublication);
            if (yearPublication.equals(searchYear)) {
                String date = DateTimeFormatter.ISO_DATE.format(time);
                Integer count = posts.getOrDefault(date, 0) + 1;
                posts.put(date, count);
            }
        }

        calendarResponse.setYears(years);
        calendarResponse.setPosts(posts);
        return calendarResponse;
    }

    private Sort sortByMode(String mode) {
        switch (mode) {
            case MODE_RECENT:
                return Sort.by(Sort.Direction.DESC, FIELD_TIME);
            case MODE_POPULAR:
                return Sort.by(Sort.Direction.DESC, FIELD_COMMENT_COUNT);
            case MODE_BEST:
                return Sort.by(Sort.Direction.DESC, FIELD_LIKE_COUNT);
            case MODE_EARLY:
                return Sort.by(Sort.Direction.ASC, FIELD_TIME);
        }
        return null;
    }

    private PostsResponse.Post convertPostToPostResponseElem(PostWithCounts postWithCounts) {
        return new PostsResponse.Post()
                .setId(postWithCounts.getId().toString())
                .setTimestamp(datetimeDiff(postWithCounts.getTime()))
                .setUser(
                        new PostsResponse.Post.User()
                                .setId(postWithCounts.getAuthor().getId().toString())
                                .setName(postWithCounts.getAuthor().getName())
                )
                .setTitle(postWithCounts.getTitle())
                .setAnnounce(postWithCounts.getTitle())
                .setLikeCount(postWithCounts.getLikeCount())
                .setDislikeCount(postWithCounts.getDislikeCount())
                .setCommentCount(postWithCounts.getCommentCount())
                .setViewCount(postWithCounts.getViewCount());
    }

    private Long datetimeDiff(LocalDateTime localDateTime) {
        /* dirty magic */
        return Timestamp.valueOf(localDateTime).getTime() / 1000;
    }
}
