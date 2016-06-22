package io.lyonix.phoenix.api.gui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GUIInventory {

	private List<GUIItem> items;
	private Inventory inventory;

	public GUIInventory(String name, int rows) {
		this.items = new ArrayList<GUIItem>();

		this.inventory = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', name));
	}

	public void add(GUIItem item) {
		if(this.items.contains(item)) {
			return;
		}

		this.items.add(item);
	}

	public void build() {
		for(GUIItem item : items) {
			inventory.setItem(item.getSlot(), item.getItem());
		}
	}

	@EventHandler
	public void onInventoryClickItem(InventoryClickEvent event) {
		for(GUIItem item : items) {
			if(item.getItem() == event.getCurrentItem()) {
				item.onClick(event);
				break;
			}
		}
	}

}
