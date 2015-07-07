package uk.org.freedonia.fuzzunit;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;



public class FuzzTestCase {
	
	private Object[] values;
	private Class<?> klass;
	private String methodName;
	private FuzzTest fuzzTest;
	
	public FuzzTestCase( Object[] args, Class<?> klass, String methodName, FuzzTest fuzzTest ) {
		this.values = args;
		this.klass = klass;
		this.methodName = methodName;
		this.fuzzTest = fuzzTest;
	}
	
	public String getName() {
		return new FuzzUnitMethodNameCreator(fuzzTest, values, methodName ).getName();
	}

	public Class<?> getKlass() {
		return klass;
	}

	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public FuzzTest getFuzzTest() {
		return fuzzTest;
	}

	public void setFuzzTest(FuzzTest fuzzTest) {
		this.fuzzTest = fuzzTest;
	}


}
