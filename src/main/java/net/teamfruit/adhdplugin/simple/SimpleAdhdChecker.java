package net.teamfruit.adhdplugin.simple;

import net.teamfruit.adhdplugin.IAdhdChecker;
import net.teamfruit.adhdplugin.detail.DetailAdhdChecker;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleAdhdChecker implements IAdhdChecker {
    private final List<SimpleSectionContainer> sections = Arrays.asList(
            new SimpleSectionContainer(600),
            new SimpleSectionContainer(600, 200),
            new SimpleSectionContainer(600, 400),
            new SimpleSectionContainer(150),
            new SimpleSectionContainer(150, 50),
            new SimpleSectionContainer(150, 100)
    );
    private final Map<Double, List<SimpleSectionContainer>> sectionGroups =
            sections.stream().collect(Collectors.groupingBy(section -> section.sectionLength));
    private final SimpleSectionTimer timer = new SimpleSectionTimer(5000);

    @Override
    public Optional<IAdhdChecker> walk(PlayerMoveEvent event) {
        // 5秒止まっていたらリセット
        if (timer.isResetNeededTime())
            sections.stream().forEach(section -> section.clear());
        // タイマー記録
        timer.record();

        // 行動を大まかに記録
        sections.stream().forEach(section -> section.section.record(event.getTo(), event.getFrom().distance(event.getTo())));

        // 同じところをぐるぐるしているか大まかに判定
        Optional<Map.Entry<Double, List<SimpleSectionContainer>>> sectionGroup = sectionGroups.entrySet().stream().filter(
                entry -> entry.getValue().stream().allMatch(section -> section.lastSection.check())
        ).findFirst();
        if (sectionGroup.isPresent()) {
            event.getPlayer().chat("§eYou are now ADHD");
            return Optional.of(new DetailAdhdChecker(sectionGroup.get().getKey()));
        }

        // 距離に達したら0からカウントし直す
        sections.stream().filter(SimpleSectionContainer::isResetNeededLength).forEach(SimpleSectionContainer::reset);

        return Optional.empty();
    }
}
