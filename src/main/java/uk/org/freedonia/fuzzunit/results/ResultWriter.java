package uk.org.freedonia.fuzzunit.results;

import java.util.HashMap;

import org.junit.runner.Result;

public interface ResultWriter {
	
	void writeResults( Result result, HashMap<String, RunResult> resultMap );

}
