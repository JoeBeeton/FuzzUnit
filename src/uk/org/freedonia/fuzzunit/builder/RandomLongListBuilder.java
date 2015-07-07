package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;


/**
 * RandomLongListBuilder is used to generate a random list of Long values within a range of sizes.
 * For example 
 * 	new RandomLongListBuilder().getListOfSizeRange(5, 10).setMinMaxValueLength( 100, 1000 ).generateList();
 * Would generate a list of random Long values between 5 - 10 values in length with each Long element in the list
 * have a length between 100 to 1000.5 .
 * @author jbeeton
 *
 */
public class RandomLongListBuilder extends RandomListWithElementRangeBase<Long> {
	

	@Override
	public List<Long> generateList() {
		List<Long> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getRandomLongInRange( 
					getMinValue(), getMaxValue() ) );
		}
		return list;
	}

}
