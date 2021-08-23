package socket_programming;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketTest {
	public static void main(String[] args)  throws IOException{
		
		Socket socket = new Socket("localhost", 1215);
		System.out.println("연결되었습니다." + socket);
	// localhost 자기 네트워크에 연결한다는 것...(??)
		socket.close();
		
	}

}
