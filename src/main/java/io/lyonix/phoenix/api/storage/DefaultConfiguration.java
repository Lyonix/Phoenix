package io.lyonix.phoenix.api.storage;

import lombok.Getter;

import java.util.HashMap;

public abstract class DefaultConfiguration {

	@Getter
	private HashMap<String, Object> defaultValues = new HashMap<>();

	public DefaultConfiguration() {
		insertValues();
	}

	public abstract void insertValues();

	protected void insert(String key, Object object) {
		defaultValues.put(key, object);
	}

}