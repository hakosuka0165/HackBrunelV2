package com.github.hakosuka0165.triviabot.command;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import com.github.hakosuka0165.triviabot.Main;

public class Round implements MessageCreateListener {
	private static int difficulty; // Difficulty ranges from 1 (Easy) to 3 (Hard)
	
	
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		// TODO Auto-generated method stub
		String content = event.getMessageContent();
		if(content.substring(0, 11).equalsIgnoreCase(Main.prefix + "roundstart")) {
			new MessageBuilder()
			.setContent("Looks like it worked " + content.substring(12))
			.send(event.getChannel());
			
			new MessageBuilder()
			.addComponents(
					ActionRow.of(Button.success("success", "True"),
							Button.danger("danger", "False")))
			.send(event.getChannel());
		}
	}

}
