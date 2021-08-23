package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
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

public class EcoClient {
	public static void main(String[] args) throws IOException, UnknownHostException {
		
		// 자기 pc에서 127.0.0.1 혹은 localhost 
		Socket socket = new Socket("localhost", 1215);
		System.out.println("서버연결 완료");
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("서버로 전송 할 메세지를 입력해 주세요.");
		
		// 반복문을 돌려서 
		while(true) {
			System.out.print("> ");
			// 입력받은 문자열 보내기
			String msg = sc.nextLine();
			dos.writeUTF(msg);
			dos.flush();
			
			if(msg.equalsIgnoreCase("exit")) {
				break;
			}
		}
		
		
		
		/*
		 * InputStream in = socket.getInputStream(); DataInputStream dis = new
		 * DataInputStream(in);
		 */
		
		sc.close();
		socket.close();
		
		System.out.println("클라이언트 종료");
	
	}

}
