package net.kunmc.lab.tritium;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public final class Tritium extends JavaPlugin {
    public static final Map<Player, BossBar> bossBars = new HashMap<>();
    public static final BukkitRunnable timer = new Timer();
    private static Tritium plugin;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.getServer().getPluginCommand("Tritium").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
    }

    public static void init() {
        plugin.getServer().getPluginManager().registerEvents(new Events(), plugin);
        Bukkit.getOnlinePlayers().forEach(Tritium::addBar);
        timer.runTaskTimer(plugin, 0, 20);
        Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle("処理水プラグイン", "製作者：YukiAt", 10, 40, 10));
    }

    public static void terminate() {
        HandlerList.unregisterAll(plugin);
        bossBars.forEach((player, bar) -> {
            bar.removeAll();
            bossBars.remove(player);
        });
        timer.cancel();
    }

    public static void addBar(Player player) {
        if (bossBars.containsKey(player)) {
            return;
        }

        BossBar bar = Bukkit.createBossBar("体内トリチウム濃度", BarColor.RED, BarStyle.SEGMENTED_10);
        bar.addPlayer(player);
        bar.setProgress(0.0);
        bossBars.put(player, bar);
    }

    public static void playerEffect(Player player) {
        BossBar bar = bossBars.get(player);
        player.removePotionEffect(PotionEffectType.WITHER);
        player.removePotionEffect(PotionEffectType.POISON);
        if (Math.abs(bar.getProgress() - 1.0) < Double.MIN_VALUE * 10.0) {
            player.setHealth(0.0);
            bar.setProgress(0.0);
        } else if (bar.getProgress() > 0.9) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 600000, 1));
        } else if (bar.getProgress() > 0.7) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600000, 1));
        }
    }
}
