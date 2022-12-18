package ru.axel.fileloader;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Вспомогательный класс для загрузки файлов из URL или адреса строкой.
 */
public final class FileLoader implements IFileLoader {
    private final URL url;

    public FileLoader(String path) throws MalformedURLException {
        url = new URL(path);
    }
    public FileLoader(@NotNull URL path) {
        url = path;
    }

    /**
     * Метод возвращает массив байтов файла. Не требуется автозакрытие ресурса.
     * @return массив байтов файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public byte @NotNull [] getBytes() throws IOException, URISyntaxException {
        return Files.readAllBytes(Path.of(url.toURI()));
    }

    /**
     * Метод возвращает список строк файла. Не требуется автозакрытие ресурса.
     * @return список строк файла в кодировке UTF_8.
     * @throws IOException ошибка чтения файла
     */
    @Override
    public List<String> getFileData() throws IOException, URISyntaxException {
        return getFileData(StandardCharsets.UTF_8);
    }

    /**
     * Метод возвращает список строк файла. Не требуется автозакрытие ресурса.
     * @param charset кодировка.
     * @return список строк файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public List<String> getFileData(Charset charset) throws IOException, URISyntaxException {
        return Files.readAllLines(Path.of(url.toURI()), charset);
    }

    /**
     * Получаем строку расширения файла
     * @return строка расширения файла или пустая строка
     */
    @Override
    public String getFileExtension() {
        final String[] splitUrl = url.getPath().split("\\.");

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
    public String getMineFile() throws IOException, URISyntaxException {
        return Files.probeContentType(Path.of(url.toURI()));
    }
}
