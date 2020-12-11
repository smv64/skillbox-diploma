package ru.skillbox.smv647.DiplomaProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.smv647.DiplomaProject.model.GlobalSetting;
import ru.skillbox.smv647.DiplomaProject.repositories.GlobalSettingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GlobalSettingService {
    private final GlobalSettingRepository settingRepository;
    private final String YES = "YES";

    public Map<String, Boolean> getGlobalSettingsResponse(){
        List<GlobalSetting> globalSettingList = settingRepository.findAll();
        Map<String, Boolean> response = new HashMap<>();
        for(GlobalSetting globalSetting: globalSettingList){
            response.put(globalSetting.getCode(), globalSetting.getValue().equals(YES));
        }
        return response;
    }
}
