package com.stschools.util;

import com.stschools.common.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.stschools.common.constants.SystemConstant.*;

public class FileControl {
    public static void saveFile(FileType fileType,
                                MultipartFile file) throws IOException {

        Path path = CURRENT_FOLDER.resolve(STATIC_PATH);

        switch (fileType){
            case IMAGE:
                path = path.resolve(IMAGE_PATH);
                break;
            case VIDEO:
                path = path.resolve(VIDEO_PATH);
                break;
            default:
                path = path.resolve(ANOTHER_PATH);
                break;
        }

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        Path filePath = path.resolve(Objects.requireNonNull(file.getOriginalFilename()));

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
