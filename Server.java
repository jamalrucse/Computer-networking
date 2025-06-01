import java.io.*;
import java.net.*;
import java.time.LocalDate; 
import java.time.LocalTime; 
public class Server { 
public static void main(String[] args) { 
try (ServerSocket serverSocket = new ServerSocket(12345)) { 
System.out.println("Server is running and waiting for clients..."); 
while (true) { 
Socket clientSocket = serverSocket.accept(); 
System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress()); 
new Thread(() -> handleClient(clientSocket)).start(); 
} 
} catch (IOException e) { 
System.err.println("Error in server: " + e.getMessage()); 
} 
} 
private static void handleClient(Socket clientSocket) { 
try ( 
BufferedReader in = new BufferedReader(new 
InputStreamReader(clientSocket.getInputStream())); 
PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true) 
        ) { 
            String clientMessage; 
            while ((clientMessage = in.readLine()) != null) { 
                System.out.println("Received from client: " + clientMessage); 
                 
                switch (clientMessage) { 
                    case "Hi": 
                        out.println("Hello"); 
                        break; 
                    case "Date": 
                        out.println(LocalDate.now()); 
                        break; 
                    case "Time": 
                        out.println(LocalTime.now()); 
                        break; 
                    case "IP": 
                        out.println(clientSocket.getInetAddress().getHostAddress()); 
                        break; 
                    default: 
                        out.println("Invalid Command"); 
                } 
            } 
        } catch (IOException e) { 
            System.err.println("Error handling client: " + e.getMessage()); 
        } finally { 
            try { 
                clientSocket.close(); 
            } catch (IOException e) { 
                System.err.println("Error closing client socket: " + e.getMessage()); 
            } 
        } 
    } 
} 