import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(15270);
            while (true) {
                new Server().serverWork(serverSocket.accept());
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void serverWork(Socket socketClient) {
        try (Socket socket = socketClient;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                String clientMessage = reader.readLine();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                System.out.println("Сервер принял сообщение от клиента. Время получения сообщения: " +
                        simpleDateFormat.format(date) + ". Содержание сообщения: " +
                        clientMessage + ". Адрес клиента: " +
                        socket.getInetAddress().getHostAddress());
                socket.shutdownInput();
                writer.write(simpleDateFormat.format(date));
             } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
        }
}
