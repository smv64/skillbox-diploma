package ru.skillbox.smv647.DiplomaProject.controllers.responses;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class InitResponse {
    @Value("${vars.app.title}")
    private String title;
    @Value("${vars.app.subtitle}")
    private String subtitle;
    @Value("${vars.app.phone}")
    private String phone;
    @Value("${vars.app.email}")
    private String email;
    @Value("${vars.app.copyright}")
    private String copyright;
    @Value("${vars.app.copyrightFrom}")
    private String copyrightFrom;
}
