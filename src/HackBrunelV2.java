import swiftbot.SwiftBotAPI;

public class HackBrunelV2
{
	public static void main(String args[]) 
	{
		SwiftBotAPI sb = new SwiftBotAPI();
		
		double distance = sb.useUltrasound();
		System.out.println("Distance from obstacle: " + distance + "cm");

		try 
		{
			sb.move(100, 100, 5000);
		}
		catch(IllegalArgumentException | InterruptedException e) 
		{
			e.printStackTrace();
		}

		sb.shutdown();
	}
}
