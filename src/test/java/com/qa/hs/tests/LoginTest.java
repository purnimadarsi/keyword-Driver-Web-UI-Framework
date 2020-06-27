package com.qa.hs.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine keyWordEngine;
	
	@Test
	public void loginTest() throws InvalidFormatException, IOException {
		keyWordEngine = new KeywordEngine();
		keyWordEngine.startExecution("login");
		
	}

}
