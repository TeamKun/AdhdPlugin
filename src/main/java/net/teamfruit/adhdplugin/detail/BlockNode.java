package net.teamfruit.adhdplugin.detail;

import net.teamfruit.adhdplugin.util.ListSampler;
import org.bukkit.Location;

import java.util.*;

public class BlockNode {
    public Location blockLocation;
    public LinkedList<Location> frames = new LinkedList<>();

    public BlockNode(Location location, Location blockLocation) {
        this.blockLocation = blockLocation;
        this.frames.add(location);
    }

    public List<Location> getSampleFrames(int count) {
        return ListSampler.getSample(frames, count);
    }
}
