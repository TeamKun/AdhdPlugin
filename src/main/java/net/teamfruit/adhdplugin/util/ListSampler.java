package net.teamfruit.adhdplugin.util;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSampler {
    public static <T> List<T> getSample(List<T> sourceList, int count) {
        if (count <= 0)
            return Collections.emptyList();

        List<T> list = new ArrayList<>(count);

        int size = sourceList.size();
        if (size <= count)
            return sourceList;

        // for (int i = 0; i < count; i++) {
        //    list.add(frames.get(i * size / count));
        // }

        int i = 0;
        for (T source : sourceList) {
            int index = list.size() * size / count;
            if (i >= index)
                list.add(source);
            i++;
        }

        return list;
    }
}
