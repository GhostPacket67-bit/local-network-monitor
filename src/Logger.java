import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public static void salvar(String texto) {
        try (FileWriter writer = new FileWriter("resultado.txt", true)) {
            writer.write(texto + "\n");
        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao salvar arquivo");
        }
    }
}