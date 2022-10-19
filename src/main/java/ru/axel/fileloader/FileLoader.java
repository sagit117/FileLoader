package ru.axel.fileloader;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * Вспомогательный класс для загрузки файлов из URL или адреса строкой.
 */
public final class FileLoader implements IFileLoader {
    private final URL url;
    private final File file;

    public FileLoader(String path) throws MalformedURLException, URISyntaxException {
        url = new URL(path);
        file = new File(url.toURI());
    }
    public FileLoader(@NotNull URL path) throws URISyntaxException {
        url = path;
        file = new File(this.url.toURI());
    }

    /**
     * Метод возвращает массив байтов файла. Не требуется автозакрытие ресурса.
     * @return массив байтов файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public byte @NotNull [] getBytes() throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    /**
     * Метод возвращает список строк файла. Не требуется автозакрытие ресурса.
     * @return список строк файла в кодировке UTF_8.
     * @throws IOException ошибка чтения файла
     */
    @Override
    public List<String> getFileData() throws IOException {
        return getFileData(StandardCharsets.UTF_8);
    }

    /**
     * Метод возвращает список строк файла. Не требуется автозакрытие ресурса.
     * @param charset кодировка.
     * @return список строк файла
     * @throws IOException ошибка чтения файла
     */
    @Override
    public List<String> getFileData(Charset charset) throws IOException {
        return Files.readAllLines(file.toPath(), charset);
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
    public String getMineFile() throws IOException {
        return Files.probeContentType(file.toPath());
    }
}
