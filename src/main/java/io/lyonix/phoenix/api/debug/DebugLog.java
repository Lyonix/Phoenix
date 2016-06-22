package io.lyonix.phoenix.api.debug;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class DebugLog {

	private String prefix;

	public DebugLog(String prefix) {
		this.prefix = prefix;
	}

	public void log(Level level, String message) {
		Bukkit.getLogger().log(level, prefix + message);
	}

	public void log(Level level, String message, Exception ex) {
		Bukkit.getLogger().log(level, prefix + message, ex);
	}

}