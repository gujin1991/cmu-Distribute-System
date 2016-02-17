import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServerTCP {

    public static void main(String args[]) {
        Socket clientSocket = null;
        PrintWriter out = null;
        //out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
        BufferedReader reader = null;
        

        try {
            int serverPort = 7777; // the server port we are using
            
            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);
            
            /*
             * Block waiting for a new connection request from a client.
             * When the request is received, "accept" it, and the rest
             * the tcp protocol handshake will then take place, making 
             * the socket ready for reading and writing.
             */
            while(true) {
                clientSocket = listenSocket.accept();
            // If we get here, then we are now connected to a client.
            
            // Set up "in" to read from the client socket
                Scanner in;
                in = new Scanner(clientSocket.getInputStream());
                
                // Set up "out" to write to the client socket
                //PrintWriter out;
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
                
                /* 
                 * Forever,
                 *   read a line from the socket
                 *   print it to the console
                 *   echo it (i.e. write it) back to the client
                 */
                int sign = 0;
                String m;
                while (in.hasNext()) {
                    String data = in.nextLine();
                    if(sign == 0) {
                        sign = 1;
                        String filename = data.split("/")[1].split(" ")[0];
                        System.out.println(filename);
                        //File file = new File(filename);
                        try {
                            reader = new BufferedReader(new FileReader(filename));
                        } catch (IOException e) {
                            System.out.println("IO Exception:" + e.getMessage());
                            out.println("HTTP/1.1 404 OK\n\n");
                            out.println("File Not found");
                            out.println();
                            //out.println();
                            out.flush();
                            out.close();
                            //break;
                        }
                        String tempString = null;
                        int line = 1;
                        if(null != reader) {
                            out.println("HTTP/1.1 200 OK\n\n");
                            while((tempString = reader.readLine()) != null) {
                                line++;
                                out.println(tempString);
                                out.flush();

                            }
                            out.close();
                            reader.close();
                        }

                    }
                    System.out.println("Echoing: " + data);
                    //out.println(data);
                    //out.flush();
                }
        
            }
        // Handle exceptions
        } catch (IOException e) {
            System.out.println("IO Exception:" + "good"+ e.getMessage());
            //PrintWriter out;
            //out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            //String line = "404";
            //out.write(line.getBytes());
            
        // If quitting (typically by you sending quit signal) clean up sockets
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
    
}
