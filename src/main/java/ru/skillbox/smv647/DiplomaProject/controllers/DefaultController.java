package ru.skillbox.smv647.DiplomaProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.skillbox.smv647.DiplomaProject.controllers.responses.InitResponse;

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexHtml() {
        return "index";
    }
}
