package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;

/**
 * RandomFloatListBuilder is used to generate a random list of Float values within a range of sizes.
 * For example 
 * 	new RandomFloatListBuilder().getListOfSizeRange(5, 10).setMinMaxValueLength( 100, 1000.5 ).generateList();
 * Would generate a list of random Floats values between 5 - 10 values in length with each Float element in the list
 * have a length between 100 to 1000.5 .
 * @author jbeeton
 *
 */
public class RandomFloatListBuilder extends RandomListWithElementRangeBase<Float> {

	@Override
	public List<Float> generateList() {
		List<Float> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getRandomFloatInRange( 
					getMinValue(), getMaxValue() ) );
		}
		return list;
	}

}
