package ru.skillbox.smv647.DiplomaProject.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GlobalSettingsResponse {
    @JsonProperty("MULTIUSER_MODE")
    private Boolean multiUserMode;
    @JsonProperty("POST_PREMODERATION")
    private Boolean postPreModeration;
    @JsonProperty("STATISTICS_IS_PUBLIC")
    private Boolean statisticIsPublic;
}
