package com.dmi.sjbu.proj.transcript.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dmi.sjbu.proj.transcript.model.ModuleInfo;
import com.dmi.sjbu.proj.transcript.model.Transcript;
import com.dmi.sjbu.proj.transcript.model.TranscriptLineObject;

public class TranscriptDAO {
	public static List<TranscriptLineObject> readFile(String file) {
		List<TranscriptLineObject> trans = new ArrayList<>();
		try (Stream<String> lines = Files.lines(Paths.get(file), StandardCharsets.ISO_8859_1)) {
			TranscriptFunction func = new TranscriptFunction();
			trans = lines.map(func).collect(Collectors.toList());
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return trans;
	}

	public static int uploadRecords(String file) {
		int count = 0;
		List<TranscriptLineObject> trans = readFile(file);
		try {
			Connection con = AppUtil.getConnection();
			String query = "INSERT INTO transcript values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			for (TranscriptLineObject t : trans) {
				ps.setString(1, t.getStudyCenter());
				ps.setString(2, t.getProgramme());
				ps.setString(3, t.getRegNo());
				ps.setString(4, t.getStudName());
				ps.setString(5, t.getGender());
				ps.setString(6, t.getDob());
				ps.setString(7, t.getDoe());
				ps.setDouble(8, t.getCgpa());
				ps.setString(9, t.getClassOfAward());
				ps.setString(10, t.getLastAppear());
				ps.setString(11, t.getDoi());
				ps.setInt(12, t.getSemester());
				ps.setInt(13, t.getModuleOrder());
				ps.setString(14, t.getModuleCode());
				ps.setString(15, t.getModuleName());
				ps.setDouble(16, t.getGrade());
				ps.setString(17, t.getAppeared());

				ps.addBatch();
			}
			int counts[] = ps.executeBatch();
			ps.clearBatch();
			ps.clearParameters();
			
			for (int c : counts) {
				count += c;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return count;
	}

	public static Transcript getTranscript(String regNo) {
		Transcript t = new Transcript();
		String sql1 = "SELECT study_center, programme, registration_no, stud_name, gender, "
				+ "dob, doe, cgpa, class_of_award, last_appear, doi"
				+ " FROM transcript WHERE registration_no = ? GROUP BY registration_no";
		String sql2 = "SELECT semester, module_order, module_code, module_name, grade, appeared"
				+ " FROM transcript WHERE registration_no = ? ORDER BY semester, module_order";
		Connection con = AppUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, regNo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				t.setStudyCenter(rs.getString(1));
				t.setProgramme(rs.getString(2));
				t.setRegNo(rs.getString(3));
				t.setStudName(rs.getString(4));
				t.setGender(rs.getString(5));
				t.setDob(LocalDate.parse(rs.getString(6)));
				t.setDoe(rs.getString(7));
				t.setCgpa(rs.getDouble(8));
				t.setClassOfAward(rs.getString(9));
				t.setLastAppear(rs.getString(10));
				t.setDoi(LocalDate.parse(rs.getString(11)));
				Map<Integer,List<ModuleInfo>> modules = t.getModules();
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, regNo);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					ModuleInfo m = new ModuleInfo();
					m.setSemester(rs2.getInt(1));
					m.setModuleOrder(rs2.getInt(2));
					m.setModuleCode(rs2.getString(3));
					m.setModuleName(rs2.getString(4));
					m.setGrade(rs2.getDouble(5));
					m.setAppeared(rs2.getString(6));
					if(modules.containsKey(m.getSemester())) {
						modules.get(m.getSemester()).add(m);
					}else {
						List<ModuleInfo> mlist = new ArrayList<ModuleInfo>();
						mlist.add(m);
						modules.put(m.getSemester(), mlist);
					}
				}
				ps2.clearParameters();
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return t;
	}
}
