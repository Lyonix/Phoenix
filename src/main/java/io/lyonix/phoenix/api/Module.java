package io.lyonix.phoenix.api;

import io.lyonix.phoenix.api.command.Command;
import org.bukkit.event.Listener;
import java.util.HashSet;

public class Module {

	private String name;

	private String version;

	private String author;

	private String description;

	private HashSet<Listener> listeners;

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

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public HashSet<Listener> getListeners() {
		return listeners;
	}

	public HashSet<Command> getCommands() {
		return commands;
	}
}