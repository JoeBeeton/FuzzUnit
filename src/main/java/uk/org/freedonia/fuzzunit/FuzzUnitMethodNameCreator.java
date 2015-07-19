package uk.org.freedonia.fuzzunit;

import java.util.HashMap;

import uk.org.freedonia.fuzzunit.annotations.FuzzList;
import uk.org.freedonia.fuzzunit.annotations.FuzzTest;
import uk.org.freedonia.fuzzunit.annotations.FuzzTypes;
import uk.org.freedonia.fuzzunit.annotations.FuzzValue;

public class FuzzUnitMethodNameCreator {
	
	private FuzzTest fuzz;
	private Object[] values;
	private String defaultName;
	private int maxNameSize;

	public FuzzUnitMethodNameCreator( FuzzTest fuzz, Object[] values, String defaultName ) {
		this( fuzz, values, defaultName, 50 );
	}
	
	public FuzzUnitMethodNameCreator( FuzzTest fuzz, Object[] values, String defaultName, int maxNameSize ) {
		this.fuzz = fuzz;
		this.values = values;
		this.defaultName = defaultName;
		this.maxNameSize = maxNameSize;
	}
	
	
	

	public String getName() {
		HashMap<Integer, String> indexNameMap = getIndexNameMap();
		StringBuilder name = new StringBuilder();
		if ( values.length > 0 ) {
			for ( int i = 0; i < values.length; i++ ) {
				name.append("[");
				name.append( indexNameMap.get(i) );
				name.append( " {" + values[i] + "}" );
				name.append("] ");
			}
		} else {
			name.append( defaultName );
		}
		
		return getNameTrimmedTo( maxNameSize, name.toString() );
	}
	
	
	
	private String getNameTrimmedTo( int trimLength, String name ) {
		if ( name.length() > trimLength ) {
			return name.substring( 0, trimLength ) + "...";
		} else {
			return name;
		}
	}
	
	private HashMap<Integer, String> getIndexNameMap() {
		HashMap<Integer, String> indexNameMap = new HashMap<>();
		for ( FuzzList list : fuzz.fuzzLists() ) {
			indexNameMap.put( list.argOrder(), getListValueTypeName( list.type() ) );
		}
		for ( FuzzValue value : fuzz.fuzzValues() ) {
			indexNameMap.put( value.argOrder(), getSingleValueTypeName( value.type() ) );
		}
		return indexNameMap;
	}

	private String getListValueTypeName( FuzzTypes type ) {
		return "List<"+ type.name() + ">";
	}
	
	private String getSingleValueTypeName( FuzzTypes type ) {
		return type.name();
	}
	

}
