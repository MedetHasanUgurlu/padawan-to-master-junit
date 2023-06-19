package com.medet.junitcore;

import java.util.function.Predicate;

public class PredicatePositiveNumber implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        return integer > 0;
    }
}
