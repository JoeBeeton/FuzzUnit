package uk.org.freedonia.fuzzunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;
import uk.org.freedonia.fuzzunit.results.FuzzUnitRunListener;

public class FuzzUnit extends Suite {
	
	private final HashMap<String,List<FuzzTestCase>> caseMap = new HashMap<>();
	private Class<?> klass;
	 public FuzzUnit( Class<?> klass ) throws InitializationError {
		 super( klass, Collections.<Runner>emptyList() );
		 loadCaseMap( klass );
		 this.klass = klass;
	 }
	 
	 @Override
	 public void run( RunNotifier notifier ){
	        notifier.addListener( new FuzzUnitRunListener( caseMap ) );
	        super.run(notifier);
	    }
	 
	 private void loadCaseMap( Class<?> klass ) throws InitializationError {
		 for ( Method method : klass.getMethods() ) {
			 if ( method.isAnnotationPresent( FuzzTest.class )  
					 && !method.isAnnotationPresent( Ignore.class ) ) {
				 List<FuzzTestCase> casesForMethod = getCasesForMethod( method, klass );
				 caseMap.put( method.getName(), casesForMethod );
			 } if ( method.isAnnotationPresent( Test.class )  
					 && !method.isAnnotationPresent( Ignore.class ) ) {
				 List<FuzzTestCase> casesForMethod = getCaseForNormalTest( method, klass );
				 caseMap.put( method.getName(), casesForMethod );
			 }
		 }
	 }
	 
	   /**
      * Get the test runners
      *
      * @return List<Runner>
      */
     @Override
     protected List<Runner> getChildren() {
    	 List<Runner> runners = new ArrayList<>();
    	   try {
   			runners.add( new BlockJUnit4ClassRunner( getTestClass().getJavaClass() ) );
   		} catch (InitializationError e) {
   			e.printStackTrace();
   		}
    	 for( String methodName  : caseMap.keySet() ) {
    		try {
				runners.add( new FuzzUnitTestRunner( klass,  caseMap.get(methodName), methodName ) );
			} catch (InitializationError e) {
				e.printStackTrace();
			}
    	 }
        return runners;
     }
	 
	 private List<FuzzTestCase> getCasesForMethod( Method method, Class<?> klass ) throws InitializationError {
		FuzzTest fuzz = method.getAnnotation( FuzzTest.class );
		return getCasesForFuzz( fuzz, klass, method.getName() );
	 }
	
	 
	private List<FuzzTestCase> getCaseForNormalTest( Method method, Class<?> klass ) throws InitializationError {
		FuzzTest fuzz = new FuzzTest() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return FuzzTest.class;
			}

			@Override
			public FuzzList[] fuzzLists() {
				return new FuzzList[]{};
			}

			@Override
			public FuzzValue[] fuzzValues() {
				return new FuzzValue[]{};
			}

			@Override
			public int iterationCount() {
				return 1;
			}};
		return getCasesForFuzz( fuzz, klass, method.getName() );
	}
	 
	 private List<FuzzTestCase> getCasesForFuzz( FuzzTest fuzz, Class<?> klass, String methodName ) throws InitializationError {
		 List<FuzzTestCase> cases = new ArrayList<>();
		 for ( int i =0; i < fuzz.iterationCount(); i++ ) {
			 cases.add( new FuzzTestCaseCreator(fuzz, klass, methodName).build()  );
		 }
		 return cases;
	 }




	
	

}
