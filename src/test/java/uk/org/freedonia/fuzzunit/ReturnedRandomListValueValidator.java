package uk.org.freedonia.fuzzunit;

import java.util.List;

import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;

public class ReturnedRandomListValueValidator extends ListValidatorBase {



	public void validateListValue( List<?> values, int minListSize,
			int maxListSize, int minElementSize, int maxElementSize, FuzzTypes type ) {
		validateThatAllElementsAreNotNull( values );
		validateListSizeInRange( values, minListSize, maxListSize );
		for (Object object : values) {
			validateElementSizes( object, minElementSize, maxElementSize, type );
		}
	}
	
	
	private void validateElementSizes( Object value, int minElementSize,
			int maxElementSize, FuzzTypes type ) {
		switch ( type ) {
			case BOOLEAN:
				break;
			case BYTE:
				break;
			case CHARACTER:
				break;
			case DOUBLE:
				if ( value instanceof Double ) {
					getDoubleMinMaxElementSizeValidator().isValueEqualOrGreaterThan( minElementSize, (Double) value );
					getDoubleMinMaxElementSizeValidator().isValueEqualOrLessThan( maxElementSize, (Double) value );
				}
				break;
			case FLOAT:
				if ( value instanceof Float ) {
					getFloatMinMaxElementSizeValidator().isValueEqualOrGreaterThan( minElementSize, (Float) value );
					getFloatMinMaxElementSizeValidator().isValueEqualOrLessThan( maxElementSize, (Float) value );
				}
				break;
			case INTEGER:
				if ( value instanceof Integer ) {
					getIntegerMinMaxElementSizeValidator().isValueEqualOrGreaterThan( minElementSize, (Integer) value );
					getIntegerMinMaxElementSizeValidator().isValueEqualOrLessThan( maxElementSize, (Integer) value );
				}
				break;
			case LONG:
				if ( value instanceof Long ) {
					getLongMinMaxElementSizeValidator().isValueEqualOrGreaterThan( minElementSize, (Long) value );
					getLongMinMaxElementSizeValidator().isValueEqualOrLessThan( maxElementSize, (Long) value );
				}
				break;
			case SHORT:
				if ( value instanceof Short ) {
					getShortMinMaxElementSizeValidator().isValueEqualOrGreaterThan( minElementSize, (Short) value );
					getShortMinMaxElementSizeValidator().isValueEqualOrLessThan( maxElementSize, (Short) value );
				}
				break;
			case STRING:
				if ( value instanceof String ) {
					getStringMinMaxElementSizeValidator().isValueEqualOrGreaterThan( minElementSize, (String) value );
					getStringMinMaxElementSizeValidator().isValueEqualOrLessThan( maxElementSize, (String) value );
				}
				break;
			default:
				break;
		}
	}
	
	private MinMaxElementSizeValidator<Short> getShortMinMaxElementSizeValidator() {
		return new MinMaxElementSizeValidator<Short>(){
			@Override
			public boolean isValueEqualOrLessThan( int size,
					Short value ) {
				return value <= size ;
			}

			@Override
			public boolean isValueEqualOrGreaterThan( int size,
					Short value ) {
				return value >= size ;
			}};
	}
	
	private MinMaxElementSizeValidator<Long> getLongMinMaxElementSizeValidator() {
		return new MinMaxElementSizeValidator<Long>(){
			@Override
			public boolean isValueEqualOrLessThan( int size,
					Long value ) {
				return value <= size ;
			}

			@Override
			public boolean isValueEqualOrGreaterThan( int size,
					Long value ) {
				return value >= size ;
			}};
	}
	
	private MinMaxElementSizeValidator<Double> getDoubleMinMaxElementSizeValidator() {
		return new MinMaxElementSizeValidator<Double>(){
			@Override
			public boolean isValueEqualOrLessThan( int size,
					Double value ) {
				return value <= size ;
			}

			@Override
			public boolean isValueEqualOrGreaterThan( int size,
					Double value ) {
				return value >= size ;
			}};
	}
	
	private MinMaxElementSizeValidator<Float> getFloatMinMaxElementSizeValidator() {
		return new MinMaxElementSizeValidator<Float>(){
			@Override
			public boolean isValueEqualOrLessThan( int size,
					Float value ) {
				return value <= size ;
			}

			@Override
			public boolean isValueEqualOrGreaterThan( int size,
					Float value ) {
				return value >= size ;
			}};
	}
	
	
	private MinMaxElementSizeValidator<Integer> getIntegerMinMaxElementSizeValidator() {
		return new MinMaxElementSizeValidator<Integer>(){
			@Override
			public boolean isValueEqualOrLessThan( int size,
					Integer value ) {
				return value <= size ;
			}

			@Override
			public boolean isValueEqualOrGreaterThan( int size,
					Integer value ) {
				return value >= size ;
			}};
	}
	
	private MinMaxElementSizeValidator<String> getStringMinMaxElementSizeValidator() {
		return new MinMaxElementSizeValidator<String>(){
				@Override
				public boolean isValueEqualOrLessThan( int size,
						String value ) {
					return value.length() <= size ;
				}

				@Override
				public boolean isValueEqualOrGreaterThan( int size,
						String value ) {
					return value.length() >= size ;
				}};
	}
	
	
}
