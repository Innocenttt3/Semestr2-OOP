package com.example.kolos2;

import java.io.File;

public class FileLister {
    public static String listFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
            File[] files = folder.listFiles();
            return String.valueOf(files[0]);

    }
}
