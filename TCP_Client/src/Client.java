import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сообщение для сервера");
        String clientMessage = scanner.next();
        System.out.println("Введите адрес сервера");
        String host = scanner.next();
        try {
            Socket socket = new Socket(host,15270);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 ) {
                writer.write(clientMessage);
                writer.flush();
                socket.shutdownOutput();
                String serverMessage = reader.readLine();
                System.out.println("Сервер передал время обращения: " + serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Соединение с сервером прекратилось. Попробуйте ещё раз.");
        }
    }
}
