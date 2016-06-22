package io.lyonix.phoenix.api;

import io.lyonix.phoenix.api.command.Command;
import io.lyonix.phoenix.api.debug.DebugLog;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.logging.Level;

public class Plugin extends JavaPlugin {

    @Getter
    private HashSet<Module> modules = new HashSet<Module>();

    private boolean first = false;

    private DebugLog log;

    private CommandMap commandMap;

    @Override
    public void onLoad() {
        load();

        for (Module module : modules) {
            module.load();
        }
    }

    @Override
    public void onEnable() {
        if (!first) {
            firstEnable();

            for (Module module : modules) {
                module.firstEnable();
            }

            first = true;
        }

        enable();

        for (Module module : modules) {
            module.enable();

            for (Listener listener : module.getListeners()) {
                Bukkit.getPluginManager().registerEvents(listener, this);
            }

            commandMap = getCommandMap();

            for (Command command : module.getCommands()) {
                commandMap.register(command.getName(), command.new CommandExecutor());
            }
        }
    }

    @Override
    public void onDisable() {
        for (Module module : modules) {
            module.disable();
        }

        disable();
    }

    public void load() {
    }

    public void firstEnable() {
    }

    public void enable() {
    }

    public void disable() {
    }

    protected void addModule(Module module) {
        if (this.modules.contains(module)) {
            return;
        }

        this.modules.add(module);
    }

    public DebugLog getLog() {
        return log == null ? new DebugLog("[" + this.getName() + "] ") : log;
    }

    private final CommandMap getCommandMap() {
        if (commandMap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                commandMap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (Exception e) {
                getLog().log(Level.SEVERE, "Unable to register commands", e);
            }
        } else if (commandMap != null) {
            return commandMap;
        }
        
        return getCommandMap();
    }

}