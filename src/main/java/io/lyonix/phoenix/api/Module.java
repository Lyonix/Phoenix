package io.lyonix.phoenix.api;

import io.lyonix.phoenix.api.command.Command;
import lombok.Getter;
import org.bukkit.event.Listener;

import java.util.HashSet;

public class Module {

	@Getter
	private String name;

	@Getter
	private String version;

	@Getter
	private String author;

	@Getter
	private String description;

	@Getter
	private HashSet<Listener> listeners;

	@Getter
	private HashSet<Command> commands;

	protected Module(String name, String version, String author, String description) {
		this.name = name;
		this.version = version;
		this.author = author;
		this.description = description;

		this.listeners = new HashSet<Listener>();
		this.commands = new HashSet<Command>();
	}

	protected void addListener(Listener listener) {
		if (listeners.contains(listener)) {
			return;
		}

		listeners.add(listener);
	}

	protected void registerCommand(Command command) {
		if(commands.contains(command)) {
			return;
		}

		commands.add(command);
	}

	public void load() {
	}

	public void firstEnable() {
	}

	public void enable() {
	}

	public void disable() {
	}

}