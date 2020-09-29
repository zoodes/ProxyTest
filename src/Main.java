import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        //this is proxy bypassing
        final String proxyHost = System.getProperty("http.proxyHost");
        final int proxyPort = Integer.parseInt(System.getProperty("http.proxyPort"));
        final String proxyUser = System.getProperty("http.proxyUser");
        final String proxyPassword = System.getProperty("http.proxyPassword");

        try {
            URL url = new URL("http://google.com");
            InetSocketAddress proxyLocation = new InetSocketAddress(proxyHost, proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyLocation);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(proxy);

            if (proxyUser != null && proxyPassword != null) {
                Authenticator.setDefault(
                    new Authenticator() {
                        @Override
                        public PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                proxyUser, proxyPassword.toCharArray()
                            );
                        }
                    }
                );
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
