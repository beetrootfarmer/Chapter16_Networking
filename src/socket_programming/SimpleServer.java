package socket_programming;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String[] args) throws IOException{
		
		ServerSocket server = new ServerSocket(1215);
		System.out.println("Server is Ready");
		
		Socket socket = server.accept();
		System.out.println("Client Connected");
		
		byte[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		// socket has input output Streams. 두개 쌍으로 
		OutputStream out = socket.getOutputStream();
		
		
		out.write(arr);
		out.flush();
		out.close(); // 서버가 생성한 '클라이언트를 배뎐한?코드_
	
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
