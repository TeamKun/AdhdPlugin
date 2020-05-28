package net.teamfruit.adhdplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class AdhdPlugin extends JavaPlugin implements Listener {
    public static AdhdPlugin INSTANCE;
    public static Logger log;
    private Map<Player, AdhdPlayerMemoryData> store = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        log = getLogger();
        getLogger().info("ADHD Plugin is Enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ADHD Plugin is Disabled");
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        AdhdPlayerMemoryData data = store.computeIfAbsent(player, key -> new AdhdPlayerMemoryData());
        data.walk(event);
    }
}
