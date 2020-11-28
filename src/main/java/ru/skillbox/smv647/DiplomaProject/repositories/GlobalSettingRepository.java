package ru.skillbox.smv647.DiplomaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.smv647.DiplomaProject.model.GlobalSetting;

public interface GlobalSettingRepository extends JpaRepository<GlobalSetting, Integer> {
}
