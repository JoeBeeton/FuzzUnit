package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;


/**
 * RandomIntegerListBuilder is used to generate a random list of Float values within a range of sizes.
 * For example 
 * 	new RandomIntegerListBuilder().getListOfSizeRange(5, 10).setMinMaxValueLength( 100, 1000 ).generateList();
 * Would generate a list of random Integers values between 5 - 10 values in length with each Float element in the list
 * have a length between 100 to 1000 .
 * @author jbeeton
 *
 */
public class RandomIntegerListBuilder extends RandomListWithElementRangeBase<Integer> {


	@Override
	public List<Integer> generateList() {
		List<Integer> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getNumberInRange( 
					getMinValue(), getMaxValue() ) );
		}
		return list;
	}

}
