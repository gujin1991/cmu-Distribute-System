import java.net.*;
import java.io.*;
import java.util.*;
public class TCPServer {
	//static int count = 0;
	public static void main (String args[]) {
		try{
			String key = "";
			int count = 0;
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please input your KEY."); 
			key += input.readLine();
			//TEA tea = new TEA(key.getBytes());
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for spies to visit..."); 
			while(true) {
				Socket clientSocket = listenSocket.accept();
				count++;
				System.out.println(count);
				Connection c = new Connection(clientSocket, key, count);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	String key;
	int localcount;
	Map<String, String> userInfo;
	public Connection (Socket aClientSocket, String key, int count) {
		try {
			this.localcount = count;
			this.key = key;
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
			userInfo = new HashMap<String, String>();
			userInfo.put("jamesb", "jame");
			userInfo.put("joem", "joe");
			userInfo.put("mikem", "mike");
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			        
			//System.out.println(key);  
			      // an echo server
			TEA tea = new TEA(key.getBytes());
			byte[] data = new byte[4096];
			int count = in.read(data);
			//System.out.println(count + "good");
			byte[] secret = new byte[count];
			//String secret = null;
			
			for(int i = 0; i < count; i++) {
				
				//
				secret[i] = data[i];
			}
			
			//System.out.println(count);
			byte[] dataBytes = tea.decrypt(secret);
			String secretStr = new String(dataBytes);
			for(int i = 0; i < dataBytes.length; i++) {
				//System.out.println(secretStr.charAt(i) - 0);
				if(secretStr.charAt(i) - 0 >= 128) {
					System.out.println("Got visit " + localcount + " illegal symmetric key used. This may be an attack.");
					return;
				}		
			}
			
			String dataStr = new String(dataBytes);
			//System.out.println(dataStr);
			String[] userStr = dataStr.split(" ");
			//for(int i = 0; i < 2; i++) {
				if(!userInfo.containsKey(userStr[0])) {
					System.out.println("Got visit " + localcount + " from " + userStr[0] + ". Illegal Password attempt. This may be an attack.");
					out.write("No find".getBytes());
				} else if(!userInfo.get(userStr[0]).equals(userStr[1])) {
					System.out.println("Got visit " + localcount + " from " + userStr[0] + ". Illegal Password attempt. This may be an attack.");
					out.write("No find".getBytes());
				}else {
					System.out.println("Got visit " + localcount + " from " + userStr[0]);
					out.write("Got You!".getBytes());	
				}
			//}

			
			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
