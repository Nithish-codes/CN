package ex3a;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoClient {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 6666);
			
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			Scanner input = new Scanner(System.in);
			
			String sendData = "", receiveData = "";
			
			while (true) {
				System.out.print("To Server: ");
				sendData = input.nextLine();
				dout.writeUTF(sendData);
				dout.flush();
				
				if (sendData.equals("stop")) {
					break;
				}
				
				receiveData = din.readUTF();
				System.out.println("Server says: " + receiveData);
				
				if (receiveData.equals("stop")) {
					break;
				}
			}
			
			din.close();
			dout.close();
			s.close();
			input.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
