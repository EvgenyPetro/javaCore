package ru.petrov;

import java.io.*;

public class Backup {
    public static void main(String[] args) throws IOException {
        File source = new File("/Users/petro/geekbrains/java/core/lesson5");
        File dist = new File("/Users/petro/geekbrains/java/core/backup");
        copyFilesAndDir(source, dist);
    }

    public static void copyFilesAndDir(File sourceDir, File destDir) throws IOException {

        destDir.mkdirs();
        for (String file : sourceDir.list()) {
            copyDirectory(new File(sourceDir, file), new File(destDir, file));
        }
    }


    public static void copyDirectory(File source, File dist) throws IOException {
        if (source.isDirectory()) {
            copyFilesAndDir(source, dist);
        } else {
            copyFile(source, dist);
        }

    }

    private static void copyFile(File sourceFile, File destinationFile)
            throws IOException {
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

}
