import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import swiftbot.SwiftBotAPI;
import swiftbot.SwiftBotAPI.Underlight;

public class HackBrunelV2 {
	static SwiftBotAPI swiftBot;

	public static void main(String[] args) throws InterruptedException {
		try {
			swiftBot = new SwiftBotAPI();
		} catch (Exception e) {
			System.out.println("\nI2C disabled!");
			System.out.println("Run the following command:");
			System.out.println("sudo raspi-config nonint do_i2c 0\n");
			System.exit(0);
		}
		// The Discord Robot will send: true or false;

		System.out.println(
			".___________..______       __  ____    ____  __       ___      .______     ______   .___________.\r\n"
			+ "|           ||   _  \\     |  | \\   \\  /   / |  |     /   \\     |   _  \\   /  __  \\  |           |\r\n"
			+ "`---|  |----`|  |_)  |    |  |  \\   \\/   /  |  |    /  ^  \\    |  |_)  | |  |  |  | `---|  |----`\r\n"
			+ "    |  |     |      /     |  |   \\      /   |  |   /  /_\\  \\   |   _  <  |  |  |  |     |  |     \r\n"
			+ "    |  |     |  |\\  \\----.|  |    \\    /    |  |  /  _____  \\  |  |_)  | |  `--'  |     |  |     \r\n"
			+ "    |__|     | _| `._____||__|     \\__/     |__| /__/     \\__\\ |______/   \\______/      |__|     \r\n"
			+ "                                                                                                 "
				);
		
		if(args.length > 0) 
		{
			
			ServerHandler server = new ServerHandler(args[0]);
			String input;
			boolean runLoop = true;
			while (runLoop) {
				input = server.ReceiveString(); // input = "true"; input = "false";
				String[] inputArgs = input.split("\\s");
				switch (inputArgs[0]) {
				case "terminate":
					runLoop = false;
					break;
				case "move":
					int speed = Integer.valueOf(inputArgs[1]);
					moveSwiftbot(speed);
					break;
				case "spin":
					moveWheel();
					break;
				case "rlights":
					redLighting();
					break;
				case "glights":
					useUnderlighting();
					break;
				case "correct":
					useUnderlighting();
					moveSwiftbot(80);
					moveWheel();
					break;
				case "wrong":
					redLighting();
					break;
				default:
					System.out.println("Invalid command " + inputArgs[0]);
					break;
				}
			}
		}
		else 
		{
			System.out.println("Running offline mode");
			
			ArrayList<Question> questions = new ArrayList<Question>();
			
			Scanner console = new Scanner(System.in);

			while(true)
			{
				AddArrayToList(questions, Questions.easyQuestions);
				AddArrayToList(questions, Questions.mediumQuestions);
				AddArrayToList(questions, Questions.hardQuestions);
				
				Collections.shuffle(questions);
				
				while(questions.size() > 0) 
				{
					Question q = questions.get(0);
					
					System.out.println(q.q);
					
					String answer = console.nextLine();
					
					boolean a;
					
					if(answer.charAt(0) == 't') 
					{
						a = true;
					}
					else if(answer.charAt(0) == 'f') 
					{
						a = false;
					}
					else 
					{
						System.out.println("Invalid answer, type true or false...");
						continue;
					}
					
					if(a != q.a) 
					{
						redLighting();
						moveSwiftbot(-40);
					}
					else 
					{
						useUnderlighting();
						switch(q.d) 
						{
						case 0:
							moveSwiftbot(80);
							break;
						case 1:
							moveSwiftbot(80);
							break;
						case 2:
							moveSwiftbot(80);
							break;
						}
					}
					
					questions.remove(0);
				}
			}

		}
		/*
		 * input = "true";
		 * 
		 * if (input.compareTo("true") == 0) {
		 * 
		 * useUnderlighting(); int speed = 80; moveSwiftbot(speed); moveWheel();
		 * 
		 * } else { redLighting();
		 * 
		 * }
		 */

	}
	private static void AddArrayToList(ArrayList<Question> questionList, Question[] questionArray) 
	{
		for(int i = 0; i < questionArray.length; i++)
		{
			questionList.add(questionArray[i]);
		}
	}

	public static void moveSwiftbot(int speed) {
		int right_wheel_speed = speed, left_wheel_speed = speed, time_rob = 1000;
		try {
			swiftBot.move(right_wheel_speed, left_wheel_speed, time_rob);
		} catch (IllegalArgumentException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void moveWheel() throws InterruptedException {
		int leftVelocity;
		int rightVelocity;
		leftVelocity = 100;
		rightVelocity = 0;
		swiftBot.move(leftVelocity, rightVelocity, 2570);

	}

	public static void useUnderlighting() {
		try {
			int[] colour_1 = { 0, 0, 255 }, colour_2 = { 255, 0, 0 }, colour_3 = { 0, 255, 0 },
					colour_4 = { 0, 0, 255 }, colour_5 = { 255, 0, 0 }, colour_6 = { 0, 255, 0 },

					help_colour = { 0, 0, 0 };
			int i;

			for (i = 1; i <= 6; i++) {
				swiftBot.setUnderlight(Underlight.FRONT_LEFT, colour_1);
				swiftBot.setUnderlight(Underlight.FRONT_RIGHT, colour_2);
				swiftBot.setUnderlight(Underlight.MIDDLE_LEFT, colour_3);
				swiftBot.setUnderlight(Underlight.MIDDLE_RIGHT, colour_4);
				swiftBot.setUnderlight(Underlight.BACK_LEFT, colour_5);
				swiftBot.setUnderlight(Underlight.BACK_RIGHT, colour_6);
				swiftBot.updateUnderlights();
				Thread.sleep(500);
				help_colour[0] = colour_1[0];
				help_colour[1] = colour_1[1];
				help_colour[2] = colour_1[2];
				colour_1[0] = colour_2[0];
				colour_1[1] = colour_2[1];
				colour_1[2] = colour_2[2];
				colour_2[0] = colour_3[0];
				colour_2[1] = colour_3[1];
				colour_2[2] = colour_3[2];
				colour_3[0] = colour_4[0];
				colour_3[1] = colour_4[1];
				colour_3[2] = colour_4[2];
				colour_4[0] = colour_5[0];
				colour_4[1] = colour_5[1];
				colour_4[2] = colour_5[2];
				colour_5[0] = colour_6[0];
				colour_5[1] = colour_6[1];
				colour_5[2] = colour_6[2];
				colour_6[0] = help_colour[0];
				colour_6[1] = help_colour[1];
				colour_6[2] = help_colour[2];

			}
			swiftBot.disableUnderlights();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void redLighting() {
		try {
			// redlight
			swiftBot.fillUnderlights(255, 0, 0);
			Thread.sleep(1000);
			swiftBot.disableUnderlights();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}