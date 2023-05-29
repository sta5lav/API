package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "/avatar")
public class AvatarController {

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    private final AvatarService avatarService;

    @GetMapping(path = "/fromBD/{id}")
    public ResponseEntity<byte[]> getAvatarOfIdStudent(@PathVariable() long id) {
        Avatar avatar = avatarService.findStudentAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }


    @GetMapping(path = "/{id}")
    public void getAvatarOfIdStudent(@PathVariable() long id,
                                     HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findStudentAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()
        ) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @PostMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatarOfIdStudent
            (@PathVariable long id,
             @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatarOfIdStudent(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getAllAvatarsForPage")
    public ResponseEntity<List<Avatar>> getListOfAvatar(@RequestParam("page") Integer pageNumber,
                                                        @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = avatarService.getListOfAvatar(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }


}
