package uk.org.freedonia.fuzzunit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class ListValidatorBase {
	
	protected void validateListSizeInRange( List<?> values, int minListSize, int maxListSize ) {
		assertTrue( values.size() >= minListSize );
		assertTrue( values.size() <= maxListSize );
	}
	
	protected void validateThatAllElementsAreNotNull( List<?> list ) {
		for ( Object object : list ) {
			assertNotNull( object );
		}
	}

}
