import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class LogProcessor {

    public static List<String> processLogs(List<Path> logFiles, LocalDateTime startDate, LocalDateTime endDate, DateTimeFormatter formatter) {
        List<String> filteredLogs = new ArrayList<>();

        for (Path logFile : logFiles) {
            try {
                List<String> logLines = Files.readAllLines(logFile);

                for (String line : logLines) {
                    LocalDateTime logDate = extractDateFromLog(line, formatter);
                    if (logDate != null && !logDate.isBefore(startDate) && !logDate.isAfter(endDate)) {
                        filteredLogs.add(line);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error reading log file: " + logFile.toString());
            }
        }

        return filteredLogs;
    }

    private static LocalDateTime extractDateFromLog(String log, DateTimeFormatter formatter) {
        try {
            if (log.startsWith("[")) {
                int endIndex = log.indexOf("]");
                if (endIndex != -1) {
                    String dateString = log.substring(1, endIndex);
                    return LocalDateTime.parse(dateString, formatter);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing date from log: " + e.getMessage());
        }
        return null;
    }
}
