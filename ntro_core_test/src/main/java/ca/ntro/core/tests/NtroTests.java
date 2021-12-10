package ca.ntro.core.tests;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.services.ExceptionThrowerMock;

public class NtroTests {

	protected ExceptionThrowerMock registerMockExceptionThrower() {
		ExceptionThrowerMock exceptionThrowerMock = new ExceptionThrowerMock();

		InitializerTest.registerExceptionThrower(exceptionThrowerMock);

		return exceptionThrowerMock;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
	}

}
