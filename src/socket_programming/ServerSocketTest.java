package socket_programming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {
	public static void main(String[] args) throws IOException {
		
		/** NIC (network card)
		 * IPA
		 * 
		 * tcp 통신 : 안정적통신. 연결 확인을 하고 데이터를 주는데, 보낸쪽에서 데잉터를 줬는데 받았는지 확인까지 하고...
		 * udp 통신 : broadcasting 한방향적 데이터 송......확인불가능. 빠르다.
		 * 
		 * socket programming은 무엇인가??
		 * Socket 끝과 끝을 열결하는 창구
		 * 네트워크 카드~를 클래스로 만든것~~
		 * 통신은 socket과 socket의 연결->외부에서 접속이 가능해진
		다 -> 서버가 된다
		통신의 주체가 socket
		
		 */
		
		ServerSocket serverSocket = new ServerSocket(1215);
		//		 1024	가 넘는 숫자..
		System.out.println("클라이언트 연결 대기 중...");
		Socket clientSocket = serverSocket.accept(); //클라이언트가 접속하지 않으면 lock이 걸리는 메소드 
		
		System.out.println("연결되었습니다." + serverSocket);

		clientSocket.close();
		serverSocket.close();
	}

}
