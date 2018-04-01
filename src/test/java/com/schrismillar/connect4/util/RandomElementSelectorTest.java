package com.schrismillar.connect4.util;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import java.util.Random;

public class RandomElementSelectorTest {

    @Test
    public void selectFromReturnsRandomElementFromList() {
        Random random = mock(Random.class);
        when(random.nextInt(4)).thenReturn(1);
        RandomElementSelector randomElementSelector = new RandomElementSelector(random);

        String selected = randomElementSelector.selectFrom(asList("a", "b", "c", "d"));

        assertEquals("b", selected);
    }
}