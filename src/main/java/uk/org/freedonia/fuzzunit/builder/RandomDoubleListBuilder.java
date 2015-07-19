package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;


/**
 * RandomDoubleListBuilder is used to generate a random list of Double values within a range of sizes.
 * For example 
 * 	new RandomDoubleListBuilder().getListOfSizeRange(5, 10).setMinMaxValueLength( 100, 1000.5 ).generateList();
 * Would generate a list of random Doubles values between 5 - 10 values in length with each Double element in the list
 * have a length between 100 to 1000.5 .
 * @author jbeeton
 *
 */
public class RandomDoubleListBuilder extends RandomListWithElementRangeBase<Double> {
	

	@Override
	public List<Double> generateList() {
		List<Double> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getRandomDoubleInRange( 
					getMinValue(), getMaxValue() ) );
		}
		return list;
	}

}
