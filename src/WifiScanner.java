public class WifiScanner {

    public static void scan() {
        try {
            System.out.println("[INFO] Escaneando Wi-Fi...\n");

            String result = CommandRunner.run("termux-wifi-scaninfo");

            if (result.isEmpty()) {
                System.out.println("[AVISO] Nenhuma rede encontrada ou permissão negada.");
                return;
            }

            System.out.println("[RESULTADO]\n" + result);

        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao escanear Wi-Fi: " + e.getMessage());
        }
    }
}