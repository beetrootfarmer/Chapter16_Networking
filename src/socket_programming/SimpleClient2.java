package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.omg.CORBA.portable.InputStream;

public class SimpleClient2 {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("loacalhast", 1215);
		System.out.println("서버연결 완료");
		
		// 보조스트림 연결
		java.io.InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		String message = dis.readUTF();
		System.out.println("받은 메세지: " + message);
				
		dis.close();
		in.close();
		socket.close();
		
		System.out.println("클라이언트 종료");
		
	}

}
