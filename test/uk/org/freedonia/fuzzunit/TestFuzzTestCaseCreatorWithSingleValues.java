package uk.org.freedonia.fuzzunit;

import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;

@RunWith( FuzzUnit.class )
public class TestFuzzTestCaseCreatorWithSingleValues extends TestFuzzTestCaseCreator {

	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithSingleStringList() throws InitializationError {
		String methodName = "testWithSingleStringList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runSingleTest( methodName, FuzzTypes.STRING, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithSingleIntegerList() throws InitializationError {
		String methodName = "testWithSingleIntegerList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 100000;
		runSingleTest( methodName, FuzzTypes.INTEGER, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithSingleLongList() throws InitializationError {
		String methodName = "testWithSingleLongList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 100000;
		runSingleTest( methodName, FuzzTypes.LONG, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithSingleFloatList() throws InitializationError {
		String methodName = "testWithSingleFloatList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 100000;
		runSingleTest( methodName, FuzzTypes.FLOAT, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithSingleDoubleList() throws InitializationError {
		String methodName = "testWithSingleDoubleList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 100000;
		runSingleTest( methodName, FuzzTypes.DOUBLE, minListSize, maxListSize, minElementSize, maxElementSize );

	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithSingleShortList() throws InitializationError {
		String methodName = "testWithSingleShortList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 30000;
		runSingleTest( methodName, FuzzTypes.SHORT, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithByteShortList() throws InitializationError {
		String methodName = "testWithSingleByteList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 0;
		runSingleTest( methodName, FuzzTypes.BYTE, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithCharShortList() throws InitializationError {
		String methodName = "testWithCharByteList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 0;
		runSingleTest( methodName, FuzzTypes.CHARACTER, minListSize, maxListSize, minElementSize, maxElementSize );
	}
	

	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithBooleanShortList() throws InitializationError {
		String methodName = "testWithBooleanByteList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 0;
		int maxElementSize = 0;
		runSingleTest( methodName, FuzzTypes.BOOLEAN,
				minListSize, maxListSize, minElementSize, maxElementSize );
	}
	
	@SuppressWarnings("unchecked")
	private void runSingleTest( String methodName, FuzzTypes type, int minListSize, int maxListSize,
			int minElementSize, int maxElementSize ) throws InitializationError {
		FuzzTestCase testCase = createTest( methodName, minListSize,
				maxListSize, minElementSize, maxElementSize, type );
		assertTestGeneralValues( testCase, methodName, 1 );
		new ReturnedRandomListValueValidator().validateListValue( 
				(List<Object>) testCase.getValues()[0], 
				minListSize, 
				maxListSize, 
				minElementSize,
				maxElementSize, 
				type
			);
	}
	
	
	
}
