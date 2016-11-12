package io.lyonix.phoenix.api.storage;

import java.util.HashMap;

public abstract class DefaultConfiguration {

	public HashMap<String, Object> getDefaultValues() {
		return defaultValues;
	}

	private HashMap<String, Object> defaultValues = new HashMap<>();

	public DefaultConfiguration() {
		insertValues();
	}

	public abstract void insertValues();

	protected void insert(String key, Object object) {
		defaultValues.put(key, object);
	}

}