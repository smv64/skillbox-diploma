package ru.skillbox.smv647.DiplomaProject.controllers.responses.post;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class PostsResponse {
    private Long count;
    private List<Post> posts = new ArrayList<>();

    @Data
    @Accessors(chain = true)
    public static class Post {
        private String id;
        private Long timestamp;
        private User user;
        private String title;
        private String announce;
        private Integer likeCount;
        private Integer dislikeCount;
        private Integer commentCount;
        private Integer viewCount;

        @Data
        @Accessors(chain = true)
        public static class User {
            private String id;
            private String name;
        }
    }
}
