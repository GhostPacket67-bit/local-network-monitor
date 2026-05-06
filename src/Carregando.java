public class Carregando {

    private static final String[] FRAMES = {"|", "/", "-", "\\"};

    public static void animar(String mensagem, int duracaoMs) {

        System.out.print("[LOADING] " + mensagem + " ");

        long inicio = System.currentTimeMillis();
        int i = 0;

        try {
            while (System.currentTimeMillis() - inicio < duracaoMs) {
                System.out.print("\r[LOADING] " + mensagem + " " + FRAMES[i % FRAMES.length]);
                Thread.sleep(120);
                i++;
            }

            System.out.print("\r[LOADING] " + mensagem + " Concluído!\n");

        } catch (InterruptedException e) {
            System.out.print("\r[LOADING] " + mensagem + " Interrompido!\n");
            Thread.currentThread().interrupt();
        }
    }
}