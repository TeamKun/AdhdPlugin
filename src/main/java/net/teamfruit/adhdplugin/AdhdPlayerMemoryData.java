package net.teamfruit.adhdplugin;

import net.teamfruit.adhdplugin.simple.SimpleAdhdChecker;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Optional;

public class AdhdPlayerMemoryData {
    public IAdhdChecker checker = new SimpleAdhdChecker();

    public void walk(PlayerMoveEvent event) {
        Optional<IAdhdChecker> newState = this.checker.walk(event);
        newState.ifPresent(state -> {
            //event.getPlayer().chat("Your state is " + state.getClass().getSimpleName());
            this.checker = state;
        });
    }
}
