package ftp_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class FTPClient {
	public static final String IP = "localhost";
//	public static final String IP = "192.168.0.134";
//	내 컴퓨터 ip주소 :  192.168.0.20
	//	public static final String IP = "127.0.0.1";  localhost와 같은 의미
	public static final int PORT = 1453;
	
	public static void main(String[] args) {
		
		// 필드( 입출력 스트림 )
		InputStream in =null; //명시적으로 해주는 null
		OutputStream out =null;
		DataInputStream din =null;
		DataOutputStream dout =null;
		Scanner sc = null;
		FileOutputStream fos = null;
		
		// 소켓
		Socket socket = null;
		
		try {
			socket = new Socket(IP,PORT);
			sc = new Scanner(System.in);
			
			/*
			 * 수신(IN) 서버와 연결되는 소켓. 서버 연결정보... client 에서 accept한 socket과 연결
			 */
			in = socket.getInputStream();
			din = new DataInputStream (in);
			
			/*
			 * 송신(OUT) 
			 */
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			
			displayMenu();
			String fileName = sc.nextLine();
			
			fos = new FileOutputStream("C:/Temp/" + fileName); //받은 파일 저장할 경로
			// 클라이언트가 입력한 값 서버로 전송
			dout.writeUTF(fileName); // String이니까 writeUTF
			System.out.println(timeStamp() + "파일 서버에 요청하였습니다.");
			
			// 파일 데이터 수신
			System.out.println("파일 수신중...");
			System.out.println();
			
			int cnt = 0;
			while(true) {
				// 읽기
				int data = din.read();
				if(data == -1) break;
				// 저장
				fos.write(data);
				
				if(cnt % 1000 == 0) {
				System.out.print("♣"); // 1바이트 읽어서 보낼때마다 찍는다? if 문으로 5000바이트 읽을 때마다 한번 찍기.
				} cnt++; // 1바이트 ++
			}
			System.out.println();
			System.out.println("파일 수신을 완료하였습니다" + "(" + cnt + "byte)"); // 파일 용량 찍기
			
			System.out.println();
			System.out.println(timeStamp() + "파일 다운로드 완료");
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(din != null) {din.close();}
				if(dout != null) {dout.close();}
				if(fos != null) {fos.close();}
				if(socket != null) {socket.close();}	
				
			}catch(IOException ex) {ex.printStackTrace();
		}
		
		System.out.println(timeStamp() + "클라이언트 파일 요청 프로그램을 종료합니다.");
		// 파일 요청
		// 파일 받기 & 내용출력
	}

	}

	private static void displayMenu() {
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.println("  받고 싶은 파일의 이름(파일명.확장자)을 입력해주세요.");
		System.out.println();
		System.out.println("  [1]tSample1.txt [2]tSample2.txt [3]tSample3.txt [4]iSample.jpg  ");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────");
		System.out.println();
		System.out.println("선택> ");
	}
	
	/*
	 *  현재시간을 리턴하는 메소드
	 *  반환타입 : String
	 */
	private static String timeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
		return format.format(new Date());
	}
	
}
