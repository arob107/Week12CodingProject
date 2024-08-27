package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {

	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		// Given
		if (!expectException) {
			// When
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			// Then
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}

	/**
	 * @return a Stream of test arguments
	 */
	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
	    return Stream.of(
	        arguments(4, 6, 10, false),
	        arguments(-3, 2, -1, true),
	        arguments(1, 0, 1, true),
	        arguments(10, 100, 110, false)
	    );
	    // @formatter:on
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 1)).isEqualTo(5);
		assertThat(testDemo.addPositive(56, 9)).isEqualTo(65);
		assertThat(testDemo.addPositive(1, 1)).isEqualTo(2);
	}
	
	/*
	 * This is a parameterized test the verifies the mulitplyNegative method in TestDemo functions correctly
	 */
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForMultiplyNegative")
	void assertThatTwoNegativeNumbersAreMultipliedCorrectly(int a, int b, int expected, boolean expectException) {
		// Given
		if (!expectException) {
			// When
			assertThat(testDemo.multiplyNegative(a, b)).isEqualTo(expected);
		} else {
			// Then
			assertThatThrownBy(() -> testDemo.multiplyNegative(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}

	/**
	 * @return a Stream of test arguments
	 */
	static Stream<Arguments> argumentsForMultiplyNegative() {
		// @formatter:off
	    return Stream.of(
	        arguments(-4, -6, 24, false),
	        arguments(-3, 2, -1, true),
	        arguments(1, 0, 1, true),
	        arguments(-10, -100, 1000, false)
	    );
	    // @formatter:on
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}

}
