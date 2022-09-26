import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;
    private static String city = "???";
    private static String variant;

    public static boolean checkWord (String variantCity){
        String a = city.toLowerCase().substring(city.length() - 1);
        String b = String.valueOf(variantCity.toLowerCase().charAt(0));
        if (a.equals(b)){
            return true;
        }else
            return false;
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                {
                        out.println(city);
                        while (true) {
                            variant = in.readLine();
                            if (city.equals("???")) {
                                city = variant;
                                out.println("Текущий город - " + city + ". Назовите город на букву - " +
                                        city.substring(city.length() - 1));
                                continue;
                            } else if (checkWord(variant)) {
                                city = variant;
                                out.println("Отлично! текущий город - " + city + ". Назовите город на букву - " +
                                        city.substring(city.length() - 1));
                                continue;
                            } else
                                out.println("not OK");
                            continue;
                        }


                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
