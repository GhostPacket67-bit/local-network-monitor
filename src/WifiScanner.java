import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WifiScanner {

    public static void escanearWifi() {
        try {
            System.out.println("[INFO] Procurando redes Wi-Fi...\n");

            ProcessBuilder pb = new ProcessBuilder("sh", "-c", "termux-wifi-scaninfo");
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String linha;

            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }

        } catch (Exception e) {
            System.out.println("[ERRO] " + e.getMessage());
            System.out.println("[DICA] Instale: pkg install termux-api");
        }
    }
}