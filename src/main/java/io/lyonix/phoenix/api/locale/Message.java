package io.lyonix.phoenix.api.locale;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Message {

	private String key;
	@Getter
	private String value;

	public Message(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String get() {
		return ChatColor.translateAlternateColorCodes('&', this.value);
	}

	public Message replace(String _key, String _value) {
		return new Message(key, this.value.replace(_key, _value));
	}

	public void send(Player player) {
		player.sendMessage(get());
	}

	public void send(CommandSender sender) {
		sender.sendMessage(ChatColor.stripColor(get()));
	}

	public void broadcast() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			send(player);
		}
	}

}