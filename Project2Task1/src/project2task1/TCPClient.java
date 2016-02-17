import java.net.*;
import java.io.*;
//package project2task1.TEA;
public class TCPClient {
	public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			String userInfo = "";
			String key = "";
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please input your KEY."); 
			key += input.readLine();
			System.out.println("Please input your ID."); 
			userInfo += input.readLine();
			System.out.println("Please input your password.") ; 
			userInfo += " " + input.readLine();
			System.out.println("Please input your location.") ; 
			userInfo += " " + input.readLine();
			byte[] infoByte = userInfo.getBytes();
			byte[] result = new byte[4096];

			TEA tea = new TEA(key.getBytes());
			byte[] secret = tea.encrypt(infoByte);
			//String secret = new String();
			//System.out.println(secret.length);
			//System.out.println(secret) ; 
			//System.out.println(new String(tea.decrypt(tea.encrypt(infoByte)))) ; 


				
			out.write(secret);
			in.read(result);
			String returnMsg = new String(result);	    // read a line of data from the stream
			System.out.println(returnMsg);
			
			      	
			
			
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}
