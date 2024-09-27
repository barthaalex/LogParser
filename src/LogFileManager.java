import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LogFileManager {

    public static List<Path> getLogFiles(String directory) {
        List<Path> logFiles = new ArrayList<>();

        try {
            File dir = new File(directory);
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (!isArchiveFile(fileName)) {
                        logFiles.add(file.toPath());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error while fetching log files: " + e.getMessage());
        }

        return logFiles;
    }

    public static void saveLogs(List<String> logs, String directory) {
        try {
            Path resultFile = Paths.get(directory, "results.log");
            Files.write(resultFile, logs);
            System.out.println("Logs saved to: " + resultFile.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving logs: " + e.getMessage());
        }
    }

    private static boolean isArchiveFile(String fileName) {
        return fileName.endsWith(".zip") || fileName.endsWith(".tar") || fileName.endsWith(".gz") || fileName.endsWith(".7z");
    }
}
