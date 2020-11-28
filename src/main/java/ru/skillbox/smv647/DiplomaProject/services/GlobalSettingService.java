package ru.skillbox.smv647.DiplomaProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.GlobalSettingsResponse;
import ru.skillbox.smv647.DiplomaProject.model.GlobalSetting;
import ru.skillbox.smv647.DiplomaProject.repositories.GlobalSettingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalSettingService {
    private final GlobalSettingRepository settingRepository;
    private final String YES = "YES";

    public GlobalSettingsResponse getGlobalSettingsResponse(){
        List<GlobalSetting> globalSettingList = settingRepository.findAll();
        GlobalSettingsResponse response = new GlobalSettingsResponse();
        for(GlobalSetting globalSetting: globalSettingList){
            if(globalSetting.getName().equals("MULTIUSER_MODE")){
                response.setMultiUserMode(globalSetting.getValue().equals(YES));
            }
            if(globalSetting.getName().equals("POST_PREMODERATION")){
                response.setPostPreModeration(globalSetting.getValue().equals(YES));
            }
            if(globalSetting.getName().equals("STATISTICS_IS_PUBLIC")){
                response.setStatisticIsPublic(globalSetting.getValue().equals(YES));
            }
        }
        return response;
    }
}
