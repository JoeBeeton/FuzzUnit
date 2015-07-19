package uk.org.freedonia.fuzzunit;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;

@RunWith( FuzzUnit.class )
public class IntegrationTestWithSingleValues {

	
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.STRING, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleStringValue( String value ) {
		assertNotNull( value );
		assertTrue( value.length() >= 1 );
		assertTrue( value.length() <= 100 );
	}
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.INTEGER, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleIntegerValue( Integer value ) {
		assertNotNull( value );
		assertTrue( value >= 1 );
		assertTrue( value <= 100 );
	}
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.LONG, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleLongValue( Long value ) {
		assertNotNull( value );
		assertTrue( value >= 1 );
		assertTrue( value <= 100 );
	}
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.FLOAT, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleFloatValue( Float value ) {
		assertNotNull( value );
		assertTrue( value >= 1 );
		assertTrue( value <= 100 );
	}
	
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.SHORT, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleShortValue( Short value ) {
		assertNotNull( value );
		assertTrue( value >= 1 );
		assertTrue( value <= 100 );
	}
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.DOUBLE, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleDoubleValue( Double value ) {
		assertNotNull( value );
		assertTrue( value >= 1 );
		assertTrue( value <= 100 );
	}

	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.BOOLEAN, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleBooleanValue( Boolean value ) {
		assertNotNull( value );
	}
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.CHARACTER, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleCharValue( Character value ) {
		assertNotNull( value );
	}
	
	@FuzzTest( iterationCount = 1,
			fuzzLists = {}, 
			fuzzValues =  @FuzzValue( argOrder = 0, type = FuzzTypes.BYTE, minValueSize = 1, maxValueSize = 100  )  )
	public void testWithSingleByteValue( Byte value ) {
		assertNotNull( value );
	}
	
	
}
