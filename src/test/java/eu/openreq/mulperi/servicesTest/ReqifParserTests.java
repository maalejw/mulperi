package eu.openreq.mulperi.servicesTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import eu.openreq.mulperi.models.reqif.SpecObject;
import eu.openreq.mulperi.services.ReqifParser;

public class ReqifParserTests {
	
	private ReqifParser parser = new ReqifParser();
	
	@Test
	public void mapApplicationTestCase() throws Exception {
		
		Scanner scanner = new Scanner( new File("src/test/resources/MapAppV01.reqif") );
		String text = scanner.useDelimiter("\\A").next();
		HashMap<String, SpecObject> specObjects = parser.parse(text);
		scanner.close(); 
		
		assertEquals(5, specObjects.size());
		
		assertTrue(specObjects.containsKey("_SMsjsCqMEee4xasBBKtviQ"));
		SpecObject object1 = specObjects.get("_SMsjsCqMEee4xasBBKtviQ");
		assertEquals(1, object1.getTargetsOf().size());
		assertEquals(0, object1.getSourcesOf().size());
		assertEquals("REQ-6", object1.getForeignId());
		assertFalse(object1.isMandatory());
		assertEquals("_JDs0UCqLEee4xasBBKtviQ", object1.getParent().getId());
		
		assertTrue(specObjects.containsKey("_XS_kkCqLEee4xasBBKtviQ"));
		SpecObject object2 = specObjects.get("_XS_kkCqLEee4xasBBKtviQ");
		assertEquals(0, object2.getTargetsOf().size());
		assertEquals(0, object2.getSourcesOf().size());
		assertEquals("REQ-4", object2.getForeignId());
		assertTrue(object2.isMandatory());
		assertEquals("_JDs0UCqLEee4xasBBKtviQ", object2.getParent().getId());
		
		assertTrue(specObjects.containsKey("_a93eYCqMEee4xasBBKtviQ"));
		SpecObject object3 = specObjects.get("_a93eYCqMEee4xasBBKtviQ");
		assertEquals(0, object3.getTargetsOf().size());
		assertEquals(2, object3.getSourcesOf().size());
		assertEquals("REQ-7", object3.getForeignId());
		assertFalse(object3.isMandatory());
		assertEquals("_JDs0UCqLEee4xasBBKtviQ", object3.getParent().getId());
		
		assertTrue(specObjects.containsKey("_JDs0UCqLEee4xasBBKtviQ"));
		SpecObject object4 = specObjects.get("_JDs0UCqLEee4xasBBKtviQ");
		assertEquals(0, object4.getTargetsOf().size());
		assertEquals(0, object4.getSourcesOf().size());
		assertEquals("REQ-3", object4.getForeignId());
		assertFalse(object4.isMandatory());
		assertNull(object4.getParent());
	}
	
	
}
