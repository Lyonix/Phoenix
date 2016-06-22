package io.lyonix.phoenix.api.command;

import org.bukkit.command.CommandSender;

public abstract class CommandAlgorithm {

    public abstract void execute(CommandSender sender, String[] args);

}