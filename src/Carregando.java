public class Carregando {

    public static void animar() throws InterruptedException {

        String[] animacao = {"|", "/", "-", "\\"};

        System.out.print("[LOADING] ");

        for (int i = 0; i < 20; i++) {
            System.out.print("\r[LOADING] " + animacao[i % animacao.length]);
            Thread.sleep(120);
        }

        System.out.print("\r[LOADING] Concluído!\n");
    }
}