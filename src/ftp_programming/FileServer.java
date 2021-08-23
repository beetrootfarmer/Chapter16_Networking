package ftp_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/** [파일 서비스 프로그래밍]

 */
public class FileServer {

	public static final int PORT = 12000;
	public static void main(String[] args) throws IOException, NullPointerException {
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("클라이언트 접속 대기중 입니다...");
		
		Socket client = server.accept(); // 클라이언트 정보가 담긴 소켓
		
		System.out.println(client.getInetAddress().getHostName() +" 님이 접속하셨습니다.");
		
		OutputStream out = client.getOutputStream();//클라이언트 소켓과 연결 ...
		OutputStreamWriter outw = new OutputStreamWriter(out);
		// printwriter 
		PrintWriter pw = new PrintWriter(outw);
		
		FileInputStream fin = null;
		DataOutputStream dout = new DataOutputStream(out);

		/*
		 * 리소스 자동 배포
		 */
		File rescs = new File("resources\\"); // C:\Dev211\Studyjava\Temp\resources
		System.out.println(rescs.list().length + "개의 파일이 있습니다.");
		
		String filePath = null;	
		for(String file : rescs.list()) {
			// 파일 정보 (이름/크기) 얻기
			filePath = rescs.getName() +"\\" + file; 
			File sendFile = new File(filePath);
			System.out.println(sendFile.getName() + "송신 중...");
			// 파일 송신 (printWriter이용)
			pw.println(sendFile); // 파일 보내기...
			pw.flush();
			pw.println(sendFile.length()); // 파일의 크기정보 보내기 
			pw.flush();
			
			// 파일을 읽어서 바이너리로 보내기
			fin = new FileInputStream(filePath); // inputStream 으로 파일 읽어오기
			int cnt = 0;
			for(int i=0; i<sendFile.length(); i++) {
				// 파일 읽기
				int data = fin.read();
				// 파일 쓰기
				dout.write(data);
				
				if(cnt % 1000 == 0) {
					System.out.print("■");
				} cnt++;
				System.out.println("("+cnt + "바이트)");
				System.out.println("완료\n");
			}
				
			fin.close();
			dout.close();
//			outw.close();
			pw.close();
			client.close();
			server.close();
			
//			System.out.println(filePath);
		}
		
		
	}
}