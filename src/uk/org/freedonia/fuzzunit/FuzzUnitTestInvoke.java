package uk.org.freedonia.fuzzunit;

import org.junit.internal.runners.statements.InvokeMethod;

public class FuzzUnitTestInvoke extends InvokeMethod {

	private FuzzUnitTestMethod testMethod;
	private Object target;

	public FuzzUnitTestInvoke( FuzzUnitTestMethod testMethod, Object target) {
		super(testMethod, target);
		this.testMethod = testMethod;
		this.target = target;
	}
	
	  @Override
      public void evaluate() throws Throwable {
              testMethod.invokeExplosively( target, testMethod.getTestCase().getValues() );
      }

}
