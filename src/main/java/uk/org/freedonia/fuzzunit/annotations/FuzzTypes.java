package uk.org.freedonia.fuzzunit.annotations;

/**
 * The object types that can be passed as method arguments to the test method.
 * Currently the support types are all of the primitive types via their respective wrapper classes and
 * Strings.
 * @author jbeeton
 *
 */
public enum FuzzTypes {

	/**
	 * Denotes a java.lang.String.
	 */
	STRING,
	/**
	 * Denotes a java.lang.Short
	 */
	SHORT,
	/**
	 * Denotes a java.lang.Integer
	 */
	INTEGER,
	/**
	 * Denotes a java.lang.Float
	 */
	FLOAT,
	/**
	 * Denotes a java.lang.Long
	 */
	LONG,
	/**
	 * Denotes a java.lang.Double
	 */
	DOUBLE,
	/**
	 * Denotes a java.lang.Boolean
	 */
	BOOLEAN,
	/**
	 * Denotes a java.lang.Byte
	 */
	BYTE,
	/**
	 * Denotes a java.lang.Character
	 */
	CHARACTER

}
