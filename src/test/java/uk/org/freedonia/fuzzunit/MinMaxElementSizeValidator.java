package uk.org.freedonia.fuzzunit;

public interface MinMaxElementSizeValidator<E> {
	
	boolean isValueEqualOrLessThan( int size, E value );
	
	boolean isValueEqualOrGreaterThan( int size, E value );
	
}
