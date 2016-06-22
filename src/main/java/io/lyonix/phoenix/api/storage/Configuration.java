package io.lyonix.phoenix.api.storage;

import io.lyonix.phoenix.api.Plugin;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Configuration {

	@Getter
	protected final String            name;
	@Getter
	private final   YamlConfiguration config;
	private final   File              file;
	protected       Plugin            plugin;

	public Configuration(Plugin plugin, String name) {
		this.plugin = plugin;
		this.name = name;

		this.file = new File(plugin.getDataFolder(), name + ".yml");

		if (!file.exists()) {
			file.getParentFile().mkdirs();

			try {
				file.createNewFile();
			} catch (IOException e) {
				plugin.getLog().log(Level.SEVERE, "Unable to create file: " + name + ".yml", e);
			}
		}

		this.config = YamlConfiguration.loadConfiguration(file);
	}

	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			plugin.getLog().log(Level.SEVERE, "Unable to save to file: " + name + ".yml", e);
		}
	}

	public void loadDefault(DefaultConfiguration defaultConfig) {
		boolean changes = false;

		for (String key : defaultConfig.getDefaultValues().keySet()) {
			if (config.isSet(key)) {
				continue;
			}

			config.set(key, defaultConfig.getDefaultValues().get(key));
			plugin.getLog().log(Level.INFO, "Loaded default value of " + key + " into " + name + ".yml");

			changes = true;
		}

		if (changes) {
			save();
		}
	}

}