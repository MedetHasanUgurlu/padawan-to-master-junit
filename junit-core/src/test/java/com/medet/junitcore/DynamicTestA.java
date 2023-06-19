package com.medet.junitcore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@Log4j2
@SpringBootTest
@SuppressWarnings({"java:S5863","java:S3415","java:S5785","java:S2924"})
public class DynamicTestA {

    PredicatePositiveNumber predicatePositiveNumber = new PredicatePositiveNumber();
    @TestFactory
    Iterator<DynamicTest> positiveNumberPredicate(){
        return asList(
                        dynamicTest("negative-number",() -> assertFalse(predicatePositiveNumber.test(-5))),
                        dynamicTest("positive-number",() -> assertTrue(predicatePositiveNumber.test(5)))
        ).iterator();
    }

}
