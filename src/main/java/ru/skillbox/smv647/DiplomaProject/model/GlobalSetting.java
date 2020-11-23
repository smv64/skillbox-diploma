package ru.skillbox.smv647.DiplomaProject.model;

import javax.persistence.*;

@Entity
@Table(name = "GLOBAL_SETTINGS")
public class GlobalSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "value", nullable = false, length = 255)
    private String value;
}
