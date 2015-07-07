package uk.org.freedonia.fuzzunit.results;

import java.util.Date;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

import uk.org.freedonia.fuzzunit.FuzzTestCase;

public class RunResult {
	
	private Date startTime;
	private Date endTime;
	private String methodName;
	private boolean testPass;
	private FuzzTestCase testCase;
	private Failure fail;
	private Description description;
	
	
	
	
	public RunResult( String methodName, FuzzTestCase testCase ) {
		this.methodName = methodName;
		this.testCase = testCase;
	}
	
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public boolean isTestPass() {
		return testPass;
	}
	public void setTestPass(boolean testPass) {
		this.testPass = testPass;
	}
	public FuzzTestCase getTestCase() {
		return testCase;
	}
	public void setTestCase(FuzzTestCase testCase) {
		this.testCase = testCase;
	}

	public Failure getFail() {
		return fail;
	}

	public void setFail(Failure fail) {
		this.fail = fail;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}
	

}
