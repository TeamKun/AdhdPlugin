package net.teamfruit.adhdplugin;

import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Optional;

public interface IAdhdChecker {
    Optional<IAdhdChecker> walk(PlayerMoveEvent event);
}
