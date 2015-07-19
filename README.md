# FuzzUnit
FuzzUnit is an annotation driven Junit Runner which is an extension to the Parameterized Runner which provides random values ( within ranges ) to tests.
For Example 

	@FuzzTest( iterationCount = 2,
					fuzzLists = @FuzzList( 
							argOrder = 0,
							type = FuzzTypes.STRING ), 
					fuzzValues = {} )
	public void runTest( List<String> data  ) throws IOException {
	
	
Currently issue tracking is done via Jira. See https://fuzzunit.atlassian.net

