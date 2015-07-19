package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;


/**
 * RandomCharListBuilder is used to generate a random list of Char values within a range of sizes.
 * For example 
 * 	new RandomCharListBuilder().getListOfSizeRange(5, 10).generateList();
 * Would generate a list of random Char values between 5 - 10 values in length
 * @author jbeeton
 *
 */
public class RandomCharListBuilder extends RandomListBuilderBase<Character> {

	@Override
	public List<Character> generateList() {
		List<Character> list = new ArrayList<>( getListSize() );
		for ( char c : RandomUtils.getRandomCharArrayOfLength( getListSize() ) ) {
			list.add( c );
		}
		return list;
	}

}
