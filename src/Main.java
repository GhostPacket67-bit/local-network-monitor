import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            iniciarSistema();

            while (true) {
                mostrarMenu();

                String cmd = scanner.nextLine().trim().toLowerCase();

                switch (cmd) {
                    case "w10":
                        System.out.println(Cores.ROXO + "[MODULO] Executando Derruba...\n" + Cores.RESET);
                        Derruba.executar();
                        break;

                    case "wifi":
                        System.out.println(Cores.CIANO + "[SCAN] Wi-Fi iniciado...\n" + Cores.RESET);
                        WifiScanner.escanearWifi();
                        break;

                    case "scan":
                        System.out.println(Cores.CIANO + "[SCAN] Rede local iniciada...\n" + Cores.RESET);
                        ScannerRede.escanear();
                        break;

                    case "exit":
                        System.out.println(Cores.AMARELO + "[INFO] Encerrando sistema..." + Cores.RESET);
                        return;

                    default:
                        System.out.println(Cores.VERMELHO + "[ERRO] Comando desconhecido." + Cores.RESET);
                }
            }

        } catch (InterruptedException e) {
            System.err.println(Cores.VERMELHO + "[ERRO] Processo interrompido: " + e.getMessage() + Cores.RESET);
            Thread.currentThread().interrupt();
        } finally {
            scanner.close();
        }
    }

    private static void iniciarSistema() throws InterruptedException {
        exibirMensagem("Inicializando sistema...");
        Carregando.animar();
        exibirMensagem("Sistema carregado com sucesso.");
    }

    private static void mostrarMenu() {
        System.out.println(Cores.AZUL + "\n===== MENU =====" + Cores.RESET);
        System.out.println(Cores.BRANCO + "wifi  " + Cores.RESET + "= escanear redes Wi-Fi");
        System.out.println(Cores.BRANCO + "scan  " + Cores.RESET + "= escanear dispositivos na rede");
        System.out.println(Cores.BRANCO + "w10   " + Cores.RESET + "= executar módulo Derruba");
        System.out.println(Cores.BRANCO + "exit  " + Cores.RESET + "= sair");
        System.out.print(Cores.VERDE + ">> " + Cores.RESET);
    }

    private static void exibirMensagem(String mensagem) {
        System.out.println(Cores.VERDE + "[INFO] " + mensagem + Cores.RESET);
    }
}