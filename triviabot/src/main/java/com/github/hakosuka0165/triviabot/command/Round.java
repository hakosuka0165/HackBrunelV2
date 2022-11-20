package com.github.hakosuka0165.triviabot.command;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.listener.message.MessageCreateListener;

import com.github.hakosuka0165.triviabot.Bibliotheque;
import com.github.hakosuka0165.triviabot.Main;
import com.github.hakosuka0165.triviabot.Question;

public class Round implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		// TODO Auto-generated method stub
		String command = event.getMessageContent();
		if(command.equalsIgnoreCase(Main.prefix + "roundstart")) {
			Question[] container = Bibliotheque.library;
			Question[] shuffledContainer = Bibliotheque.ShuffleQuestions(container);
			
			new MessageBuilder()
			.append("Beginning round!", MessageDecoration.BOLD)
			.send(event.getChannel());
			
			for (int i = shuffledContainer.length - 1; i > 0; --i) {
				new MessageBuilder()
				.setContent(shuffledContainer[i].q)
				.addComponents(
						ActionRow.of(Button.success("success", "True"),
								Button.danger("danger", "False")))
				.send(event.getChannel());
				
				event.getApi().addMessageComponentCreateListener(e -> {
					MessageComponentInteraction messageComponentInteraction = e.getMessageComponentInteraction();
					String customId = messageComponentInteraction.getCustomId();
					
					switch(customId) {
					case "success":
						messageComponentInteraction.createImmediateResponder()
						.setContent("Answer recorded for " + messageComponentInteraction.getUser() + "(" + customId + ")")
						.respond();
					break;
					case "danger":
						messageComponentInteraction.createImmediateResponder()
						.setContent("Answer recorded for " + messageComponentInteraction.getUser() + "(" + customId + ")")
						.respond();
					break;
					}
				});
			}
		}
	}

}
