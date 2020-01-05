package com.schrismillar.connect4.util;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomElementSelector {
    private final RandomWrapper random;

    @Autowired
    public RandomElementSelector(RandomWrapper random)  {
        this.random = random;
    }

    public <T> T selectFrom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
