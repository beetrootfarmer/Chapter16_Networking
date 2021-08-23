package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * Scanner랑 비슷한게 DataInputStream 
 * SystemIn(키보드 입력을 받겠다)
 * DataInputStream (입력버퍼)
 * 이용해서 (클라이언트에서) 입력한 문자열을 전송하기
 */

public class EcoClient2 {
	public static void main(String[] args) throws IOException, UnknownHostException {
		
		// 자기 pc에서 127.0.0.1 혹은 localhost 
		Socket socket = new Socket("localhost", 1215);
		System.out.println("서버연결 완료");
		
		
		// 서버로 데이터 전송
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		// 서버에서 데이터 받기
		InputStream in = socket.getInputStream(); 
		DataInputStream dis = new DataInputStream(in);
		
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("서버로 전송 할 메세지를 입력해 주세요.");
		
		// 반복문을 돌려서 
		while(true) {
			System.out.print("> ");
			// 입력받은 문자열 보내기
			String sendMsg = sc.nextLine();
			
			dos.writeUTF(sendMsg);
			dos.flush();
			
			// 수신받은 문자열 읽기
			String readMsg = dis.readUTF();
			System.out.println("서버의 메세지: " + readMsg);
			
			if(sendMsg.equalsIgnoreCase("exit")) {
				break;
			}
		}
		
		
		
		dis.close();
		dos.close();
		sc.close();
		socket.close();
		
		System.out.println("클라이언트 종료");
	
	}

}
