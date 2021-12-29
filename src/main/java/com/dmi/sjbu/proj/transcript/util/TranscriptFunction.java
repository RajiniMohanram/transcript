package com.dmi.sjbu.proj.transcript.util;

import java.util.function.Function;

import com.dmi.sjbu.proj.transcript.model.TranscriptLineObject;

public class TranscriptFunction implements Function<String, TranscriptLineObject>{

	@Override
	public TranscriptLineObject apply(String t) {
		TranscriptLineObject obj = null;
		String v[] = t.split(",");
		String studyCenter = v[0].trim(); 
		String programme = v[1].trim();   
		String regNo = v[2].trim();       
		String studName = v[3].trim();    
		String gender = v[4].trim();      
		String dob = v[5].trim();         
		String doe = v[6].trim();         
		double cgpa = Double.parseDouble(v[7].trim().isEmpty()?"0":v[7].trim());        
		String classOfAward = v[8].trim();
		String lastAppear = v[9].trim();  
		String doi = v[10].trim();         
		int semester = Integer.parseInt(v[11].trim().isEmpty()?"0":v[11].trim());       
		int moduleOrder = Integer.parseInt(v[12].trim().isEmpty()?"0":v[12].trim());
		String moduleCode = v[13].trim();  
		String moduleName = v[14].trim();  
		double grade = Double.parseDouble(v[15].trim().isEmpty()?"0":v[15].trim());       
		String appeared = v[16].trim();
		
		obj = new TranscriptLineObject(studyCenter, programme, regNo, studName, gender, dob, doe, cgpa, classOfAward, lastAppear, doi, semester, moduleOrder, moduleCode, moduleName, grade, appeared);
		
		return obj;
	}

}
