import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ScannerRede {

    public static void escanear() {

        String ipBase = "192.168.0.";
        AtomicInteger ativos = new AtomicInteger(0);

        ExecutorService pool = Executors.newFixedThreadPool(50);

        System.out.println("[INFO] Escaneando rede (modo rápido)...\n");

        for (int i = 1; i <= 254; i++) {
            final int hostFinal = i;

            pool.execute(() -> {
                try {
                    String host = ipBase + hostFinal;
                    InetAddress inet = InetAddress.getByName(host);

                    if (inet.isReachable(200)) {
                        System.out.println(Cores.VERDE + "[ATIVO] " + host + Cores.RESET);
                        ativos.incrementAndGet();
                    }

                } catch (Exception ignored) {}
            });
        }

        pool.shutdown();

        while (!pool.isTerminated()) {}

        System.out.println("\n[RESULTADO] Dispositivos ativos: " + ativos.get());
    }
}