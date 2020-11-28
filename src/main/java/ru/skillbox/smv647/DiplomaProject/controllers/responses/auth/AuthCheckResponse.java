package ru.skillbox.smv647.DiplomaProject.controllers.responses.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthCheckResponse {
    private Boolean result;
}
