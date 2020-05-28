package net.teamfruit.adhdplugin.detail;

import com.destroystokyo.paper.ParticleBuilder;
import net.teamfruit.adhdplugin.AdhdPlugin;
import net.teamfruit.adhdplugin.IAdhdChecker;
import net.teamfruit.adhdplugin.simple.SimpleAdhdChecker;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetailAdhdPlayer implements IAdhdChecker {
    private final LinkedList<BlockNode> path;
    private final List<Location> frames;
    private final List<Location> sampledFrames;
    private Iterator<Location> iterator;

    public DetailAdhdPlayer(LinkedList<BlockNode> path) {
        this.path = path;
        this.frames = path.stream().flatMap(m -> m.frames.stream()).collect(Collectors.toList());
        this.iterator = frames.iterator();
        this.sampledFrames = path.stream().flatMap(node -> node.getSampleFrames(4).stream()).collect(Collectors.toList());
    }

    private BukkitRunnable schedule;
    private Player player;

    @Override
    public Optional<IAdhdChecker> walk(PlayerMoveEvent event) {
        if (this.schedule == null) {
            this.player = event.getPlayer();
            this.schedule = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!iterator.hasNext()) {
                        cancel();
                        return;
                    }
                    sampledFrames.forEach(frame -> {
                        new ParticleBuilder(Particle.REDSTONE)
                                .color(Color.RED)
                                .location(frame)
                                .spawn();
                    });
                    player.teleport(iterator.next());
                }
            };
            this.schedule.runTaskTimer(AdhdPlugin.INSTANCE, 1l, 1l);
        }

        if (event.getPlayer().isSneaking())
            schedule.cancel();

        if (schedule.isCancelled())
            return Optional.of(new SimpleAdhdChecker());

        return Optional.empty();
    }
}
