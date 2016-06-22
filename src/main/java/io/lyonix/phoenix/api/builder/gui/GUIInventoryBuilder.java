package io.lyonix.phoenix.api.builder.gui;

import io.lyonix.phoenix.api.gui.GUIInventory;

public class GUIInventoryBuilder {

	private String name;
	private int    rows;

	public GUIInventoryBuilder() {
		this.name = "";
		this.rows = 1;
	}

	public GUIInventoryBuilder name(String name) {
		this.name = name;

		return this;
	}

	public GUIInventoryBuilder rows(int rows) {
		this.rows = rows;

		return this;
	}

	public GUIInventory build() {
		return new GUIInventory(name, rows);
	}

}