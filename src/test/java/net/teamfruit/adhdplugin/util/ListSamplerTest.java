package net.teamfruit.adhdplugin.util;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ListSamplerTest {
    @Test
    public void getSample() {
        List<Integer> sample = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        assertTrue(ListSampler.getSample(sample, 0).isEmpty());

        System.out.println(ListSampler.getSample(sample, 2));
        System.out.println(ListSampler.getSample(sample, 5));
        System.out.println(ListSampler.getSample(sample, 4));

        assertTrue(ListSampler.getSample(sample, 2).size() == 2);
        assertTrue(ListSampler.getSample(sample, 5).size() == 5);
        assertTrue(ListSampler.getSample(sample, 4).size() == 4);
    }

    @Test
    public void getSample100() {
        List<Integer> sample = IntStream.range(0, 100).boxed().collect(Collectors.toList());

        assertTrue(ListSampler.getSample(sample, 0).isEmpty());

        System.out.println(ListSampler.getSample(sample, 2));
        System.out.println(ListSampler.getSample(sample, 5));
        System.out.println(ListSampler.getSample(sample, 4));
        System.out.println(ListSampler.getSample(sample, 13));
        System.out.println(ListSampler.getSample(sample, 21));

        assertTrue(ListSampler.getSample(sample, 2).size() == 2);
        assertTrue(ListSampler.getSample(sample, 5).size() == 5);
        assertTrue(ListSampler.getSample(sample, 4).size() == 4);
        assertTrue(ListSampler.getSample(sample, 13).size() == 13);
        assertTrue(ListSampler.getSample(sample, 21).size() == 21);
    }
}