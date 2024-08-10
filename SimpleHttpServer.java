import javax.xml.crypto.Data;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening to connection on port 8080...");
        while (true){
            try(Socket client = server.accept()){
                InputStreamReader isr = new InputStreamReader(client.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();
                while(!line.isEmpty()){
                    System.out.println(line);
                    line = reader.readLine();
                }

                Date date = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + date;
                client.getOutputStream()
                        .write(httpResponse.getBytes("UTF-8"));
            }

            
        }
    }
}
