package ds.gae.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ds.gae.entities.Quote;
import ds.gae.view.JSPSite;


public class ReportErrorServlet implements Runnable{
	
	// verbose mode
	static final boolean verbose = true;
	
	// Client Connection via Socket Class
	private static Socket connect;
	
	public ReportErrorServlet(Socket c) {
		connect = c;
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket serverConnect = new ServerSocket(9090, 0, InetAddress.getLoopbackAddress());
			System.out.println("Server started.\nListening for connections on port : " + " ...\n");
			
			// we listen until user halts server execution
			while (true) {
				ReportErrorServlet myServer = new ReportErrorServlet(serverConnect.accept());
				
				if (verbose) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}
				
				
	            OutputStream outputFromServer = connect.getOutputStream();

	            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

	            serverPrintOut.println("A qoute failed to confirm, so all qoutes failled to confirm.");
				
				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
		}
		
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
    }
	
    public static void connectToServer() {
        //Try connect to the server on an unused port eg 9991. A successful connection will return a socket
        try(ServerSocket serverSocket = new ServerSocket(9991, 0, InetAddress.getLoopbackAddress());) {
            Socket connectionSocket = serverSocket.accept();

            //Create Input&Outputstreams for the connection
            InputStream inputToServer = connectionSocket.getInputStream();
            OutputStream outputFromServer = connectionSocket.getOutputStream();

            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

            serverPrintOut.println("A qoute failed to confirm, so all qoutes failled to confirm.");

//            //Have the server take input from the client and echo it back
//            //This should be placed in a loop that listens for a terminator text e.g. bye
//            boolean done = false;
//
//            while(!done && scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                serverPrintOut.println("Echo from <Your Name Here> Server: " + line);
//
//                if(line.toLowerCase().trim().equals("peace")) {
//                    done = true;
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
