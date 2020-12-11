package ru.skillbox.smv647.DiplomaProject.controllers.responses;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

@Data
@Accessors(chain = true)
public class CalendarResponse {
    private Set<Integer> years = new HashSet<>();
    private Map<String, Integer> posts = new HashMap<>();
}
