import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            iniciarSistema();

            while (true) {

                mostrarMenu();

                String cmd = scanner.nextLine();

                if (cmd == null) continue;

                cmd = cmd.trim().toLowerCase();

                if (cmd.isEmpty()) {
                    System.out.println(Cores.VERMELHO + "[ERRO] Digite um comando válido." + Cores.RESET);
                    continue;
                }

                switch (cmd) {

                    case "w10":
                        System.out.println(Cores.ROXO + "[MODULE] Executando módulo de simulação...\n" + Cores.RESET);
                        NetworkSimulation.runSimulation();
                        break;

                    case "wifi":
                        System.out.println(Cores.CIANO + "[SCAN] Iniciando varredura Wi-Fi...\n" + Cores.RESET);
                        WifiScanner.scan();
                        break;

                    case "scan":
                        System.out.println(Cores.CIANO + "[SCAN] Iniciando varredura de rede local...\n" + Cores.RESET);
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
            System.out.println(Cores.VERMELHO + "[ERRO] Processo interrompido." + Cores.RESET);
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            System.out.println(Cores.VERMELHO + "[ERRO] Falha inesperada: " + e.getMessage() + Cores.RESET);

        } finally {
            scanner.close();
        }
    }

    private static void iniciarSistema() throws InterruptedException {
        exibirMensagem("Inicializando sistema...");
        Carregando.animar("Carregando módulos", 2000);
        exibirMensagem("Sistema pronto para uso.");
    }

    private static void mostrarMenu() {
        System.out.println(Cores.AZUL + "\n===== MENU =====" + Cores.RESET);
        System.out.println(Cores.BRANCO + "wifi  " + Cores.RESET + "= escanear redes Wi-Fi");
        System.out.println(Cores.BRANCO + "scan  " + Cores.RESET + "= escanear rede local");
        System.out.println(Cores.BRANCO + "w10   " + Cores.RESET + "= simulação de análise");
        System.out.println(Cores.BRANCO + "exit  " + Cores.RESET + "= sair");
        System.out.print(Cores.VERDE + ">> " + Cores.RESET);
    }

    private static void exibirMensagem(String mensagem) {
        System.out.println(Cores.VERDE + "[INFO] " + mensagem + Cores.RESET);
    }
}