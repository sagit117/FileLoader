package ru.axel;

import ru.axel.fileloader.FileLoader;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        var url = Main.class.getResource("/templates/index.html");

        assert url != null;
        var file = new FileLoader(url);

        System.out.println("Mime: " + file.getMineFile());
    }
}