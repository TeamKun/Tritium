package net.kunmc.lab.tritium;

import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    private final double decrement = 0.005;

    @Override
    public void run() {
        Tritium.bossBars.forEach((player, bossBar) -> {
            if (bossBar.getProgress() < decrement) {
                bossBar.setProgress(0.0);
            } else {
                bossBar.setProgress(bossBar.getProgress() - decrement);
            }
            Tritium.playerEffect(player);
        });
    }
}
