package ru.skillbox.smv647.DiplomaProject.controllers.responses;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class TagsResponse {
    private List<TagResponse> tags = new ArrayList<>();

    @Data
    @Accessors(chain = true)
    public static class TagResponse {
        private String name;
        private Float weight;
    }
}
