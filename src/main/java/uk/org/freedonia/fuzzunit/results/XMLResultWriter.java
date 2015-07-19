package uk.org.freedonia.fuzzunit.results;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.runner.Result;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.org.freedonia.fuzzunit.FuzzUnitMethodNameCreator;

public class XMLResultWriter implements ResultWriter {

	@Override
	public void writeResults( Result result, HashMap<String, RunResult> resultMap ) {
		DocumentBuilderFactory factory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element mainRootElement = document.createElementNS( "http://fuzzunit.org/resultsDOM", "Results" );
			document.appendChild( mainRootElement );
			mainRootElement.appendChild( getStats( result, resultMap, document ) );
			mainRootElement.appendChild( getTestResults( result, resultMap, document ) );
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
 
            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(getFile());
 
            //write data
            transformer.transform(source, console);
            transformer.transform(source, file);
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Node getTestResults( Result result,
			HashMap<String, RunResult> resultMap, Document document ) {
		Element statsElement = document.createElement("TestResults");
		for ( RunResult runResult : resultMap.values() ) {
			if ( runResult.getTestCase() != null ) {
				statsElement.appendChild( getTestResult( result, runResult, document ) );
			}
		}
		return statsElement;
	}
	
	private Node getTestResult( Result result, RunResult runResult, Document document ) {
		Element statsElement = document.createElement("TestResult");
		statsElement.appendChild( getNodeElement( document, "Duration", "" + ( runResult.getEndTime().getTime() - runResult.getStartTime().getTime() ) ) );
		statsElement.appendChild( getNodeElement( document, "Success", "" + runResult.isTestPass() ) );
		statsElement.appendChild( getNodeElement( document, "MethodName", "" + getFullMethodName( runResult ) ) );

		return statsElement;
	}
	
	private String getFullMethodName( RunResult runResult ) {
		FuzzUnitMethodNameCreator creator = new FuzzUnitMethodNameCreator( runResult.getTestCase().getFuzzTest(),
				runResult.getTestCase().getValues(), runResult.getMethodName(), Integer.MAX_VALUE );
		return creator.getName();
	}

	private Element getStats( Result result, HashMap<String, RunResult> resultMap, Document doc ) {
		Element statsElement = doc.createElement("Stats");
		statsElement.appendChild( getNodeElement( doc, "TestCount", ""+result.getRunCount() ) );
		statsElement.appendChild( getNodeElement( doc, "FailureCount", ""+result.getFailureCount() ) );	
		statsElement.appendChild( getNodeElement( doc, "IgnoreCount", ""+result.getIgnoreCount() ) );
		statsElement.appendChild( getNodeElement( doc, "RunTime", ""+result.getRunTime() ) );
		statsElement.appendChild( getNodeElement( doc, "Successful", ""+result.wasSuccessful() ) );
		return statsElement;
	}
	
	private Node getNodeElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.setTextContent( StringEscapeUtils.escapeXml11(value) );
        return node;
    }
	
	private File getFile() {
		return new File("result.xml");
	}

}
