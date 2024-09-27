import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInput {

    public static LocalDateTime promptForDate(Scanner scanner, String promptMessage, DateTimeFormatter formatter) {
        System.out.print(promptMessage);
        String input = scanner.nextLine();
        return LocalDateTime.parse(input, formatter);
    }
}
