import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class LogParser {

    private static final String LOGS_DIRECTORY = "C:\\Users\\AlexBartha\\Desktop\\logs\\logs\\Sat_Jan_28_10-00-19_CST_2023";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime startDate = UserInput.promptForDate(scanner, "Enter start date and time (yyyy-MM-dd HH:mm:ss): ", dateFormatter);
            LocalDateTime endDate = UserInput.promptForDate(scanner, "Enter end date and time (yyyy-MM-dd HH:mm:ss): ", dateFormatter);

            List<Path> logFiles = LogFileManager.getLogFiles(LOGS_DIRECTORY);
            List<String> filteredLogs = LogProcessor.processLogs(logFiles, startDate, endDate, dateFormatter);
            LogFileManager.saveLogs(filteredLogs, LOGS_DIRECTORY);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
