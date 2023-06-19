# JUNIT5

1. Test classes cannot be abstract.
2. must have a single constructor.
3. The constructor must have no arguments or
   arguments that can be dynamically resolved at runtime through dependency injection.
4. A test method is an instance method that is annotated with @Test, @RepeatedTest,
   @ParameterizedTest, @TestFactory, or @TestTemplate.
5. A life cycle method is a method that is annotated with @BeforeAll, @AfterAll,
   @BeforeEach, or @AfterEach. 
6. Test methods must not return a value.

#### @DisplayName

      @Test
      @DisplayName("👻👻👻")
      void testTalking() {
         assertEquals("How are you?", systemUnderTest.talk());
      }

![img_3.png](img_3.png)

#### @Disable

The @Disabled annotation can be used over classes and test methods. It signals that the
annotated test class or test method is disabled and should not be executed.

#### @Nested
Using it for tight couple inner class.

<div align="center">
<img src="img_4.png">
</div>

## Assertion
<div align="center">
<img src="img_5.png">
</div>

Import Assertions class and its statics methods.
   
      import static org.junit.jupiter.api.Assertions.*;

![img_6.png](img_6.png)

## Assumptions 
Sometimes tests fail due to an external environment configuration or a date or time zone issue
that we cannot control. We can prevent our tests from being executed under inappropriate
conditions. 

<div align="center">
<img src="img_7.png">
</div>

## TestInfo
![img_8.png](img_8.png)

## @RepeatTest

<div align="center">
<img src="img_9.png">
<img src="img_10.png">
</div>

## @ParameterizedTest

<div align="center">
<img src="img_11.png">
<img src="img_12.png">
</div>

Using Enum
<div align="center">
<img src="img_14.png">
</div>

## DynamicTest
![img_15.png](img_15.png)




