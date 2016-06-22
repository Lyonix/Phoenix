package io.lyonix.phoenix.api.gui;

import io.lyonix.phoenix.api.gui.event.ItemClickEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIItem {

	@Getter
	private int slot;

	@Getter
	@Setter
	private ItemStack      item;

	@Getter
	private ItemClickEvent event;

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

}