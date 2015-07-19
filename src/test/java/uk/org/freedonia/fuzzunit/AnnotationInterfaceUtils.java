package uk.org.freedonia.fuzzunit;

import java.lang.annotation.Annotation;

import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;

public class AnnotationInterfaceUtils {
	
	public static FuzzList[] getFuzzyLists( final FuzzTypes[] types, 
			final int minElementCount, final int maxElementCount, 
			final int minValueSize, final int maxValueSize ) {
		FuzzList[] fuzzyList = new FuzzList[types.length];
		for ( int i =0; i < types.length; i++ ) {
			fuzzyList[i] = getFuzzyList( i, types[i], minElementCount, maxElementCount, minValueSize, maxValueSize );
		}
		return fuzzyList;
	}
	
	public static FuzzList getFuzzyList( final int argOrder, final FuzzTypes type, 
			final int minElementCount, final int maxElementCount, 
			final int minValueSize, final int maxValueSize ) {
		return new FuzzList(){

			@Override
			public Class<? extends Annotation> annotationType() {
				return FuzzList.class;
			}

			@Override
			public int argOrder() {
				return argOrder;
			}

			@Override
			public FuzzTypes type() {
				return type;
			}

			@Override
			public int minElementCount() {
				return minElementCount;
			}

			@Override
			public int maxElementCount() {
				return maxElementCount;
			}

			@Override
			public int minValueSize() {
				return minValueSize;
			}

			@Override
			public int maxValueSize() {
				return maxValueSize;
			}};
	}
	
	public static FuzzValue getFuzzyValue( final int argOrder, final FuzzTypes type, 
			final int minValueSize, final int maxValueSize ) {
		return new FuzzValue(){

			@Override
			public Class<? extends Annotation> annotationType() {
				return FuzzValue.class;
			}

			@Override
			public int argOrder() {
				return argOrder;
			}

			@Override
			public FuzzTypes type() {
				return type;
			}

			@Override
			public int minValueSize() {
				return minValueSize;
			}

			@Override
			public int maxValueSize() {
				return maxValueSize;
			}};
	}
	
	public static FuzzTest getFuzzy( final int iterationCount, final FuzzList[] fuzzyLists, final FuzzValue[] fuzzyValues ) {
		return new FuzzTest(){

			@Override
			public Class<? extends Annotation> annotationType() {
				return FuzzTest.class;
			}

			@Override
			public FuzzList[] fuzzLists() {
				return fuzzyLists;
			}

			@Override
			public FuzzValue[] fuzzValues() {
				return fuzzyValues;
			}

			@Override
			public int iterationCount() {
				return iterationCount;
			}};
	}

}
