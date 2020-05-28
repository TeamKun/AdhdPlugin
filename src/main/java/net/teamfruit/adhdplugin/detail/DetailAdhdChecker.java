package net.teamfruit.adhdplugin.detail;

import com.destroystokyo.paper.ParticleBuilder;
import net.teamfruit.adhdplugin.IAdhdChecker;
import net.teamfruit.adhdplugin.simple.SimpleAdhdChecker;
import net.teamfruit.adhdplugin.simple.SimpleSectionTimer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.LinkedList;
import java.util.Optional;

public class DetailAdhdChecker implements IAdhdChecker {
    private LinkedList<BlockNode> path = new LinkedList<>();
    private final double sectionLength;
    public double distance;
    private final SimpleSectionTimer timer = new SimpleSectionTimer(5000);

    public DetailAdhdChecker(double length) {
        this.sectionLength = length;
    }

    @Override
    public Optional<IAdhdChecker> walk(PlayerMoveEvent event) {
        // 5秒止まっていたらリセット
        if (timer.isResetNeededTime())
            return Optional.of(new SimpleAdhdChecker());
        // タイマー記録
        timer.record();

        Location location = event.getTo();
        Location blockLocation = location.getBlock().getLocation();

        // 記録
        if (path.isEmpty() || !path.getLast().blockLocation.equals(blockLocation)) {
            BlockNode blockNode = new BlockNode(location, blockLocation);
            path.add(blockNode);
        } else {
            path.getLast().frames.add(location);
        }

        // 詳細チェックの期間
        double move = event.getFrom().distance(event.getTo());
        distance += move;
        if (distance > sectionLength) {
            // 詳細チェック終了
            event.getPlayer().chat("§eYour ADHD Data is Here");
            return Optional.of(new DetailAdhdPlayer(path));
        }

        return Optional.empty();
    }
}
