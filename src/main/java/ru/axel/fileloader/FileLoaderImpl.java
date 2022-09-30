package ru.axel.fileloader;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

/**
 * Вспомогательный класс для загрузки файлов из URL или адреса строкой.
 */
public final class FileLoaderImpl implements FileLoader {
    private static final Logger logger = LoggerFactory.getLogger(FileLoaderImpl.class);
    private final URL url;
    private final File file;

    public FileLoaderImpl(String path) throws MalformedURLException, URISyntaxException {
        logger.debug("Загрузка файла из строки маршрута: " + path);
        url = new URL(path);
        file = new File(url.toURI());
    }
    public FileLoaderImpl(@NotNull URL url) throws URISyntaxException {
        this.url = url;

        logger.debug("Загрузка файла из url: " + this.url);
        file = new File(this.url.toURI());
    }

    /**
     * Метод возвращает массив байтов файла
     * @return массив байтов файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public byte @NotNull [] getBytes() throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    /**
     * Метод возвращает список строк файла
     * @return список строк файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public List<String> getFileData() throws IOException {
        return Files.readAllLines(file.toPath());
    }

    /**
     * Получаем строку расширения файла
     * @return строка расширения файла или пустая строка
     */
    @Override
    public String getFileExtension() {
        var splitUrl = url.getPath().split("\\.");

        if (splitUrl.length > 0) {
            return splitUrl[splitUrl.length - 1];
        } else {
            return "";
        }
    }

    /**
     * Метод возвращает mime файла
     * @return mime файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public String getMineFile() throws IOException {
        return Files.probeContentType(file.toPath());
    }
}
