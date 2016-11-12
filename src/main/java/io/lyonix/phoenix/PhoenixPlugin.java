package io.lyonix.phoenix;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PhoenixPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		Bukkit.getLogger().info("[Phoenix] This plugin/library is property of Lyonix Services.");
	}
}