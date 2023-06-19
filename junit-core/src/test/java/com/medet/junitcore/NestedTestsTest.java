package com.medet.junitcore;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NestedTestsTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";

    @Nested
    class Builder{
        private String MIDDLE_NAME = "Michael";

        @Test
         void nestedMethod(){
            assertEquals(FIRST_NAME,"John");




        }



    }

}
