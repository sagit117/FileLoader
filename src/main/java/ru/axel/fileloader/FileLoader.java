package ru.axel.fileloader;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public interface FileLoader {
    byte @NotNull [] getBytes() throws IOException;

    List<String> getFileData() throws IOException;

    String getFileExtension();

    String getMineFile() throws IOException;
}
