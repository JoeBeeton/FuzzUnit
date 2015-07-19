package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;

/**
 * RandomStringListBuilder is used to generate a random list of String values within a range of sizes.
 * For example 
 * 	new RandomStringListBuilder().getListOfSizeRange(5, 10).setMinMaxValueLength( 100, 1000 ).generateList();
 * Would generate a list of random String values between 5 - 10 values in length with each String element in the list
 * have a length between 100 to 1000 .
 * @author jbeeton
 *
 */
public class RandomStringListBuilder extends RandomListBuilderBase<String> {
	
	private int minStringLength = 0;
	private int maxStringLength = 0;
	

	public RandomStringListBuilder setMinMaxValueLength( int min, int max ) {
		this.minStringLength = min;
		this.maxStringLength = max;
		return this;
	}
	
	public List<String> generateList() {
		List<String> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getRandomStringOfLength( RandomUtils.getNumberInRange( 
					minStringLength, maxStringLength ) ) );
		}
		return list;
	}
	

}
