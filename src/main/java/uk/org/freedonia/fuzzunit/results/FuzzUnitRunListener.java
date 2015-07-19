package uk.org.freedonia.fuzzunit.results;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import uk.org.freedonia.fuzzunit.FuzzTestCase;

public class FuzzUnitRunListener extends RunListener {
	
	private HashMap<String,List<FuzzTestCase>> caseMap;
	private HashMap<String, RunResult> resultMap;
	
	public FuzzUnitRunListener( HashMap<String,List<FuzzTestCase>> caseMap ) {
		this.caseMap = caseMap;
		this.resultMap = new HashMap<>();
	}
	
	@Override
	public void testRunStarted( Description desc ) {
		startTest( desc );
	}
	@Override
	public void testStarted( Description desc ) {
		startTest( desc );
	}
	
	private void startTest( Description desc ) {
		String methodName = desc.getMethodName();
		FuzzTestCase casey = getCaseFromMethodName( methodName );
		RunResult runResult = new RunResult( methodName, casey );
		runResult.setStartTime( new Date() );	
		resultMap.put( methodName, runResult );
	}
	
	private FuzzTestCase getCaseFromMethodName( String methodName ) {
		FuzzTestCase foundCase = null;
		for ( List<FuzzTestCase> casesx : caseMap.values() ) {
			for (  FuzzTestCase casey : casesx ) {
				if ( casey.getName().equals( methodName ) ){
					foundCase = casey;
					break;
				}
			}
			if ( foundCase != null ) {
				break;
			}
		}
		return foundCase;
	}
	
	private RunResult getResultAtEnd( String methodName ) {
		RunResult result = resultMap.get( methodName );
		result.setEndTime( new Date() );
		return result;
	}

	@Override
	public void testFailure( Failure f ) {
		RunResult result = getResultAtEnd( f.getDescription().getMethodName() );
		result.setTestPass(false);
		result.setFail( f );
		result.setDescription(f.getDescription());
	}
	@Override
	public void testAssumptionFailure( Failure f ) {
		RunResult result = getResultAtEnd( f.getDescription().getMethodName() );
		result.setTestPass( false );
		result.setFail( f );
		result.setDescription(f.getDescription());

	}
	@Override
	public void testFinished( Description desc ) {
		System.out.println( desc );
		RunResult result = getResultAtEnd( desc.getMethodName() );
		result.setTestPass( true );
	}
	@Override
	public void testRunFinished( Result result ) {
		System.out.println( result );
		getResultWriter().writeResults( result, resultMap );
	}
	
	private ResultWriter getResultWriter() {
		return new XMLResultWriter();
	}

}
