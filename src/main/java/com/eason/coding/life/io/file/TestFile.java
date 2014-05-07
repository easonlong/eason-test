package com.eason.coding.life.io.file;

import java.io.FileWriter;
import java.io.IOException;

public class TestFile {

	public static void main(String[] args) {
		String reportFileName=null;
		 try {
			FileWriter fwReport = new FileWriter(reportFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
