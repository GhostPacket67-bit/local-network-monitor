import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ScannerRede {

    public static void escanear() {

        try {
            String ipBase = obterBaseIP();

            if (ipBase == null) {
                System.out.println("[ERRO] Não foi possível detectar a rede.");
                return;
            }

            AtomicInteger ativos = new AtomicInteger(0);
            ExecutorService pool = Executors.newFixedThreadPool(50);

            System.out.println("[INFO] Escaneando rede: " + ipBase + "0/24\n");

            for (int i = 1; i <= 254; i++) {
                final int hostFinal = i;

                pool.execute(() -> {
                    try {
                        String host = ipBase + hostFinal;
                        InetAddress inet = InetAddress.getByName(host);

                        if (inet.isReachable(200)) {
                            System.out.println("[ATIVO] " + host);
                            ativos.incrementAndGet();

                            Logger.salvar("Ativo: " + host);
                        }

                    } catch (Exception ignored) {}
                });
            }

            pool.shutdown();
            while (!pool.isTerminated()) {}

            System.out.println("\n[RESULTADO] Dispositivos ativos: " + ativos.get());

        } catch (Exception e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private static String obterBaseIP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback()) continue;

                Enumeration<InetAddress> enderecos = ni.getInetAddresses();

                while (enderecos.hasMoreElements()) {
                    InetAddress addr = enderecos.nextElement();

                    if (!addr.isLoopbackAddress() && addr.getHostAddress().contains(".")) {

                        String ip = addr.getHostAddress();

                        // Ex: 192.168.0.15 → 192.168.0.
                        return ip.substring(0, ip.lastIndexOf(".") + 1);
                    }
                }
            }

        } catch (Exception ignored) {}

        return null;
    }
}