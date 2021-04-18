package net.kunmc.lab.tritium;

import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Tritium.addBar(event.getPlayer());
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        BossBar bar = Tritium.bossBars.get(event.getPlayer());
        double increment;
        if (item.getItemMeta().getDisplayName().contains("処理")) {
            increment = 0.2;
        } else {
            increment = 0.05;
        }

        bar.setProgress(Math.min(bar.getProgress() + increment, 1.0));

        Tritium.playerEffect(event.getPlayer());
    }
}
