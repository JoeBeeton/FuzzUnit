package uk.org.freedonia.fuzzunit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;

@RunWith( FuzzUnit.class )
public class TestFuzzTestCaseCreatorWithMultipleValues extends TestFuzzTestCaseCreator {
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleStringList() throws InitializationError {
		String methodName = "testWithMultipleStringList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.STRING, FuzzTypes.STRING );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleIntegerList() throws InitializationError {
		String methodName = "testWithMultipleIntList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.INTEGER, FuzzTypes.INTEGER );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleDoubleList() throws InitializationError {
		String methodName = "testWithMultipleDoubleList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.DOUBLE, FuzzTypes.DOUBLE );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleFloatList() throws InitializationError {
		String methodName = "testWithMultipleFloatList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.FLOAT, FuzzTypes.FLOAT );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleShortList() throws InitializationError {
		String methodName = "testWithMultipleShortList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.SHORT, FuzzTypes.SHORT );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleLongList() throws InitializationError {
		String methodName = "testWithMultipleLongList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.LONG, FuzzTypes.LONG );
	}
	
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleByteList() throws InitializationError {
		String methodName = "testWithMultipleByteList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.BYTE, FuzzTypes.BYTE );
	}
	
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleCharList() throws InitializationError {
		String methodName = "testWithMultipleCharList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.CHARACTER, FuzzTypes.CHARACTER );
	}
	
	@FuzzTest( iterationCount = 1000, fuzzLists = {}, fuzzValues = {} )
	public void testWithMultipleBooleanList() throws InitializationError {
		String methodName = "testWithMultipleBooleanList";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, FuzzTypes.BOOLEAN, FuzzTypes.BOOLEAN );
	}
	
	
	@FuzzTest( iterationCount = 5000, fuzzLists = {
			@FuzzList(argOrder = 0, type = FuzzTypes.INTEGER, minElementCount=1, maxElementCount=200, maxValueSize = 8 )
	}, fuzzValues = {} )
	public void testWithMultipleRandomTypes( List<Integer> typeSeeds ) throws InitializationError {
		String methodName = "testWithMultipleRandomListValues";
		int minListSize = 5;
		int maxListSize = 10;
		int minElementSize = 1;
		int maxElementSize = 100;
		FuzzTypes[] types = new FuzzTypes[typeSeeds.size()];
		for ( int i = 0; i < typeSeeds.size(); i++ ) {
			types[i] = FuzzTypes.values()[typeSeeds.get(i)];
		}
		runMultipleTest( methodName, minListSize, maxListSize,
				minElementSize, maxElementSize, types );
		
		
	}
	

	
	private void runMultipleTest( String methodName, int minListSize, int maxListSize,
			int minElementSize, int maxElementSize, FuzzTypes ...types ) throws InitializationError {
		FuzzTestCase testCase = createTest( methodName, minListSize,
				maxListSize, minElementSize, maxElementSize, types );
		assertTestGeneralValues( testCase, methodName, types.length );
		assertEquals( types.length, testCase.getValues().length );
		for ( int i =0; i < types.length; i++ ) {
			new ReturnedRandomListValueValidator().validateListValue( 
					(List<?>) testCase.getValues()[i]
							, minListSize,
							maxListSize,
							minElementSize,
							maxElementSize,
							types[i]
						);
		}
	}
	
	
	
	
	
}
