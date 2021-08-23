package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EcoServer {
	public static void main(String[] args) throws IOException{
		
		
		
		ServerSocket server = new ServerSocket(1215);
		System.out.println("Server is Ready");
		
		Socket socket = server.accept();
		System.out.println("Client Connected");
		
		// 클라이언트 IP 주소
		System.out.println(socket.getInetAddress());
		
		
		// 입력한 것 받아오기
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		while(true) {
		
			// 읽어오기 
			String userMsg = dis.readUTF();
			// 찍어보기
			System.out.println("사용자 메세지 : " + userMsg);
			
			if(userMsg.equalsIgnoreCase("exit")) break;
		}
				
//		OutputStream out = socket.getOutputStream();
//		DataOutputStream dos = new DataOutputStream(out);
		
		
		dis.close();
		in.close();
		
		socket.close();
		server.close();
		
		System.out.println("서버 종료");
	}
	
	// ping google.com -> ping으로 네트워크 접근 확인. ping 때려봐(!)?)
	// 국내 포털사이트는 막아놓음
	// 연결이 문제가 없는지 확인할 수 있음
	// ipconfig -> 자기 ip주소 확인
	// ipconfig/all -> 

}
