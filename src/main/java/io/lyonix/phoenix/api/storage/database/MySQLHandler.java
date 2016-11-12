package io.lyonix.phoenix.api.storage.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.lyonix.phoenix.api.Plugin;
import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQLHandler {

	private final Plugin           plugin;
	private final HikariDataSource dataSource;

	private String table;
	private String database;

	public MySQLHandler(Plugin plugin, String host, int port, String username, String password, String database, String table) {
		this.plugin = plugin;
		this.table = table;
		this.database = database;

		HikariConfig config = new HikariDataSource();

		config.setJdbcUrl("jdbc:mysql://%host%:%port%/%database%"
				                  .replace("%host%", host)
				                  .replace("%port%", String.valueOf(port))
				                  .replace("%database%", database));
		config.setUsername(username);
		config.setPassword(password);

		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	}

	public static MySQLHandler createHandlerFromConfigurationSection(Plugin plugin, ConfigurationSection configSection) {
		String host     = configSection.getString("host", "localhost");
		int    port     = configSection.getInt("port", 3306);
		String username = configSection.getString("username", "root");
		String password = configSection.getString("password", "");
		String database = configSection.getString("database", "minecraft");
		String table    = configSection.getString("table", "friends");

		return new MySQLHandler(plugin, host, port, username, password, database, table);
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			plugin.getLog().log(Level.SEVERE, "Possible MySQL connection leak detected - no connections are available.", e);
		}

		return null;
	}

	public boolean isConnected() {
		try (Connection connection = dataSource.getConnection()) {
			// Do nothing
		} catch (SQLException e) {
			return false;
		}

		return true;
	}

	public void shutdown() {
		dataSource.close();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDatabase() {
		return database;
	}
}