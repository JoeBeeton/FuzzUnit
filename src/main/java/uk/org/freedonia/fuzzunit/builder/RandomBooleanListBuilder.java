package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;


/**
 * RandomBooleanListBuilder is used to generate a random list of Boolean values within a range of sizes.
 * For example 
 * 	new RandomBooleanListBuilder().getListOfSizeRange(5, 10).generateList();
 * Would generate a list of random Boolean values between 5 - 10 values in length
 * @author jbeeton
 *
 */
public class RandomBooleanListBuilder extends RandomListBuilderBase<Boolean> {
	
	

	@Override
	public List<Boolean> generateList() {
		List<Boolean> list = new ArrayList<>( getListSize() );
		for ( int i =0; i < getListSize(); i++ ) {
			list.add( RandomUtils.getRandomBoolean() );
		}
		return list;	
	}

}
