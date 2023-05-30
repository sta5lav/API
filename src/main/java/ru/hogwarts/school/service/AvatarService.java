package ru.hogwarts.school.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${student.avatars.dir.path}")
    String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }


    public void uploadAvatarOfIdStudent(long id, MultipartFile avatar) throws IOException {
        Student student = studentService.getById(id).orElseThrow();
        Path filePath = Path.of(avatarsDir, id + "." + StringUtils.getFilenameExtension(avatar.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream in = avatar.getInputStream();
             OutputStream out = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(in, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(out, 1024)) {
            bis.transferTo(bos);
        }
        byte[] data = avatar.getBytes();
        Avatar av = new Avatar();
        av.setData(data);
        av.setFilePath(filePath.toString());
        av.setFileSize(data.length);
        av.setMediaType(avatar.getContentType());
        av.setStudent(student);
        avatarRepository.save(av);
    }

    public Avatar findStudentAvatar(long id) {
        return avatarRepository.findByStudent_Id(id).orElse(new Avatar());
    }

    public List<Avatar> getListOfAvatar(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();

    }
}
