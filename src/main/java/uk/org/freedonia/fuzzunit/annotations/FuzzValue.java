package uk.org.freedonia.fuzzunit.annotations;

/**
 * FuzzValue is used to define the value of an argument containing a random value of a specified
 * type which is passed to the test method.
 * For example the annotation
 *  <pre>
 * {@code	
 *  @FuzzValue( 
 *		argOrder = 0,
 *		type = FuzzTypes.INTEGER,
 *		minValueSize = 20,
 *		maxValueSize = 75 )		
 * )
 * }</pre>
 * The above @FuzzyValue annotation would define a single {@code Integer} argument passed to a test, the Integer 
 * value would be passed in as the first argument to the test method.
 * The size of the Integer value is randomly determined within the range of 20 - 75.
 * @author jbeeton
 */
public @interface FuzzValue {
	
	int argOrder();
	FuzzTypes type();
	int minValueSize() default 0;
	int maxValueSize() default 100;

}
