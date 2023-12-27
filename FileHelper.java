package homeWork20;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileHelper {

    @SuppressWarnings("BusyWait")
    public static boolean waitUntilFileIsDownloaded(String filePath, String fileName, int timeoutSec) {
        File file = new File(filePath, fileName);
        int waited = 0;
        while (!file.exists() && waited < timeoutSec) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            waited++;
        }
        return file.exists();
    }

    public static boolean isExtensionCorrect(String filePath, String fileName, String ext) {
        File file = new File(filePath + fileName);
        String name = file.getName();
        return name.endsWith(ext);
    }

    public static List<String> readFileRows(String filePath, String fileName) {
        try {
            return Files.readAllLines(Paths.get(filePath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getFileHeaders(List<String> rows) {
        return Arrays.asList(rows.get(0).split(","));
    }
}
