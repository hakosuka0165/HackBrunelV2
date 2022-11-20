import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class SwiftBotContext
{
	public static ArrayList<SwiftBot> bots;
	public static ServerSocket server;
	
	public static void Initialize() 
	{
		try
		{
			server = new ServerSocket(4204);
		
			InetAddress addr = InetAddress.getLocalHost();
		
			System.out.println(String.format("Server started at port 4204 and ip %s", addr.getHostAddress().trim()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		bots = new ArrayList<SwiftBot>();
	}
	
	public static void ConnectLoop() 
	{
		Scanner console = new Scanner(System.in);

		while(true) 
		{
			try 
			{
				bots.add(new SwiftBot(server.accept()));				
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}

			System.out.print("Bot connected! Press Enter to continue or \"con\" to leave the connect phase... ");

			String input = console.nextLine();

			if(input.compareTo("con") == 0) 
			{
				//All robots are connected
				break;
			}
		}
		System.out.println("Bot connection finished");
	}
	
	public static void Shutdown() 
	{
		for(int i = 0; i < bots.size(); i++) 
		{
			bots.get(i).SendString("terminate");
		}
		try
		{
			server.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}