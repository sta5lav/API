package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class InfoPortServiceTest implements InfoPortService {

    @Value("443")
    private Integer port;

    @Override
    public Integer getPort() {
        return port;
    }
}
