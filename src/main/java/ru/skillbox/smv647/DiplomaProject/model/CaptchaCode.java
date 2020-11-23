package ru.skillbox.smv647.DiplomaProject.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CAPTCHA_CODES")
public class CaptchaCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "secret_code", nullable = false, length = 255)
    private String secretCode;
}
