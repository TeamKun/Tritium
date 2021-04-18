package net.kunmc.lab.tritium;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (!player.getName().equals("YukiAt")) {
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("引数たりん");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "enable":
                Tritium.init();
                break;
            case "disable":
                Tritium.terminate();
                break;
        }

        return true;
    }
}
