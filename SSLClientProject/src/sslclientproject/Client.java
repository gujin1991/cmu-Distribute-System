/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sslclientproject;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.net.*;
import javax.net.ssl.SSLSocketFactory;
/**
 *
 * @author Jin
 */
public class Client {
    
    public static void main(String args[]) {
        
        int port = 6502;
        try {
            // tell the system who we trust
            System.setProperty("javax.net.ssl.trustStore","myCool.truststore");
            // get an SSLSocketFactory
            SocketFactory sf = SSLSocketFactory.getDefault();
            
            // an SSLSocket "is a" Socket
            Socket s = sf.createSocket("localhost",6505);
            
            
            BufferedWriter out = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                                           s.getOutputStream()));
            BufferedReader in = new 
            BufferedReader(
                           new InputStreamReader(
                                                 s.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            
            
            String answer = in.readLine();               
            System.out.println(answer);
            String username = scanner.nextLine();
            out.write(username + "\n");
            out.flush();
            answer = in.readLine();               
            System.out.println(answer);
            String password = scanner.nextLine();
            out.write(password + "\n");
            out.flush();
            answer = in.readLine();               
            System.out.println(answer);
            
            out.close(); 
            in.close();
            //while(true);
        }
        catch(Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
} 
