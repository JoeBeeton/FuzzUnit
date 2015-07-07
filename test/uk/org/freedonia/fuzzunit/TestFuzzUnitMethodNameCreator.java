package uk.org.freedonia.fuzzunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;

public class TestFuzzUnitMethodNameCreator {
	
	@Test
	public void testWithSingleString() {
		String value = "This is a value for a string";
		Object[] values = new Object[]{ value };
		FuzzTest fuzz = AnnotationInterfaceUtils.getFuzzy(1, new FuzzList[]{},
				new FuzzValue[]{ AnnotationInterfaceUtils.getFuzzyValue(0, FuzzTypes.STRING, 1, 10 )}
				);
		FuzzUnitMethodNameCreator creator = new FuzzUnitMethodNameCreator( fuzz, values, "" );
		assertEquals( "[STRING {This is a value for a string}] ", creator.getName() );
	}
	
	@Test
	public void testWithNoValues() {
		Object[] values = new Object[]{};
		FuzzTest fuzz = AnnotationInterfaceUtils.getFuzzy(1, new FuzzList[]{},
				new FuzzValue[]{}
				);
		String defaultValue = "This is the default value tthat should be used";
		FuzzUnitMethodNameCreator creator = new FuzzUnitMethodNameCreator( fuzz, values, defaultValue );
		String value = creator.getName();
		assertNotNull( value );
		assertEquals( defaultValue, value );
	}
	

}
