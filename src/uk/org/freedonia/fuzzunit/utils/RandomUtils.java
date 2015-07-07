package uk.org.freedonia.fuzzunit.utils;

import java.security.SecureRandom;



/**
 * RandomUtils is a Utility class used to generate randomish data. This means
 * that it is random enough for testing purposes. But I wouldn't trust it for generating random data
 * for a secure purpose.
 * @author jbeeton
 *
 */
public class RandomUtils {
	
	/**
	 * The secureRandom instance.
	 */
	private static SecureRandom secureRandom = new SecureRandom();

	/**
	 * Returns a random int between the specified ranges
	 * @param min the minimum size of the random value to be returned
	 * @param max the maximum size of the value to be returned.
	 * @return the random value.
	 */
	public static int getNumberInRange( int min, int max ) {
		return secureRandom.nextInt(max - min + 1) + min;
	}
	
	
	/**
	 * returns a string containing random characters of the specified length
	 * @param length the length of the random string to return
	 * @return the random string
	 */
	public static String getRandomStringOfLength( int length ) {
		byte[] byteArray = new byte[length];
		secureRandom.nextBytes(byteArray);
		return new String( byteArray );
	}
	
	/**
	 * returns a random boolean. 
	 * @return a random boolean value.
	 */
	public static boolean getRandomBoolean() {
		return secureRandom.nextBoolean();
	}
	
	/**
	 * returns a single random byte.
	 * @return a random byte.
	 */
	public static byte getRandomByte() {
		return getRandomByteArrayOfLength(1)[0];
	}
	
	/**
	 * returns a random byte array of the specified length.
	 * @param length the length of the byte array to return
	 * @return the returned byte array containing random bytes.
	 */
	public static byte[] getRandomByteArrayOfLength( int length ) {
		byte[] bytes = new byte[length];
		secureRandom.nextBytes(bytes);
		return bytes;
	}
	
	
	/**
	 * Returns a random char.
	 * @return a random char.
	 */
	public static char getRandomChar() {
		return (char)( secureRandom.nextInt( 26 + 'a' ) );
	}
	
	/**
	 * returns a random char array of the specified length.
	 * @param length the length of the random char array to be returned.
	 * @return the random char array.
	 */
	public static char[] getRandomCharArrayOfLength( int length ) {
		char[] cArray = new char[length];
		for ( int i = 0; i < length; i++ ) {
			cArray[i] = getRandomChar(); 
		}
		return cArray;
	}
	
	
	/**
	 * returns a random short within the specified range.
	 * @param min the minimum size of the random short.
	 * @param max the maximum size of the random short.
	 * @return the random short.
	 */
	public static short getRandomShortInRange( short min, short max ) {
		return (short) ((short)secureRandom.nextInt((short)max - (short)min + 1) + (short)min);
	}
	
	/**
	 * returns a random long within the specified range.
	 * @param min the minimum size of the long.
	 * @param max the maximum size of the long.
	 * @return a random long between the specified ranges.
	 */
	public static long getRandomLongInRange( long min, long max ) {
		return (long) (secureRandom.nextDouble()*( max - min ));
	}
	
	/**
	 * returns a random double within the specified range.
	 * @param min the minimum size of the double.
	 * @param max the maximum size of the double.
	 * @return a random double between the specified ranges.
	 */
	public static double getRandomDoubleInRange( double min, double max ) {
		  double range = max - min;
		  double scaled = secureRandom.nextDouble() * range;
		  double shifted = scaled + min;
		  return shifted;
	}
	
	
	/**
	 * returns a random float within the specified range.
	 * @param min the minimum size of the float.
	 * @param max the maximum size of the float.
	 * @return a random float between the specified ranges.
	 */
	public static float getRandomFloatInRange( float min, float max ) {
		 float range = max - min;
		 float scaled = secureRandom.nextFloat() * range;
		 float shifted = scaled + min;
		 return shifted;
	}
	
	
}
