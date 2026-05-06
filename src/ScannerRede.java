import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScannerRede {

    private static final int TIMEOUT = 150;
    private static final int THREADS = 50;

    public static void escanear() {

        try {
            String baseIP = obterBaseIP();

            if (baseIP == null) {
                System.out.println("[ERRO] Rede não detectada.");
                return;
            }

            System.out.println("[INFO] Iniciando varredura: " + baseIP + "0/24\n");

            AtomicInteger ativos = new AtomicInteger(0);
            ExecutorService pool = Executors.newFixedThreadPool(THREADS);

            for (int i = 1; i <= 254; i++) {
                final int host = i;

                pool.execute(() -> scanHost(baseIP + host, ativos));
            }

            pool.shutdown();
            pool.awaitTermination(2, TimeUnit.MINUTES);

            System.out.println("\n[RESULTADO] Dispositivos ativos: " + ativos.get());

        } catch (Exception e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private static void scanHost(String ip, AtomicInteger ativos) {
        try {
            InetAddress inet = InetAddress.getByName(ip);

            if (inet.isReachable(TIMEOUT)) {
                ativos.incrementAndGet();

                String msg = "[ATIVO] " + ip;
                System.out.println(msg);

                Logger.log(Logger.Level.INFO, "Host ativo detectado: " + ip);
            }

        } catch (Exception ignored) {}
    }

    private static String obterBaseIP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback()) continue;

                Enumeration<InetAddress> addrs = ni.getInetAddresses();

                while (addrs.hasMoreElements()) {
                    InetAddress addr = addrs.nextElement();

                    if (!addr.isLoopbackAddress() && addr.getHostAddress().contains(".")) {
                        String ip = addr.getHostAddress();
                        return ip.substring(0, ip.lastIndexOf(".") + 1);
                    }
                }
            }

        } catch (Exception ignored) {}

        return null;
    }
}