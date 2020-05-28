package net.teamfruit.adhdplugin.simple;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;

public class SimpleBoundingSection {
    private BoundingBox boundingBox;
    private double distance;

    public SimpleBoundingSection(double offset) {
        this.distance = -offset;
    }

    public SimpleBoundingSection() {
        this(0);
    }

    public void record(Location location, double move) {
        if (boundingBox == null)
            boundingBox = BoundingBox.of(location, location);
        boundingBox.union(location);
        distance += move;
    }

    public double getDistance() {
        return distance;
    }

    public boolean check() {
        if (boundingBox == null)
            return false;
        double boundingDistance = boundingBox.getMin().distance(boundingBox.getMax());
        return distance > boundingDistance * 4;
    }
}
