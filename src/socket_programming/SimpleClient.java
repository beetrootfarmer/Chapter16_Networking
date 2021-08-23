package socket_programming;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.omg.CORBA.portable.InputStream;

public class SimpleClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("loacalhast", 1215);
		System.out.println("서버연결 완료");
		
		// output으로 보낸거 input으로 받아서 읽어오기
		InputStream in = (InputStream) socket.getInputStream();
		// 공간 생성
		byte[] buff = new byte[10];
		in.read(buff);
		
		System.out.println(Arrays.toString(buff));
		
		in.close();
		socket.close();
		
		System.out.println("클라이언트 종료");
		
	}

}
