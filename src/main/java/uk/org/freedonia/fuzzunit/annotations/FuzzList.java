package uk.org.freedonia.fuzzunit.annotations;

/**
 * FuzzList is used to define a List based argument containing random values of a specified
 * type which is passed to the test method.
 * For example the annotation
 *  <pre>
 * {@code	
 *  @FuzzList( 
 *		argOrder = 0,
 *		type = FuzzTypes.STRING,
 *		minElementCount = 5,
 *		maxElementCount = 10,
 *		minValueSize = 20,
 *		maxValueSize = 75 )		
 * )
 * }</pre>
 * The above @FuzzList annotation would define a single {@code List<String>} argument passed to a test, the List would be
 *  passed in as the first argument to the test method.
 * The List would contain String values. The size of the List would be between 5 - 10 elements. Which each String in 
 * the list being between 20 and 75 characters in length.
 * @author jbeeton
 */
public @interface FuzzList {
	
	/**
	 * The index of the argument in the method that this list will be passed in at. Index starts 
	 * at 0.
	 * @return index of the argument of this method.
	 */
	int argOrder();
	/**
	 * The type of value that this list will contain.
	 * @return the type of value.
	 */
	FuzzTypes type();
	/**
	 * the minimum amount of values in the List. Defaults to 0.
	 * @return the minimum  amount of values in the list.
	 */
	int minElementCount() default 0;
	/**
	 * The maximum amount of values in the List. Defaults to 1000.
	 * @return the maximum amount of values in the list.
	 */
	int maxElementCount() default 1000;
	/**
	 * The minimum value of the size of each element in the list, defaults to 100 if 
	 * no value is set. What this means is dependent on the
	 * type of value. For number values its the maximum size of the number. For Strings it's the length
	 * of the string. For Character, Byte or Boolean values this value is ignored.
	 * @return the minimum value of the size of each element in the list.
	 */
	int minValueSize() default 0;
	/**
	 * The maximum value of the size of each element in the list, defaults to 100 if 
	 * no value is set. What this means is dependent on the
	 * type of value. For number values its the maximum size of the number. For Strings it's the length
	 * of the string. For Character, Byte or Boolean values this value is ignored.
	 * @return the maximum value of the size of each element in the list.
	 */
	int maxValueSize() default 100;

	
}