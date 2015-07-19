package uk.org.freedonia.fuzzunit.builder;

import java.util.ArrayList;
import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;

/**
 * RandomByteListBuilder is used to generate a random list of Byte values within a range of sizes.
 * For example 
 * 	new RandomByteListBuilder().getListOfSizeRange(5, 10).generateList();
 * Would generate a list of random Byte values between 5 - 10 values in length
 * @author jbeeton
 *
 */
public class RandomByteListBuilder extends RandomListBuilderBase<Byte> {

	@Override
	public List<Byte> generateList() {
		List<Byte> byteList = new ArrayList<>( getListSize() );
		for ( byte b : RandomUtils.getRandomByteArrayOfLength( getListSize() ) ) {
			byteList.add( b );
		}
		return byteList;
	}

}
