package com.dmi.sjbu.proj.transcript.util;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.dmi.sjbu.proj.transcript.model.Transcript;
import com.dmi.sjbu.proj.transcript.service.TranscriptService;

public class MyForm extends JFrame{
	private JLabel lblFile, lblRegNo, lblExpMsg, lblRegNoMsg;
	private JTextField txtFile, txtRegNo;
	private JButton btnExport, btnPdf;
	
	public MyForm() {
		super("SJBU Transcript");
		lblFile = new JLabel("Enter the file name: ");
		lblRegNo = new JLabel("Enter the Student Reg.No: ");
		lblExpMsg = new JLabel("*");
		lblRegNoMsg = new JLabel("*");
		txtFile = new JTextField(100);
		txtRegNo = new JTextField(30);
		btnExport = new JButton("Export");
		btnPdf = new JButton("Generate");
		
		this.setLayout(null);
		this.setSize(700, 400);
		
		lblFile.setBounds(50, 50, 150, 30);
		txtFile.setBounds(210, 50, 300, 30);
		lblExpMsg.setBounds(50, 90, 500, 30);
		btnExport.setBounds(520, 50, 80, 30);
		
		lblRegNo.setBounds(50, 200, 150, 30);
		txtRegNo.setBounds(210, 200, 150, 30);
		lblRegNoMsg.setBounds(50, 240, 500, 30);
		btnPdf.setBounds(370, 200, 100, 30);
		
		this.add(lblFile);
		this.add(txtFile);
		this.add(lblExpMsg);
		this.add(btnExport);
		this.add(lblRegNo);
		this.add(txtRegNo);
		this.add(lblRegNoMsg);
		this.add(btnPdf);
	
		ActionListener exportListener = (event)->{
			lblExpMsg.setText("*");
			int rows = TranscriptDAO.uploadRecords(txtFile.getText());
			lblExpMsg.setText(rows+" Records uploaded in Database");
		};
		btnExport.addActionListener(exportListener);
		
		ActionListener pdfListener = (event)->{
			lblRegNoMsg.setText("*");
			Transcript t = TranscriptDAO.getTranscript(txtRegNo.getText());
	        lblRegNoMsg.setText(TranscriptService.generatePdf(t));
		};
		btnPdf.addActionListener(pdfListener);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
