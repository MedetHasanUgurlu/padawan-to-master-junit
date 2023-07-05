# JUNIT5

**____Order 66____**
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
<div align="center">
<img src="img_3.png">
</div>



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



## Software Testing Principle
<div align="center">
<img src="img_16.png">
</div>

**Unit Test**:Unit testing is a software testing method in which individual units of source code.
**Integration Test**: Examining the interaction 
among components, possibly running in their target environment, is the job of integration
testing.\
**System software testing**: System testing of software is testing conducted on a complete, integrated system to evaluate
the system’s compliance with its specified requirements. \
**Acceptance software testing**: It is important that an application performs well, but the application must also meet the
customer's needs. Acceptance tests are our final level of testing.

**Black-box testing:** A black-box test has no knowledge of the internal state or behavior of the system. The test
relies solely on the external system interface to verify its correctness such as HTTPUnit, HTMLUnit, or Selenium. 

**White-box testing:** Understanding component implementation, we need to
know how this testing process interacts with other components.
<div align="center">
<img src="img_17.png">
</div>

### Reduce dependencies
Using dependency injection reduce the fault and coupling.
<div align="center">
<img src="img_18.png">
<img src="img_19.png">
</div>


## Coarse-grained testing with stubs 
Suppose that you are working with other developers on a project. What if you
want to test your part of the application, but the other part is not ready? One solution is to
simulate the missing part by replacing it with a fake that behaves in a similar way.
There are two strategies for providing these fake objects: stubbing and using mock
objects.

The pattern of testing with a **stub** is: initialize stub > execute test > verify assertions.\
The pattern of testing with a **mock object** is: initialize mock > set expectations > execute test > verify assertions. 



**BDD= Behaviour Driven Development**



### @DataJpaTest
Test the persistence layer components that will autoconfigure an **in-memory embedded database** for testing purposes.
Does not load beans into ApplicationContext.
@DataJpaTest are transactional and roll back at the end of each test.


 
   



# Code Part
### import static methods
Assertions.assertThat()  = assertThat()

      import static org.assertj.core.api.Assertions.assertThat;
      
      Assertions.assertThat(e.getId()).isGreaterThan(0);
      assertThat(e.getName()).isEmpty();



**@SpringBootTest**: Annotation for Integration testing. This annotation creates and application context.

**@WebMvcTest**: Based tests runs faster as wit will only the specified controller and its dependencies only without loading entire application.




### A java DSL for reading JSON documents.
 
Json


      {
         "firstName": "Medet",
         "lastName": "Ugurlu"
      }
   
JsonPath Expression

      {
         $.firstName = "Medet"
         $.lastName = "Ugurlu"
      }

## Repository 

      @DataJpaTest
      class EmployeeRepositoryTest {
      
          @Autowired
          EmployeeRepository repository;
          
          private Employee e;
          @BeforeEach
          void setup(){
              e = Employee.builder().name("Metehan").surName("Ugurlu").department("TSK").salary(30000).build();
          }
      
          @Test
          void saveTest() {
              repository.save(e);
              Assertions.assertThat(e).isNotNull();
              Assertions.assertThat(e.getId()).isGreaterThan(0);
              assertThat(e.getName()).isNotEmpty();
          }
      
          @Test
          void getAll() {
              repository.save(e);
              List<Employee> employeeList = repository.findAll();
              employeeList.forEach(System.out::println);
              assertThat(employeeList.size()).isGreaterThan(0);
          }
          @Test
          void delete(){
              repository.save(e);
              repository.deleteById(e.getId());
              assertThat(repository.findById(e.getId())).isEmpty();
          }
      }

## Service

      import static org.assertj.core.api.Assertions.assertThat;
      import static org.mockito.BDDMockito.given;
      import static org.mockito.BDDMockito.willDoNothing;
      import static org.mockito.Mockito.*;

      @Log4j2
      @ExtendWith(MockitoExtension.class)
      public class EmployeeManagerTest {
      @Mock
      private EmployeeRepository employeeRepository;
      @InjectMocks
      private EmployeeManager service;
      
          @BeforeEach
          void setUp() {
      //        employeeRepository = Mockito.mock(EmployeeRepository.class);
      //        service = new EmployeeManager(employeeRepository);
      }
      
          @AfterEach
          void tearDown() {
          }
      
          @Test
          void add() {
              Employee employee = new Employee(0,"Medet","Ugurlu",76,"EEE");
              given(employeeRepository.existsByNameIgnoreCase(any())).willReturn(false);
              given(employeeRepository.save(any(Employee.class))).willReturn(employee);
              Employee savedEmployee = service.add(new EmployeeDto(0,"a","b",34,"c"));
              assertThat(savedEmployee).isNotNull();
          }
      
          @Test
          void delete() {
              int id  = 1;
              willDoNothing().given(employeeRepository).deleteById(id);
              service.delete(id);
              verify(employeeRepository,times(1)).deleteById(id);
      
          }
      
          @Test
          void get() {
              Employee employee = new Employee(1,"Hasan","Ugurlu",34,"CCC");
              given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
              EmployeeDto employeeDto = service.get(employee.getId());
              log.info(employeeDto);
      
              assertThat(employeeDto).isNotNull();
              assertThat(employeeDto.name()).isEqualTo(employee.getName());
          }
      
          @Test
          void getAll() {
              List<Employee> employeeList = List.of(
                      new Employee(0,"Hasan","Ugurlu",34,"CCC"),
                      new Employee(0,"Medet","Ugurlu",76,"EEE"));
              given(employeeRepository.findAll()).willReturn(employeeList);
              System.out.println(employeeList);
              System.out.println(service.getAll());

              assertThat(service.getAll()).isNotNull();
          }
      }

## Controller


      import static org.mockito.BDDMockito.*;
      import static org.junit.jupiter.api.Assertions.*;

      @WebMvcTest
      class EmployeeControllerTest {
      
          @Autowired
          private MockMvc mockMvc;
          @Autowired
          private ObjectMapper objectMapper;
          @MockBean
          private EmployeeService service;

          @Test
          void add() throws Exception {
              Employee employee = new Employee(1,"B","C",1,"D");
              given(service.add(ArgumentMatchers.any(EmployeeDto.class))).willReturn(employee);
      
              ResultActions response = mockMvc.perform(
                      MockMvcRequestBuilders.
                              post("/employee").
                              contentType(MediaType.APPLICATION_JSON).
                              content(objectMapper.writeValueAsString(employee)));
              response.andDo(MockMvcResultHandlers.print()).
                      andExpect(MockMvcResultMatchers.status().isOk()).
                      andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())));
         }  
      }


