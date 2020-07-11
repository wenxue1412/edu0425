package edu0425.spring.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFileDemo {

	private static String email_filepath = "C:\\Users\\15208\\eclipse-workspace\\edu0425\\src\\u-email.txt";
	private static String phone_filepath = "C:\\Users\\15208\\eclipse-workspace\\edu0425\\src\\u-phone.txt";
	private static String out_filepath = "C:\\Users\\15208\\eclipse-workspace\\edu0425\\src\\u-out.txt";

	public static void main(String[] args) {
		try {
			readFile1(email_filepath);
			readFile2(phone_filepath);
			writeFile(out_filepath);
			readFile3(email_filepath,phone_filepath,out_filepath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void readFile1(String filepath) throws IOException {
		FileInputStream fin = new FileInputStream(filepath);
		InputStreamReader reader = new InputStreamReader(fin, "UTF-8");
		BufferedReader buffer = new BufferedReader(reader);
		String strTmp = "";
		while ((strTmp = buffer.readLine()) != null) {
			String[] str = strTmp.split("\\s");
			System.out.println(str[0] + "," + str[1] + "," + str[2]);
		}
		buffer.close();
		reader.close();
		fin.close();
	}

	private static void readFile2(String filepath) throws IOException {
		// NIO的方式来读文件；
		Path path = Paths.get(filepath);
		List<String> lines = Files.readAllLines(path);
		for (String line : lines) {
			System.out.println(line);
		}
	}

	private static void writeFile(String filepath) throws IOException {
		File writeName = new File(filepath);
		writeName.createNewFile();

		FileWriter writer = new FileWriter(writeName);
		BufferedWriter out = new BufferedWriter(writer);
		out.write("Hello world \r\n");
		out.write("床前明月光,疑是地上霜 \r\n");
		out.flush();
		out.close();

	}

	private static void readFile3(String filepath1,String filepath2,String filepath3) throws IOException{
		//NIO的方式来读文件；
		List<String> f1 = new ArrayList<String>();
		List<String> f2 = new ArrayList<String>();
		List<String> f3 = new ArrayList<String>();
		List<String> g1 = new ArrayList<String>();
		List<String> g2 = new ArrayList<String>();
		List<String> g3 = new ArrayList<String>();
		FileInputStream fin = new FileInputStream(filepath1);
		InputStreamReader reader = new InputStreamReader(fin,"UTF-8");
		BufferedReader buffer = new BufferedReader(reader);
		String strTmp="";
		while((strTmp = buffer.readLine()) != null){
			int i = 0 ;
			String[] str = strTmp.split("\\t");
			f1.add(str[0]);
			f2.add(str[1]);
			f3.add(str[2]);
			i++;
		}
		buffer.close();
		reader.close();
		fin.close();
		FileInputStream fin2 = new FileInputStream(filepath2);
		InputStreamReader reader2 = new InputStreamReader(fin2,"UTF-8");
		BufferedReader buffer2 = new BufferedReader(reader2);
		String strTmp2="";
		while((strTmp2 = buffer2.readLine()) != null){
			int i = 0 ;
			String[] str2 = strTmp2.split("\\t");
			g1.add(str2[0]);
			g2.add(str2[1]);
			g3.add(str2[2]);
			i++;
		}
		buffer.close();
		reader.close();
		fin.close();
		
		File writeName = new File(filepath3);
		writeName.createNewFile();
		
		FileWriter writer = new FileWriter(writeName);
		BufferedWriter out = new BufferedWriter(writer);
		for(int a =0 ; a < f1.size() ; a++ ) {
			if(g1.contains(f1.get(a))) {
					out.write(f1.get(a)+"\t"+f2.get(a)+"\t"+f3.get(a)+"\t"+g3.get(a)+"\r\n");
			}else {
					out.write(f1.get(a)+"\t"+f2.get(a)+"\t"+f3.get(a)+"\t"+"---------"+"\r\n");
			}
			
			
			
		}
		for(int b = 0 ; b< g2.size(); b++ ) {
			if(!f1.contains(g1.get(b))) {
				out.write(g1.get(b)+"\t"+g2.get(b)+"\t"+"---------"+"\t"+g3.get(b)+"\r\n");
			}
			
		}
		out.flush();
		out.close();		
		}
}
