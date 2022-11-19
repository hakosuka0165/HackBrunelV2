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
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

import com.github.hakosuka0165.triviabot.command.Round;

public class Main {
	public static String prefix = "!";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token = "MTA0MzUxMDU5NjE2MDcyOTE4MQ.GndWB4.BhgHGzg10agkQU3q9YhuSCRdzbb4FTfHocKDU4";
		
		DiscordApi api = new DiscordApiBuilder()
				.setToken(token)
				.addIntents(Intent.MESSAGE_CONTENT)
				.login().join();
		
		/*
		TextChannel channel = api.getTextChannelById("1043543184912961666").get();
		new MessageBuilder()
		.setContent("Test question")
		.addComponents(
				ActionRow.of(Button.success("success", "True"),
						Button.danger("danger", "False")))
		.send(channel);
		
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase("!ping")) {
				event.getChannel().sendMessage("Pong!");
			}
		});
		
		SlashCommand roundstart = SlashCommand.with("roundstart", "Begins a new round of Trivia Bot",
				Arrays.asList(
						SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "easy", "Start a game on easy difficulty"),
						SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "medium", "Start a game on medium difficulty"),
						SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "hard", "Start a game on hard difficulty")))
				.createGlobal(api)
				.join();
		
		api.addSlashCommandCreateListener(event -> {
			SlashCommandInteraction interaction = event.getSlashCommandInteraction();
			if (interaction.getFullCommandName().equals("roundstart hard")) {
				new MessageBuilder()
				.setContent("Click here");
			}
		});

		api.addMessageComponentCreateListener(event -> {
			event.getMessageComponentInteraction().acknowledge();
		});
		*/
		
		api.addMessageCreateListener(new Round());
		
		System.out.println("You can invite the bot using the following URL\n" + api.createBotInvite());
	}
}
