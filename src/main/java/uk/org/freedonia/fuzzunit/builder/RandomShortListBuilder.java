package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;

/**
 * RandomShortListBuilder is used to generate a random list of Short values within a range of sizes.
 * For example 
 * 	new RandomShortListBuilder().getListOfSizeRange(5, 10).setMinMaxValueLength( 100, 1000 ).generateList();
 * Would generate a list of random Short values between 5 - 10 values in length with each Short element in the list
 * have a length between 100 to 1000 .
 * @author jbeeton
 *
 */
public class RandomShortListBuilder extends RandomListWithElementRangeBase<Short>  {

	@Override
	public List<Short> generateList() {
		List<Short> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getRandomShortInRange( 
					getMinValue(), getMaxValue() ) );
		}
		return list;
	}

}
 