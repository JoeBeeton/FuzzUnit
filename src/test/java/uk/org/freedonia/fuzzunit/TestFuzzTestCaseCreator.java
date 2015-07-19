package uk.org.freedonia.fuzzunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.runners.model.InitializationError;

import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;

public class TestFuzzTestCaseCreator {

	protected void assertArrayValueSize( Object[] argValues, int expectedSize ) {
		assertNotNull( argValues );
		assertEquals( expectedSize, argValues.length );
	}
	
	protected void assertTestGeneralValues( FuzzTestCase testCase, String methodName, int expectedArgSize ) {
		assertNotNull( testCase );
		assertEquals( methodName, testCase.getMethodName() );
		assertArrayValueSize( testCase.getValues(), expectedArgSize );
		assertTrue( testCase.getValues()[0] instanceof List );
	}
	
	protected FuzzTestCase createTest( String methodName, int minListSize, int maxListSize, 
			int minElementSize, int maxElementSize, FuzzTypes ...types ) throws InitializationError {
		FuzzTestCaseCreator creator = new FuzzTestCaseCreator( AnnotationInterfaceUtils.getFuzzy(1,  
				AnnotationInterfaceUtils.getFuzzyLists( types, minListSize, maxListSize, minElementSize, maxElementSize ), new FuzzValue[]{} ), TestFuzzTestCaseCreator.class, methodName  );
		return creator.build();
	}
	



	
	
	
}
