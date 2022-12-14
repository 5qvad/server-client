import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8080;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {

        //Создаём сокет клиента
        try (Socket clientSocket = new Socket(HOST, PORT)) {
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            {
                System.out.println(in.readLine());
                //Бесконечный цикл для общения с сервером
                while (true) {
                    out.println(scanner.nextLine());
                    System.out.println(in.readLine());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
