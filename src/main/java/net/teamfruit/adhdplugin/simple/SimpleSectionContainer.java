package net.teamfruit.adhdplugin.simple;

public class SimpleSectionContainer {
    public SimpleBoundingSection section;
    public SimpleBoundingSection lastSection;
    public final double sectionLength;
    public final double offset;

    public SimpleSectionContainer(double length, double offset) {
        this.sectionLength = length;
        this.offset = offset;
        clear();
    }

    public SimpleSectionContainer(double length) {
        this(length, 0);
    }

    public boolean isResetNeededLength() {
        return this.sectionLength < section.getDistance();
    }

    public void clear() {
        this.section = new SimpleBoundingSection(this.offset);
        this.lastSection = new SimpleBoundingSection();
    }

    public void reset() {
        this.lastSection = this.section;
        this.section = new SimpleBoundingSection();
    }

    @Override
    public String toString() {
        return String.format("(%.1fn+%.1f)", sectionLength, offset);
    }
}
