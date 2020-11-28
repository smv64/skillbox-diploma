package ru.skillbox.smv647.DiplomaProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.auth.AuthCheckResponse;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public AuthCheckResponse authCheck(){
        return new AuthCheckResponse()
                .setResult(false);
    }
}
