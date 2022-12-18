package ru.axel.fileloader;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

public interface IFileLoader {
    /**
     * Метод возвращает массив байтов файла. Не требуется автозакрытие ресурса.
     * @return массив байтов файла
     * @throws IOException ошибка чтения файла
     */
    byte @NotNull [] getBytes() throws IOException, URISyntaxException;

    /**
     * Метод возвращает список строк файла. Не требуется автозакрытие ресурса.
     * @return список строк файла в кодировке UTF_8.
     * @throws IOException ошибка чтения файла
     */
    List<String> getFileData() throws IOException, URISyntaxException;

    /**
     * Метод возвращает список строк файла. Не требуется автозакрытие ресурса.
     * @param charset кодировка.
     * @return список строк файла
     * @throws IOException ошибка чтения файла
     */
    List<String> getFileData(Charset charset) throws IOException, URISyntaxException;

    /**
     * Получаем строку расширения файла
     * @return строка расширения файла или пустая строка
     */
    String getFileExtension();

    /**
     * Метод возвращает mime файла
     * @return mime файла
     * @throws IOException ошибка чтения файла
     */
    String getMineFile() throws IOException, URISyntaxException;
}
