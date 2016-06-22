package io.lyonix.phoenix.api.builder.command;

import io.lyonix.phoenix.api.Module;
import io.lyonix.phoenix.api.command.Command;
import io.lyonix.phoenix.api.command.CommandAlgorithm;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.command.CommandSender;

@Accessors(fluent = true)
public class CommandBuilder {

    private Module module;

    public CommandBuilder(Module module) {
        this.module = module;
    }

    @Setter
    private String name = "";

    @Setter
    private String description = "";

    @Setter
    private String usage = "";

    @Setter
    private int minArgs = 0;

    @Setter
    private String permission = "";

    @Setter
    private boolean playerOnly = false;

    @Setter
    private String permissionDeniedMessage = "&cYou do not have permission.";

    @Setter
    private String playerOnlyMessage = "Only a player can perform this command.";

    @Setter
    private String notEnoughArgsMessage = "&cNot enough arguments.";

    @Setter
    private CommandAlgorithm algorithm = new CommandAlgorithm() {

        @Override
        public void execute(CommandSender sender, String[] args) {
            // This is just to avoid errors. Do nothing instead of throw exceptions.
        }

    };

    public Command build() {
        return new Command(
                module,
                name,
                description,
                usage,
                minArgs,
                permission,
                playerOnly,
                permissionDeniedMessage,
                playerOnlyMessage,
                notEnoughArgsMessage,
                algorithm
        );
    }

}
