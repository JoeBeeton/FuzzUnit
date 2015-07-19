package uk.org.freedonia.fuzzunit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @FuzzTest is a Method level annotation used to denote methods which will be run via FuzzUnit.
 * In much the same way as @Test annotation denotes a test method in Junit.
 * For Example : 
 * <pre>
 * {@code	
 *  @FuzzTest( iterationCount = 100,
 *			fuzzLists = { @FuzzList( 
 *				argOrder = 0,
 *				type = FuzzTypes.STRING ) }, 
 *			fuzzValues = { @FuzzValue(
 *				argOrder = 1,
 *				type = FuzzTypes.BOOLEAN,
 *				),
 *					@FuzzyValue(
 *						argOrder = 2,
 *						type = FuzzTypes.INTEGER,
 *						minValueSize = 1000,
 *						maxValueSize = 2000
 *			 } )
 *		)
 * 
 * {@code
 * public void exampleTest( List<String> randomStringList, Boolean randomTrueFalse, Integer randomNumber )
 * }
 * </pre>
 * The Above code example would run the test method exampleTest 100 times. Each execution of that test would be passed 
 * random values based on the definition within the @FuzzTest annotation.
 * The {@code List<String> randomStringList } would be a random List of Strings. As the size of the list and size of the Strings within the list
 * is not explicitly  defined in the above example the defaults are used. So the List will be between 0 and 1000 elements in size and each String 
 * value will be between 0 and 100 characters in length.
 * The {@code Boolean randomTrueFalse } will be a randomly chosen Boolean value.
 * The {@code Integer randomNumber } will be a random Integer with a size between 1000 2000.
 *
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface FuzzTest {
	
	/**
	 * The Array of FuzzList values. Each FuzzList defines a passed in list argument to the method.
	 * @return The array of FuzzLists.
	 */
	FuzzList[] fuzzLists();
	/**
	 * The array of FuzzValue values. Each FuzzValue defines the random value that will be passed to the 
	 * test method as an argument.
	 * @return the array of FuzzValues.
	 */
	FuzzValue[] fuzzValues();
	/**
	 * The amount of times this test should be run. Defaults to 1
	 * @return the amount of times this test should be run.
	 */
	int iterationCount() default 1;
	

}
