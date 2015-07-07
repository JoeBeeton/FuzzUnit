package uk.org.freedonia.pciterator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.org.freedonia.fuzzunit.FuzzUnit;
import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;

@RunWith( FuzzUnit.class )
public class TestIteratorWrapper {
	
	
	@Test
	public void testIteratorWrapper() throws IOException {
		List<String> data = getRandomStringListOfSize( 100 );
		List<String> resultData = new ArrayList<>( 100 );
		resultData.addAll( data );
		try( IteratorWrapper<String> wrapper = new IteratorWrapper<String>( data.iterator() ,100 ) ) {
			Iterator<String> expectedResultIterator = resultData.iterator();
			while ( expectedResultIterator.hasNext() ) {
				String expectedResult = expectedResultIterator.next();
			String result = wrapper.next();
				assertEquals( expectedResult, result );
			}
		}
	}
	
	
	
	private List<String> getRandomStringListOfSize(int i) {
		// TODO Auto-generated method stub
		return null;
	}



	@FuzzTest( iterationCount = 2,
			fuzzLists = @FuzzList( 
					argOrder = 0,
					type = FuzzTypes.STRING ), 
			fuzzValues = {} )
	public void runTest( List<String> data  ) throws IOException {
		List<String> resultData = new ArrayList<>();
		resultData.addAll( data );
		try( IteratorWrapper<String> wrapper = new IteratorWrapper<String>( data.iterator() ,5 ) ) {
			Iterator<String> expectedResultIterator = resultData.iterator();
			while ( expectedResultIterator.hasNext() ) {
				Object expectedResult = expectedResultIterator.next();
				Object result = wrapper.next();
				assertEquals( expectedResult, result );
			}
		}
	}
	

}
