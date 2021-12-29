package com.dmi.sjbu.proj.transcript.service;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.dmi.sjbu.proj.transcript.model.ModuleInfo;
import com.dmi.sjbu.proj.transcript.model.Transcript;
import com.dmi.sjbu.proj.transcript.util.AppUtil;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;

public class TranscriptService {
	public static void printTranscript(Transcript t) {
		System.out.println("Student Name: " + t.getStudName());
		System.out.println("Registration No: " + t.getRegNo());
		System.out.println("=================================");
		for (int sem : t.getModules().keySet()) {
			for (ModuleInfo m : t.getModules().get(sem)) {
				System.out.printf("%-5s %-2d %-12s %-35s %-10s\n", toRoman(m.getSemester()), m.getModuleOrder(),
						m.getModuleCode(), m.getModuleName(), m.getAppeared());
			}
		}
	}

	private static String toRoman(int n) {
		String[] romanCodes = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
		return romanCodes[n - 1];
	}

	public static String generatePdf(Transcript t) {
		try {
			String fileName = t.getRegNo() + ".pdf";
			File file = new File(fileName);
			if(file.exists()) {
				return "Transcript already exists";
			}
			
			PdfWriter writer = new PdfWriter(fileName);
			PdfDocument pdfDoc = new PdfDocument(writer);
			PdfPage pdfPage = pdfDoc.addNewPage(PageSize.A4);
			Document doc = new Document(pdfDoc);
			doc.setMargins(30, 50, 30, 50);
			PdfFont headingFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			PdfFont contFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

			Image logo = new Image(ImageDataFactory.create("src/main/resources/dmisjbu.jpg"));
			logo.setFixedPosition(50, 725);
			logo.scaleAbsolute(65, 75);
			doc.add(logo);

			Text universityName = new Text("DMI - ST. JOHN THE BAPTIST UNIVERSITY").setFont(headingFont).setFontSize(20)
					.setFontColor(Color.BLUE);
			Paragraph university = new Paragraph().add(universityName).setMarginTop(0).setMarginBottom(0);
			university.setTextAlignment(TextAlignment.CENTER).setFirstLineIndent(65);
			doc.add(university);

			Paragraph line1 = new Paragraph(String.format("%-126s %-50s", "Main Campus", "Lilongwe Campus"));
			Paragraph line2 = new Paragraph(
					String.format("%-117s %-50s", "P.O.BoxNo.406,Mangochi", "P.O.BoxNo.2398,Lilongwe"));
			Paragraph line3 = new Paragraph(
					String.format("%-122s %-50s", "Malawi,CentralAfrica", "Malawi,CentralAfrica"));
			Paragraph line4 = new Paragraph(String.format("%-107s %-50s", "Phone: +265991287235, +2651599790",
					"Phone: +265881047088, +265992371901"));
			Paragraph line5a = new Paragraph(String.format("%-50s", "Emailid:dmisjbu@gmail.com"));
			Text line5b = new Text(String.format("%-47s", "www.dmisjbu.edu.mw"));
			Text line5c = new Text("Emailid:dmisjbulilongwe@gmail.com");

			line1.setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(75).setMarginTop(0).setMarginBottom(0);
			line2.setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(75).setMarginTop(0).setMarginBottom(0);
			line3.setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(75).setMarginTop(0).setMarginBottom(0);
			line4.setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(75).setMarginTop(0).setMarginBottom(0);
			line5a.setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(75).setMarginTop(0).setMarginBottom(0);
			line5b.setFont(contFont).setFontSize(8).setBold().setFontColor(Color.BLUE);
			line5c.setFont(contFont).setFontSize(8).setBold();
			doc.add(line1);
			doc.add(line2);
			doc.add(line3);
			doc.add(line4);
			line5a.add(line5b).add(line5c);
			doc.add(line5a);

			PdfCanvas canvas = new PdfCanvas(pdfPage);
			canvas.moveTo(50, 720);
			canvas.lineTo(550, 720);
			canvas.closePathStroke();

			Paragraph transcriptTitle = new Paragraph("TRANSCRIPT");
			transcriptTitle.setFont(contFont).setFontSize(12).setBold().setUnderline()
					.setTextAlignment(TextAlignment.CENTER).setFirstLineIndent(15).setMarginTop(10);
			doc.add(transcriptTitle);

			Paragraph pName = new Paragraph("Name of the Student:").setFont(contFont).setFontSize(12).setMarginTop(0)
					.setMarginBottom(0);
			Paragraph pRegNo = new Paragraph("Registration Number:").setFont(contFont).setFontSize(12).setMarginTop(0)
					.setMarginBottom(0);
			Paragraph pDob = new Paragraph("Date Of Birth:").setFont(contFont).setFontSize(12).setMarginTop(0)
					.setMarginBottom(0);
			Paragraph pProg = new Paragraph("Programme:").setFont(contFont).setFontSize(12).setMarginTop(0)
					.setMarginBottom(0);
			Paragraph pDur = new Paragraph("Duration:").setFont(contFont).setFontSize(12).setMarginTop(0)
					.setMarginBottom(0);
			Paragraph pYop = new Paragraph("Year Of Passing:").setFont(contFont).setFontSize(12).setMarginTop(0)
					.setMarginBottom(0);

			Paragraph valName = new Paragraph(t.getStudName()).setFont(contFont).setFontSize(12).setBold()
					.setMarginTop(0).setMarginBottom(0);
			Paragraph valRegNo = new Paragraph(t.getRegNo()).setFont(contFont).setFontSize(12).setBold().setMarginTop(0)
					.setMarginBottom(0);
			Paragraph valDob = new Paragraph(t.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
					.setFont(contFont).setFontSize(12).setBold().setMarginTop(0).setMarginBottom(0);
			Paragraph valProg = new Paragraph(t.getProgramme()).setFont(contFont).setFontSize(12).setBold()
					.setMarginTop(0).setMarginBottom(0);
			String doe = t.getDoe();
			String begYr = doe.substring(doe.indexOf("-") + 1);
			Paragraph valDur = new Paragraph(begYr + " - " + t.getDoi().getYear()).setFont(contFont).setFontSize(12)
					.setBold().setMarginTop(0).setMarginBottom(0);
			Paragraph valYop = new Paragraph(t.getDoi().getMonth().name() + "-" + t.getDoi().getYear())
					.setFont(contFont).setFontSize(12).setBold().setMarginTop(0).setMarginBottom(0);

			float[] hcol = {1, 1.75f, 0.75f};
			Table headerTable = new Table(hcol).useAllAvailableWidth();
			headerTable.setMarginTop(0);
			headerTable.setMarginBottom(0);
			
			doc.add(headerTable);
			Cell c1 = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell c2 = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell c3 = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell c4 = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell c5 = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell c6 = new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE);

			c1.add(pRegNo).add(valRegNo);
			c2.add(pName).add(valName);
			c3.add(pDob).add(valDob);
			c4.add(pDur).add(valDur);
			c5.add(pProg).add(valProg);
			c6.add(pYop).add(valYop);
			headerTable.addCell(c1);
			headerTable.addCell(c2);
			headerTable.addCell(c3);
			headerTable.addCell(c4);
			headerTable.addCell(c5);
			headerTable.addCell(c6);
			headerTable.complete();
			
			float[] colWidths = { 1, 1.5f, 4.5f, 1, 2 };
			Table table1 = new Table(colWidths).useAllAvailableWidth();
			table1.setMarginTop(0);
			
			Cell ch1 = new Cell().add("Sem").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch2 = new Cell().add("Module\nCode").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch3 = new Cell().add("Module Name").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch4 = new Cell().add("Grade").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch5 = new Cell().add("Appeared").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			table1.addHeaderCell(ch1);
			table1.addHeaderCell(ch2);
			table1.addHeaderCell(ch3);
			table1.addHeaderCell(ch4);
			table1.addHeaderCell(ch5);

			int count=0;
			
			for(int sem : t.getModules().keySet()) {
				//int sem = semNumbers.get(count);
				String semCode = toRoman(sem);
				List<ModuleInfo> modules = t.getModules().get(sem);
				
				Cell semCell = new Cell(modules.size(), 1).add(semCode).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setTextAlignment(TextAlignment.CENTER);
				table1.addCell(semCell);

				for (ModuleInfo m : modules) {
					Cell mc = new Cell().add(m.getModuleCode()).setFont(contFont).setFontSize(12)
							.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
					Cell mn = new Cell().add(m.getModuleName()).setFont(contFont).setFontSize(12)
							.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
					Cell mg = new Cell().add("").setFont(contFont).setFontSize(12)
							.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
					Cell ma = new Cell().add(m.getAppeared()).setFont(contFont).setFontSize(12)
							.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
					table1.addCell(mc);
					table1.addCell(mn);
					table1.addCell(mg);
					table1.addCell(ma);
				}
				count++;
				if(count==4) {
					break;
				}
			}
			
			//table1.setFixedPosition(50, pdfPage.getPageSize().getTop()-750, 500);
			doc.setRenderer(new DocumentRenderer(doc) {
		        @Override
		        protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
		            LayoutArea area = super.updateCurrentArea(overflowResult);
		            if (area.getPageNumber() == 1) {
		                area.getBBox().decreaseHeight(200);
		            }
		            return area;
		        }
		    });
			
			doc.add(table1);
			if(count<t.getModules().size()) {
				doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				
				Table table2 = new Table(colWidths).useAllAvailableWidth();
				table2.setMarginTop(0);
				
				Cell ch21 = new Cell().add("Sem").setFont(contFont).setFontSize(12).setBold()
						.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ch22 = new Cell().add("Module\nCode").setFont(contFont).setFontSize(12).setBold()
						.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ch23 = new Cell().add("Module Name").setFont(contFont).setFontSize(12).setBold()
						.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ch24 = new Cell().add("Grade").setFont(contFont).setFontSize(12).setBold()
						.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ch25 = new Cell().add("Appeared").setFont(contFont).setFontSize(12).setBold()
						.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
				table2.addHeaderCell(ch21);
				table2.addHeaderCell(ch22);
				table2.addHeaderCell(ch23);
				table2.addHeaderCell(ch24);
				table2.addHeaderCell(ch25);
			
				count=0;
				for(int sem : t.getModules().keySet()) {
					count++;
					if(count<=4) {
						continue;
					}
					
					String semCode = toRoman(sem);
					List<ModuleInfo> modules = t.getModules().get(sem);
					
					Cell semCell = new Cell(modules.size(), 1).add(semCode).setFont(contFont).setFontSize(12)
							.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setTextAlignment(TextAlignment.CENTER);
					table2.addCell(semCell);

					for (ModuleInfo m : modules) {
						Cell mc = new Cell().add(m.getModuleCode()).setFont(contFont).setFontSize(12)
								.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
						Cell mn = new Cell().add(m.getModuleName()).setFont(contFont).setFontSize(12)
								.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
						Cell mg = new Cell().add("").setFont(contFont).setFontSize(12)
								.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
						Cell ma = new Cell().add(m.getAppeared()).setFont(contFont).setFontSize(12)
								.setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingTop(0).setPaddingBottom(0).setPaddingLeft(5);
						table2.addCell(mc);
						table2.addCell(mn);
						table2.addCell(mg);
						table2.addCell(ma);
					}
					
				}
				doc.add(table2);
			}
			
			String cumGpaLabel = "Cumulative Grade Point Average: ";
			Paragraph cumGpa = new Paragraph(cumGpaLabel+String.format("%5.2f", t.getCgpa())).setFont(contFont).setFontSize(12).setBold()
					.setMarginTop(0).setMarginBottom(0);
			String awardLabel = "Class of Award: ";
			Paragraph award = new Paragraph(awardLabel+t.getClassOfAward()).setFont(contFont).setFontSize(12).setBold()
					.setMarginTop(0).setMarginBottom(0);
			Paragraph coe = new Paragraph("Deputy Controller Of Examination").setFont(contFont).setFontSize(12).setBold()
					.setMarginTop(0).setMarginBottom(0).setTextAlignment(TextAlignment.RIGHT);
			
			doc.add(cumGpa);
			doc.add(award);
			doc.add(coe);
			doc.close();
		} catch (Exception e) {
			return e.toString();
		}
		return "Transcript generated";
	}

	/*
	public static void pdfTranscript(Transcript t) {

		try {
			String fileName = AppUtil.getSettings().getProperty("pdf.location") + t.getRegNo() + ".pdf";
			//System.out.println("file Location: " + fileName);

			PdfWriter writer = new PdfWriter(fileName);
			PdfDocument pdfDoc = new PdfDocument(writer);
			PdfPage pdfPage = pdfDoc.addNewPage(PageSize.A4.rotate());
			Document doc = new Document(pdfDoc);

			PdfFont headingFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			PdfFont contFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

			Image logo = new Image(ImageDataFactory.create("src/main/resources/dmisjbu.jpg"));
			logo.setFixedPosition(100, 465);
			logo.scaleAbsolute(65, 75);
			doc.add(logo);

			Text universityName = new Text("DMI - ST. JOHN THE BAPTIST UNIVERSITY").setFont(headingFont).setFontSize(25)
					.setFontColor(Color.BLUE);
			Paragraph university = new Paragraph().add(universityName);
			university.setTextAlignment(TextAlignment.CENTER).setFirstLineIndent(65);

			doc.add(university);
			Paragraph line1 = new Paragraph(String.format("%-203s %-50s", "Main Campus", "Lilongwe Campus"));
			Paragraph line2 = new Paragraph(
					String.format("%-194s %-50s", "P.O.BoxNo.406,Mangochi", "P.O.BoxNo.2398,Lilongwe"));
			Paragraph line3 = new Paragraph(
					String.format("%-200s %-50s", "Malawi,CentralAfrica", "Malawi,CentralAfrica"));
			Paragraph line4 = new Paragraph(String.format("%-185s %-50s", "Phone: +265991287235, +2651599790",
					"Phone: +265881047088, +265992371901"));
			Paragraph line5a = new Paragraph(String.format("%-75s", "Emailid:dmisjbu@gmail.com"));
			Text line5b = new Text(String.format("%-100s", "www.dmisjbu.edu.mw"));
			Text line5c = new Text("Emailid:dmisjbulilongwe@gmail.com");

			line1.setMultipliedLeading(0).setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(150);
			line2.setMultipliedLeading(0).setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(150);
			line3.setMultipliedLeading(0).setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(150);
			line4.setMultipliedLeading(0).setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(150);
			line5a.setMultipliedLeading(0).setFont(contFont).setFontSize(8).setBold().setFirstLineIndent(150);
			line5b.setFont(contFont).setFontSize(8).setBold().setFontColor(Color.BLUE);
			line5c.setFont(contFont).setFontSize(8).setBold();
			doc.add(line1);
			doc.add(line2);
			doc.add(line3);
			doc.add(line4);
			line5a.add(line5b).add(line5c);
			doc.add(line5a);

			PdfCanvas canvas = new PdfCanvas(pdfPage);
			canvas.moveTo(50, 450);
			canvas.lineTo(800, 450);
			canvas.closePathStroke();

			Paragraph transcriptTitle = new Paragraph("TRANSCRIPT");
			transcriptTitle.setFont(contFont).setFontSize(12).setBold().setUnderline()
					.setTextAlignment(TextAlignment.CENTER);
			transcriptTitle.setMultipliedLeading(3);

			doc.add(transcriptTitle);

			Paragraph pName = new Paragraph("Name of the Student: ").setFont(contFont).setFontSize(12);
			Paragraph pRegNo = new Paragraph("Registration Number: ").setFont(contFont).setFontSize(12);
			Paragraph pDob = new Paragraph("Date Of Birth: ").setFont(contFont).setFontSize(12);
			Paragraph pProg = new Paragraph("Programme: ").setFont(contFont).setFontSize(12);
			Paragraph pDur = new Paragraph("Duration: ").setFont(contFont).setFontSize(12);
			Paragraph pYop = new Paragraph("Year Of Passing: ").setFont(contFont).setFontSize(12);

			Text valName = new Text(t.getStudName()).setFont(contFont).setFontSize(12).setBold();
			Text valRegNo = new Text(t.getRegNo()).setFont(contFont).setFontSize(12).setBold();
			Text valDob = new Text(t.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).setFont(contFont)
					.setFontSize(12).setBold();
			Text valProg = new Text(t.getProgramme()).setFont(contFont).setFontSize(12).setBold();
			String doe = t.getDoe();
			String begYr = doe.substring(doe.indexOf("-") + 1);
			Text valDur = new Text(begYr + " - " + t.getDoi().getYear()).setFont(contFont).setFontSize(12).setBold();
			Text valYop = new Text(t.getDoi().getMonth().name() + "-" + t.getDoi().getYear()).setFont(contFont)
					.setFontSize(12).setBold();

			pName.add(valName);
			pRegNo.add(valRegNo);
			pDob.add(valDob);
			pProg.add(valProg);
			pDur.add(valDur);
			pYop.add(valYop);

			float[] colWidths = { 1, 2, 4.5f, 1, 1.5f, 1, 2, 4.5f, 1, 1.5f };
			Table table1 = new Table(colWidths);
			table1.setFixedPosition(50, 320, 750);

			Cell c1 = new Cell(1, 5);
			Cell c2 = new Cell(1, 5);
			c1.add(pName).add(pRegNo).add(pDob);
			c2.add(pProg).add(pDur).add(pYop);
			table1.addCell(c1);
			table1.addCell(c2);
			Cell ch1 = new Cell().add("Sem").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch2 = new Cell().add("Module\nCode").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch3 = new Cell().add("Module Name").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch4 = new Cell().add("Grade").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch5 = new Cell().add("Appeared").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch6 = new Cell().add("Sem").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch7 = new Cell().add("Module\nCode").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch8 = new Cell().add("Module Name").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch9 = new Cell().add("Grade").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell ch10 = new Cell().add("Appeared").setFont(contFont).setFontSize(12).setBold()
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);

			table1.addCell(ch1);
			table1.addCell(ch2);
			table1.addCell(ch3);
			table1.addCell(ch4);
			table1.addCell(ch5);
			table1.addCell(ch6);
			table1.addCell(ch7);
			table1.addCell(ch8);
			table1.addCell(ch9);
			table1.addCell(ch10);

			Table sem1 = new Table(5);
			List<ModuleInfo> modules1 = t.getModules().get(1);
			String semCode1 = toRoman(1);
			Cell semCell = new Cell(modules1.size(), 1).add(semCode1).setFont(contFont).setFontSize(12)
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			sem1.addCell(semCell);
			for (ModuleInfo m : modules1) {
				Cell mc = new Cell().add(m.getModuleCode()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mn = new Cell().add(m.getModuleName()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mg = new Cell().add(String.format("%5.2f", m.getGrade())).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ma = new Cell().add(m.getAppeared()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				sem1.addCell(mc);
				sem1.addCell(mn);
				sem1.addCell(mg);
				sem1.addCell(ma);
			}
			Cell s1 = new Cell(1, 5).add(sem1);
			table1.addCell(s1);

			Table sem3 = new Table(5);
			List<ModuleInfo> modules3 = t.getModules().get(3);
			String semCode3 = toRoman(3);
			Cell semCell3 = new Cell(modules3.size(), 1).add(semCode3).setFont(contFont).setFontSize(12)
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			sem1.addCell(semCell3);
			for (ModuleInfo m : modules3) {
				Cell mc = new Cell().add(m.getModuleCode()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mn = new Cell().add(m.getModuleName()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mg = new Cell().add(String.format("%5.2f", m.getGrade())).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ma = new Cell().add(m.getAppeared()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				sem1.addCell(mc);
				sem1.addCell(mn);
				sem1.addCell(mg);
				sem1.addCell(ma);
			}
			Cell s3 = new Cell(1, 5).add(sem3);
			table1.addCell(s3);

			Table sem2 = new Table(5);
			List<ModuleInfo> modules2 = t.getModules().get(2);
			String semCode2 = toRoman(2);
			Cell semCell2 = new Cell(modules2.size(), 1).add(semCode2).setFont(contFont).setFontSize(12)
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			sem1.addCell(semCell2);
			for (ModuleInfo m : modules2) {
				Cell mc = new Cell().add(m.getModuleCode()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mn = new Cell().add(m.getModuleName()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mg = new Cell().add(String.format("%5.2f", m.getGrade())).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ma = new Cell().add(m.getAppeared()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				sem1.addCell(mc);
				sem1.addCell(mn);
				sem1.addCell(mg);
				sem1.addCell(ma);
			}
			Cell s2 = new Cell(1, 5).add(sem2);
			table1.addCell(s2);

			Table sem4 = new Table(5);
			List<ModuleInfo> modules4 = t.getModules().get(4);
			String semCode4 = toRoman(4);
			Cell semCell4 = new Cell(modules4.size(), 1).add(semCode4).setFont(contFont).setFontSize(12)
					.setVerticalAlignment(VerticalAlignment.MIDDLE);
			sem1.addCell(semCell4);
			for (ModuleInfo m : modules4) {
				Cell mc = new Cell().add(m.getModuleCode()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mn = new Cell().add(m.getModuleName()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell mg = new Cell().add(String.format("%5.2f", m.getGrade())).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				Cell ma = new Cell().add(m.getAppeared()).setFont(contFont).setFontSize(12)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				sem1.addCell(mc);
				sem1.addCell(mn);
				sem1.addCell(mg);
				sem1.addCell(ma);
			}
			Cell s4 = new Cell(1, 5).add(sem4);
			table1.addCell(s4);

			doc.add(table1);
			doc.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	*/
}
