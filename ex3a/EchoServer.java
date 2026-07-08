package ex3a;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(6666);
			
			while (true) {
				Socket s = ss.accept();
				
				DataInputStream din = new DataInputStream(s.getInputStream());
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				
				Scanner input = new Scanner(System.in);
				String sendData = "", receiveData = "";
				
				while (true){
					receiveData = din.readUTF();
					System.out.println("Client Says: " + receiveData);
					
					if (receiveData.equals("stop")) {
						break;
					}
					
					System.out.print("To client: ");
					sendData = input.nextLine();
					dout.writeUTF(sendData);
					dout.flush();
					
					if (sendData.equals("stop")) {
						break;
					}
				}
				
				din.close();
				dout.close();
				s.close();
				ss.close();
				input.close();
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
