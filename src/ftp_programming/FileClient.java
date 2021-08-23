package ftp_programming;


/**
 * 
 *  파일 하나만 옮겨지고 안됌
 *  server에서 바이트마다 텍스트가 찍힘
 * 
 * 
 * 
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class FileClient { 
//	public static final String IP = "192.168.0.134";
	public static final String IP = "localhost";
	public static final int PORT = 12000;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket(IP,PORT);
		
		InputStream in = socket.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inr);
		
		DataInputStream din = new DataInputStream(in);
		FileOutputStream fout = null;
		
		while(true) {
//						// 파일 정보 (이름/크기) 얻기
//						filePath = rescs.getName() +"\\" + file; 
//						File sendFile = new File(filePath);
//						System.out.println(sendFile.getName() + "송신 중...");
			
//						// 파일 송신 (printWriter이용)
//						pw.println(sendFile); // 파일 보내기...
//						pw.flush();
//						pw.println(sendFile.length()); // 파일의 크기정보 보내기 
//						pw.flush();
			// 파일 이름 수신
			String strline = br.readLine(); //printwriter 와 bufferedreader는 짝!!
			String strlength = br.readLine(); // 파일의 길이정보
//						
			if(strline == null) break;
			System.out.println(strline + " 파일 수신중...");
			System.out.println("(파일크기: " +strlength + ")" );
			
			
//						// 파일을 읽어서 바이너리로 보내기
//						fin = new FileInputStream(filePath); // inputStream 으로 파일 읽어오기
			String path = "C:\\Temp/Downloads/" + strline;
			fout = new FileOutputStream(path); // 경로에 연결~
			
			int cnt = 0;
			for(int i=0; i<Integer.parseInt(strlength); i++) { // 파일 하나 바이트 사이즈만큼 반복하도록 설정
				// 파일 읽기
				int data = din.read(); // dout으로 보낸 것 din으로 받기
				// 파일 쓰기
				fout.write(data); 
							
				if(cnt % 1000 == 0) {
					System.out.println("■");
					} cnt++;
				}
				System.out.println("("+cnt + "바이트)");
				System.out.println("완료\n");
		}
		fout.close();
		din.close();
		br.close();
		socket.close();
		
		System.out.println();
		System.out.println("파일 요청 프로그램을 종료합니다");
	}
}
