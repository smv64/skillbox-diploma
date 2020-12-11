package ru.skillbox.smv647.DiplomaProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.InitResponse;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.TagsResponse;
import ru.skillbox.smv647.DiplomaProject.services.GlobalSettingService;
import ru.skillbox.smv647.DiplomaProject.services.TagService;

import java.util.Map;


/**
 * для прочих запросов к API.
 */
@RestController
@RequiredArgsConstructor
public class ApiGeneralController {
    private final InitResponse initResponse;
    private final GlobalSettingService globalSettingService;
    private final TagService tagService;

    @RequestMapping(value = "/api/init", method = RequestMethod.GET)
    public InitResponse initData() {
        return initResponse;
    }

    @RequestMapping(value = "/api/settings", method = RequestMethod.GET)
    public Map<String, Boolean> globalSetting() {
        return globalSettingService.getGlobalSettingsResponse();
    }

    @RequestMapping(value = "/api/tag", method = RequestMethod.GET)
    public TagsResponse getTags(
            @RequestParam(name = "query", defaultValue = "") String query
    ) {
        return tagService.getTagsResponse(query);
    }
}
