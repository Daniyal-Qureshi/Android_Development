package com.example.mediaplayer_assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Songs {
    public static ArrayList<File> files;
    public static ArrayList<File> current;

    public static List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if (file.getName().endsWith(".mp3")) {
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

}
