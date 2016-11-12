package io.lyonix.phoenix.api.command;

import io.lyonix.phoenix.api.Module;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Command {

    private Module module;

    
    private String name;

    private String description;

    
    private String usage;


    private int minArgs;

    private String permission;

    
    private boolean playerOnly;

    
    private String permissionDeniedMessage;

    
    private String playerOnlyMessage;

    
    private String notEnoughArgsMessage;

    
    private CommandAlgorithm algorithm;

    public Command(Module module, String name, String description, String usage, int minArgs, String permission, boolean playerOnly, String permissionDeniedMessage, String playerOnlyMessage, String notEnoughArgsMessage, CommandAlgorithm algorithm) {
        this.module = module;
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.minArgs = minArgs;
        this.permission = permission;
        this.playerOnly = playerOnly;
        this.permissionDeniedMessage = permissionDeniedMessage;
        this.playerOnlyMessage = playerOnlyMessage;
        this.notEnoughArgsMessage = notEnoughArgsMessage;
        this.algorithm = algorithm;
    }

    public class CommandExecutor extends org.bukkit.command.Command {

        public CommandExecutor() {
            super(Command.this.name, Command.this.description, Command.this.usage, new ArrayList<String>());
        }

        @Override
        public boolean execute(CommandSender commandSender, String label, String[] args) {
            if (!(commandSender instanceof Player) && playerOnly) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerOnlyMessage));
                return true;
            }

            if (!commandSender.hasPermission(permission) && !permission.isEmpty()) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', permissionDeniedMessage));
                return true;
            }

            if (args.length < minArgs) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', notEnoughArgsMessage));
                return true;
            }

            algorithm.execute(commandSender, args);

            return true;
        }

    }

    public Module getModule() {
        return module;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public String getPermissionDeniedMessage() {
        return permissionDeniedMessage;
    }

    public String getPlayerOnlyMessage() {
        return playerOnlyMessage;
    }

    public String getNotEnoughArgsMessage() {
        return notEnoughArgsMessage;
    }

    public CommandAlgorithm getAlgorithm() {
        return algorithm;
    }
}