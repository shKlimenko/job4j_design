package socket;

import logs.UsageLog4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                StringBuilder msg = new StringBuilder();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        msg.append(str);
                    }
                    if (msg.toString().contains("msg=Exit")) {
                        System.out.println("Closing server");
                        server.close();
                    }
                    String answer = msg.toString().contains("msg=Hello") ? "Hello" : "What";

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("ServerSocket throws IOException", e);
        }
    }
}