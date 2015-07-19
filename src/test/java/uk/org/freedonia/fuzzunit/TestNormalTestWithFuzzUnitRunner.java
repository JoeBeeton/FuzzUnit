package uk.org.freedonia.fuzzunit;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;


@RunWith( FuzzUnit.class )
/**
 * These tests are to ensure that @Test tests can be run using the FuzzUnit Runner.
 * @author jbeeton
 */
public class TestNormalTestWithFuzzUnitRunner {
	
	private boolean isNormalTestRun = false;
	private boolean isSetupCalled = false;
	
	@Ignore
	@Before
	public void setup() {
		isSetupCalled = true;
	}

	@Ignore
	@Test
	public void runNormalTest() {
		isNormalTestRun = true;
	}
	
	@Ignore
	@FuzzTest(fuzzLists = {}, fuzzValues = {})
	public void runFuzzUnitTest() {
		assertTrue( isSetupCalled );
	}
	
	@Ignore
	@After
	public void afterTest() {
		assertTrue( isNormalTestRun );
		assertTrue( isSetupCalled );
	}

	
}
