package com.stschools.common.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemConstant {
    public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    public static final Path STATIC_PATH = Paths.get("src/main/resources/static");
    public static final Path IMAGE_PATH = Paths.get("images");
    public static final Path VIDEO_PATH = Paths.get("videos");
    public static final Path ANOTHER_PATH = Paths.get("another");
}
