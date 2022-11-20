import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SwiftBot
{
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	
	public SwiftBot(Socket sckt)
	{
		socket = sckt;
		try
		{
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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
}