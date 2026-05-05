public class Derruba {

    public static void executar() throws InterruptedException {

        System.out.println("[MODULO] Iniciando módulo Derruba...\n");

        String[] etapas = {
                "Conectando ao alvo...",
                "Coletando pacotes...",
                "Analisando tráfego...",
                "Executando rotina...",
                "Finalizando..."
        };

        for (String etapa : etapas) {
            System.out.println("[PROCESSO] " + etapa);
            Thread.sleep(700);
        }

        System.out.println("\n[STATUS] Operação concluída (simulação).");
    }
}