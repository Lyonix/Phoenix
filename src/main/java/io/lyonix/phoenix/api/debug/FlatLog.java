package io.lyonix.phoenix.api.debug;

import io.lyonix.phoenix.api.Plugin;
import org.bukkit.Location;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class FlatLog {

	private Plugin plugin;
	private File   file;

	public FlatLog(Plugin plugin, String name) {
		this.plugin = plugin;
		this.file = new File(plugin.getDataFolder(), name + ".txt");

		if (!file.exists()) {
			file.getParentFile().mkdirs();

			try {
				file.createNewFile();
			} catch (IOException e) {
				plugin.getLog().log(Level.SEVERE, "Unable to create file: " + name + ".txt", e);
			}
		}
	}

	public void writeLine(String line) {
		PrintStream fileStream = null;

		try {
			fileStream = new PrintStream(new FileOutputStream(file, true));
		} catch (FileNotFoundException e) {
			plugin.getLog().log(Level.SEVERE, "Unable to write to file " + file.getName(), e);
			return;
		}

		fileStream.println("[" + getTime() + "] " + line);

		fileStream.close();
	}

	private String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		return sdf.format(new Date());
	}

	public String getLocation(Location location) {
		return location.getWorld().getName() + " @ " +
		       location.getBlockX() + ", " +
		       location.getBlockY() + ", " +
		       location.getBlockZ();
	}

}