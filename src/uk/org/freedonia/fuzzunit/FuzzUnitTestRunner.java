package uk.org.freedonia.fuzzunit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.MethodRule;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import uk.org.freedonia.fuzzunit.annotations.FuzzTest;



public class FuzzUnitTestRunner  extends ParentRunner<FrameworkMethod>{

	private List<FuzzTestCase> testCases;
	private String name;
	
	
	public FuzzUnitTestRunner( Class<?> testClass, List<FuzzTestCase> testCases, String name ) throws InitializationError {
		super(testClass);
		this.testCases = testCases;
		this.name = name;
	}
	
	  @Override
      public String getName()  {
         return this.name;
      }

    /**
     * Uniquely identify the tests
     *
     * @param method The test method
     * @return a brief description of the tests
     */
    @Override
    protected Description describeChild( FrameworkMethod method ) {
            return Description.createTestDescription(
                                                                    getTestClass().getClass(),
                                                                    method.getName(),
                                                                    method.getAnnotations()
            );
    }

	@Override
	protected List<FrameworkMethod> getChildren() {
		List<FrameworkMethod> methods = new ArrayList<FrameworkMethod>();
        FrameworkMethod fm = getFrameworkMethod( name );
        if( fm != null )
                for( FuzzTestCase tc : testCases ) {
                        methods.add( new FuzzUnitTestMethod( fm, tc ) );
                }

        return methods;
	}
	
	   protected FrameworkMethod getFrameworkMethod( String methodName )
       {
               for( FrameworkMethod fm : getTestClass().getAnnotatedMethods( FuzzTest.class ) ) {
                       if( fm.getName().equals( methodName ) )
                               return fm;
               }

               return null;
       }

	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		EachTestNotifier eachNotifier = makeNotifier( method, notifier );
        FuzzUnitTestMethod tTestMethod = (FuzzUnitTestMethod) method;

        eachNotifier.fireTestStarted();
        if( method.getAnnotation( Ignore.class ) != null  ) {
                eachNotifier.fireTestIgnored();
                return;
        }
        
        try {
            
            methodBlock( tTestMethod ).evaluate();

         
            } catch (Throwable e) {
				eachNotifier.addFailure( e );
			} finally {
            	  eachNotifier.fireTestFinished();
            }
        }
        
		
	
    /**
     * Create the test
     * @return the test class
     * @throws Exception per getTestClass
     */
    public Object createTest() throws Exception
    {
            return getTestClass().getOnlyConstructor().newInstance();
    }
	
    protected Statement methodBlock( FuzzUnitTestMethod testMethod )
    {
            Object test;
            try {
                    test = new ReflectiveCallable()
                    {
                            @Override
                            protected Object runReflectiveCall() throws Throwable
                            {
                                    return createTest();
                            }
                    }.run();
            } catch( Throwable e ) {
                    return new Fail( e );
            }

            Statement statement = methodInvoker( testMethod, test );
            statement = withRules( testMethod, test, statement );
            return statement;
    }
    
    private Statement withRules( FrameworkMethod method, Object target, Statement statement )
    {
            Statement result = statement;
            for( MethodRule each : rules( target ) )
                    result = each.apply( result, method, target );
            return result;
    }
    
    /**
     * @param test the test class
     * @return the MethodRules that can transform the block
     *         that runs each method in the tested class.
     */
    protected List<MethodRule> rules( Object test )
    {
            List<MethodRule> results = new ArrayList<MethodRule>();
            for( FrameworkField each : ruleFields() )
                    results.add( createRule( test, each ) );
            return results;
    }
    
    private List<FrameworkField> ruleFields()
    {
            return getTestClass().getAnnotatedFields( Rule.class );
    }

    private MethodRule createRule( Object test, FrameworkField each )
    {
            try {
                    return (MethodRule) each.get( test );
            } catch( IllegalAccessException e ) {
                    throw new RuntimeException( "How did getFields return a field we couldn't access?" );
            } catch( IllegalArgumentException ex ) {
                    throw new RuntimeException( "How did getFields return a field we couldn't access?" );
            }
    }
    
    /**
     * Returns a {@link Statement} that invokes {@code method} on {@code test}
     *
     * @param testMethod the method and a test case
     * @param test the actual test
     * @return Statement
     */
    protected Statement methodInvoker( FuzzUnitTestMethod testMethod, Object test )
    {
            return new FuzzUnitTestInvoke( testMethod, test );
    }
	
	
    /**
     * @param method the test
     * @param notifier a test run notifier
     * @return the married test and notifier
     */
    protected EachTestNotifier makeNotifier( FrameworkMethod method, RunNotifier notifier )
    {
            return new EachTestNotifier( notifier, describeChild( method ) );
    }

}
