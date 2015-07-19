package uk.org.freedonia.fuzzunit.builder;


/**
 * RandomListWithElementRangeBase is an abstract class used to define the min and max value of 
 * each value in a list.
 * @author jbeeton
 *
 * @param <T>
 */
public abstract class RandomListWithElementRangeBase<T> extends RandomListBuilderBase<T> {
	
	
	/**
	 * The minimum size of each value.
	 */
	private T minValue;
	/**
	 * The maximum size of each value.
	 */
	private T maxValue;
	
	/**
	 * sets the min and max element values for the list.
	 * @param min the minimum size of each element in the list.
	 * @param max the maximum size of each element in the list.
	 * @return the RandomListWithElementRangeBase.
	 */
	public RandomListWithElementRangeBase<T> setMinMaxValueLength( T min, T max ) {
		minValue = min;
		maxValue = max;
		return this;
	}
	
	/**
	 * Returns the minimum value.
	 * @return the minimum value.
	 */
	public T getMinValue() {
		return minValue;
	}
	
	/**
	 * Returns the maximum value.
	 * @return the maximum value.
	 */
	public T getMaxValue() {
		return maxValue;
	}
	
	
}
