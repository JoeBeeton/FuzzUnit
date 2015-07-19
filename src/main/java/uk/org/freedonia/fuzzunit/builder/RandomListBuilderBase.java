package uk.org.freedonia.fuzzunit.builder;

import java.util.List;

import uk.org.freedonia.fuzzunit.utils.RandomUtils;

/**
 * Base class for all of the Random List Builders.
 */
public abstract class RandomListBuilderBase<T> {
	
	/**
	 * the size of the list to be built.
	 */
	private int listSize = 0;
	
	/**
	 * Sets the min and max size of the list to be built and returns this list builder.
	 * @param min the min size of the list.
	 * @param max the max size of the list.
	 * @return this list builder.
	 */
	public RandomListBuilderBase<T> setListOfSizeRange( int min, int max  ) {
		listSize = RandomUtils.getNumberInRange( min , max );
		return this;
	}


	/**
	 * returns the size of the list to be generated.
	 * @return the size of the list.
	 */
	public int getListSize() {
		return listSize;
	}
	
	/**
	 * generates the list.
	 * @return
	 */
	public abstract List<T> generateList();



	
	
}