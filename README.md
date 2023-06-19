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
      @DisplayName("😱😱")
      void testTalking() {
         assertEquals("How are you?", systemUnderTest.talk());
      }

