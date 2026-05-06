import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String FILE_NAME = "logs/resultado.log";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public enum Level {
        INFO,
        WARNING,
        ERROR
    }

    public static void log(Level level, String message) {
        try {
            ensureLogDirectory();

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String line = String.format("[%s] [%s] %s%n", timestamp, level, message);

            try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
                writer.write(line);
            }

        } catch (IOException e) {
            System.out.println("[LOGGER ERROR] Falha ao salvar log: " + e.getMessage());
        }
    }

    private static void ensureLogDirectory() {
        File file = new File("logs");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}