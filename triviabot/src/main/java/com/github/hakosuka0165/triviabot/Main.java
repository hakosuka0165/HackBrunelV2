package com.github.hakosuka0165.triviabot;

import java.util.Arrays;
import java.util.Optional;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;

import com.github.hakosuka0165.triviabot.command.Round;

public class Main {
	public static String prefix = "!";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token = "";
		
		DiscordApi api = new DiscordApiBuilder()
				.setToken(token)
				.addIntents(Intent.MESSAGE_CONTENT)
				.login().join();
		
		api.addMessageCreateListener(new Round());
		
		api.addMessageComponentCreateListener(event -> {
			event.getMessageComponentInteraction().acknowledge();
			System.out.println("Hello!");
		});
		
		System.out.println("You can invite the bot using the following URL\n" + api.createBotInvite());
	}
}
