package io.lyonix.phoenix.api.command;

import io.lyonix.phoenix.api.Module;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Command {

    private Module module;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private String usage;

    @Getter
    private int minArgs;

    @Getter
    private String permission;

    @Getter
    private boolean playerOnly;

    @Getter
    private String permissionDeniedMessage;

    @Getter
    private String playerOnlyMessage;

    @Getter
    private String notEnoughArgsMessage;

    @Getter
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

}