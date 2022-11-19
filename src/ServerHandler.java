import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ServerHandler
{
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	
	public ServerHandler(String ip)
	{
		try 
		{
			socket = new Socket(ip, 4204);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			System.out.println("Connected to " + ip + " @ port 4204");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String ReceiveString()
	{
		try
		{
			return in.readUTF();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void SendString(String data) 
	{
		try
		{
			out.writeUTF(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
