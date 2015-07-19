package uk.org.freedonia.fuzzunit;


import org.junit.runners.model.FrameworkMethod;

public class FuzzUnitTestMethod extends FrameworkMethod {

	private FuzzTestCase testCase;

	public FuzzUnitTestMethod(FrameworkMethod method, FuzzTestCase testCase ) {
		super(method.getMethod());
		this.testCase = testCase;
	}
	
	public FuzzTestCase getTestCase() {
		return testCase;
	}
	
	@Override
	public String getName() {
		return new FuzzUnitMethodNameCreator( testCase.getFuzzTest(), testCase.getValues(), getMethod().getName() ).getName();
	}

}
