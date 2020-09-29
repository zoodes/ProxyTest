import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        //this is proxy bypassing
        final String proxyUser = System.getProperty("http.proxyUser");
        final String proxyPassword = System.getProperty("http.proxyPassword");

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

        //main code starts here
        try {

            URL url = new URL("https://google.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
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
