 
import java.io.*; 
import java.net.*; 
 
public class Client { 
    public static void main(String[] args) { 
        try (Socket socket = new Socket("localhost", 12345)) { 
            System.out.println("Connected to the server."); 
             
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); 
             
            String userInput; 
            while (true) { 
                System.out.print("PC1 (Client): "); 
                userInput = console.readLine(); 
                 
                if ("exit".equalsIgnoreCase(userInput)) { 
                    System.out.println("Closing connection..."); 
                    break; 
                } 
                 
                out.println(userInput); 
                String response = in.readLine(); 
                System.out.println("PC2(Server)): " + response); 
            } 
        } catch (IOException e) { 
            System.err.println("Error in client: " + e.getMessage()); 
        } 
    } 
} 
