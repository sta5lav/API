package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoPortService;

@RestController
@RequestMapping(path = "/getPort")
public class InfoController {

    private final InfoPortService infoPortService;

    public InfoController(InfoPortService infoPortService) {
        this.infoPortService = infoPortService;
    }

    @GetMapping
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(infoPortService.getPort());
    }


}
