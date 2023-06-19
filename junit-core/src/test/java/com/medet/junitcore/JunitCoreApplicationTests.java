package com.medet.junitcore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Log4j2
@SpringBootTest
@SuppressWarnings({"java:S5863","java:S3415","java:S5785","java:S2924"})
class JunitCoreApplicationTests {
	boolean checkJavaVersion = true;

	@BeforeEach
	void assume(TestInfo testInfo){
		assumeTrue(checkJavaVersion);
		log.info("Java version checked.");
	}
	@Test
	void testInfo(TestInfo testInfo){
		log.info(testInfo.getTestClass().toString());
		log.info(testInfo.getTestMethod());
	}

	@RepeatedTest(value = 5,name = "{displayName}: repetition-  {currentRepetition}/{totalRepetitions}")
	void repeatTest(){
		assertTrue("m"=="m");
	}


	@Test
	@DisplayName("\uD83D\uDC7B \uD83D\uDC7B \uD83D\uDC7B")
	void contextLoads() {
		assertAll(
				() -> assertEquals(2,2),
				() -> assertEquals("Medet","Medet"));
	}




	@ParameterizedTest
	@ValueSource(strings = {"Metehan","Medet"})
	void parameterizedTest(String sentence){
		assertTrue(sentence.startsWith("M"));
	}


	@EnumSource(value = Languages.class)
	@ParameterizedTest
	void parameterizedEnumTest(Languages sentences){
		assertTrue(sentences.value().startsWith("J"));
	}

	enum Languages{
		JAVA("JAVA"),
		RUST("RUST"),
		C("C");

		private final String languages;
		Languages(String languages) {
			this.languages = languages;
		}
		public String value() {
			return languages;
		}

	}


}
