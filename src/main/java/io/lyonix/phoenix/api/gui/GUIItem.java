package io.lyonix.phoenix.api.gui;

import io.lyonix.phoenix.api.gui.event.ItemClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIItem {

	private int slot;

	private ItemStack item;

	private ItemClickEvent event;

	public ItemClickEvent getEvent() {
		return event;
	}

	public GUIItem(int slot, ItemStack item, ItemClickEvent event) {
		this.slot = slot;
		this.item = item;
		this.event = event;
	}

	public void onClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}

		this.event.run((Player) event.getWhoClicked());
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public int getSlot() {
		return slot;
	}
}