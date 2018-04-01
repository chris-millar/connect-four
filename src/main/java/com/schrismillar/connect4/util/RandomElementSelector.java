package com.schrismillar.connect4.util;

import java.util.List;
import java.util.Random;

public class RandomElementSelector {
    private final Random random;

    public RandomElementSelector(Random random)  {
        this.random = random;
    }

    public <T> T selectFrom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
