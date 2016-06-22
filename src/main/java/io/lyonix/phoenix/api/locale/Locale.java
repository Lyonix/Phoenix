package io.lyonix.phoenix.api.locale;

import io.lyonix.phoenix.api.Plugin;
import io.lyonix.phoenix.api.storage.Configuration;

public class Locale extends Configuration {

	public Locale(Plugin plugin) {
		super(plugin, "messages");
	}

	public Locale(Plugin plugin, String name) {
		super(plugin, name);
	}

	public Message getMessage(String key) {
		return new Message(key, getConfig().getString(key, ""));
	}

}