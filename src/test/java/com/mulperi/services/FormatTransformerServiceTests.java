package com.mulperi.services;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.mulperi.models.Feature;
import com.mulperi.models.ParsedModel;
import com.mulperi.models.submit.Relationship;
import com.mulperi.models.submit.Requirement;
import com.mulperi.services.FormatTransformerService;
import com.mulperi.services.KumbangModelGenerator;

public class FormatTransformerServiceTests {
	
	private FormatTransformerService transform = new FormatTransformerService();
	
	@Test
	public void smallTestCase() {
		
		ArrayList<Requirement> requirements = new ArrayList<>();
		Requirement req = new Requirement();
		req.setId("T1");
		Relationship rel = new Relationship();
		rel.setId("R2");
		rel.setType("requires");
		List<Relationship> relationships = new ArrayList<Relationship>();
		relationships.add(rel);
		req.setRelationships(relationships);
		requirements.add(req);
		
		assertEquals("Kumbang model Test\n"
			+"	 root component Test\n"
			+"	 root feature Test\n"
			+"\n"
			+"//---components-----\n"
			+"\n"
			+"component type Test {\n"
			+"}\n"
			+"\n"
			+"\n"
			+"//---features-----\n"
			+"\n"
			+"feature type Test {\n"
			+"	subfeatures\n"
			+"		T1 t1;\n"
			+"	constraints\n"
			+"		present(t1) => present(r2);\n"
			+"}\n"
			+"\n"
			+"\n"
			+"feature type T1 {\n"
			+"}\n"
			+"\n"
			+"\n", transform.SimpleToKumbang("Test", requirements));
		
	}
	
	
}