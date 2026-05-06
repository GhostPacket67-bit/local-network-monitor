import java.io.*;

public class CommandRunner {

    public static String run(String command) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("sh", "-c", command);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder output = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString().trim();
    }
}