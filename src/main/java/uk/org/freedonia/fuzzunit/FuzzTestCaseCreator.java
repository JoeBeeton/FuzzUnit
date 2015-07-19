package uk.org.freedonia.fuzzunit;

import java.util.List;

import org.junit.runners.model.InitializationError;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;
import uk.org.freedonia.fuzzunit.builder.RandomBooleanListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomByteListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomCharListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomDoubleListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomFloatListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomIntegerListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomLongListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomShortListBuilder;
import uk.org.freedonia.fuzzunit.builder.RandomStringListBuilder;
import uk.org.freedonia.fuzzunit.utils.RandomUtils;

public class FuzzTestCaseCreator {
	
	private FuzzTest fuzz;
	private Class<?> klass;
	private String methodName;

	public FuzzTestCaseCreator( FuzzTest fuzz, Class<?> klass, String methodName ) {
		this.fuzz = fuzz;
		this.klass = klass;
		this.methodName = methodName;
	}
	
	public FuzzTestCase build() throws InitializationError {
		FuzzTestCase testCase = new FuzzTestCase( getArgs(), klass, methodName, fuzz );
		return testCase;
	}
	
	private Object[] getArgs() throws InitializationError {
		Object[] args = new Object[getArgCount()];
		for ( FuzzList fList : fuzz.fuzzLists() ) {
			List<?> valueList = getGeneratedListFromFuzzList( fList );
			if ( args[fList.argOrder()] != null ) {
				throw new InitializationError("Value with order : " + fList.argOrder() + " specified twice." );
			} else {
				args[fList.argOrder()] = valueList;
			}
		}
		for ( FuzzValue fValue : fuzz.fuzzValues() ) {
			Object valueList = getGenerateValueFromFuzzValue( fValue );
			if ( args[fValue.argOrder()] != null ) {
				throw new InitializationError("Value with order : " + fValue.argOrder() + " specified twice." );
			} else {
				args[fValue.argOrder()] = valueList;
			}
		}
		return args;
	}
	
	private Object getGenerateValueFromFuzzValue( FuzzValue fValue ) {
		Object value = null;
		switch ( fValue.type() ) {
			case BOOLEAN: {
				 value = RandomUtils.getRandomBoolean();
				break;
			} case FLOAT: {
				value = RandomUtils.getRandomFloatInRange( fValue.minValueSize(), fValue.maxValueSize() );
				break;
			} case INTEGER: {
				value = RandomUtils.getNumberInRange( fValue.minValueSize(), fValue.maxValueSize() );
				break;
			} case LONG: {
				value = RandomUtils.getRandomLongInRange( fValue.minValueSize(), fValue.maxValueSize() );
				break;
			} case DOUBLE: {
				value = RandomUtils.getRandomDoubleInRange( fValue.minValueSize(), fValue.maxValueSize() );
				break;
			} case SHORT: {
				value = RandomUtils.getRandomShortInRange( (short)fValue.minValueSize(), (short)fValue.maxValueSize() );
				break;
			} case STRING: {
				value = RandomUtils.getRandomStringOfLength( RandomUtils.getNumberInRange( fValue.minValueSize(), fValue.maxValueSize() ) );
				break;
			} case BYTE : {
				value = RandomUtils.getRandomByte();
				break;
			}  case CHARACTER : {
				value = RandomUtils.getRandomChar();
				break;
			} default: {
				break;
			}
		}
		return value;
	}
	
	private List<?> getGeneratedListFromFuzzList( FuzzList fList ) {
		List<?> resultList = null;
		switch ( fList.type() ) {
			case BOOLEAN: { 
				resultList =  new RandomBooleanListBuilder()
								.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() )
								.generateList();
				break;
			} case FLOAT: {
					resultList = new RandomFloatListBuilder()
						.setMinMaxValueLength( new Float( fList.minValueSize() ), new Float( fList.maxValueSize() ) )
						.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			} case INTEGER: {
				resultList = new RandomIntegerListBuilder()
						.setMinMaxValueLength( fList.minValueSize(), fList.maxValueSize() )
						.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			} case DOUBLE: {
				resultList = new RandomDoubleListBuilder()
				.setMinMaxValueLength( new Double( fList.minValueSize() ), new Double( fList.maxValueSize() ) )
						.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			}   case LONG: {
				resultList = new RandomLongListBuilder()
				.setMinMaxValueLength( new Long( fList.minValueSize() ), new Long( fList.maxValueSize() ) )
						.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			}  case SHORT: {
				resultList = new RandomShortListBuilder()
					.setMinMaxValueLength( (short) fList.minValueSize(), (short) fList.maxValueSize() )
					.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			} 
			
			case STRING: {
				resultList = new RandomStringListBuilder()
					.setMinMaxValueLength( fList.minValueSize(), fList.maxValueSize() )
					.setListOfSizeRange( fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			} case BYTE : { 
				resultList = new RandomByteListBuilder().setListOfSizeRange( 
						fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			}  case CHARACTER : { 
				resultList = new RandomCharListBuilder().setListOfSizeRange( 
						fList.minElementCount(), fList.maxElementCount() ).generateList();
				break;
			} default: {
				break;
			}
		
		}
		return resultList;
	}
	
	private int getArgCount() {
		int count = 0;
		count += fuzz.fuzzLists().length;
		count += fuzz.fuzzValues().length;
		return count;
	}

}
