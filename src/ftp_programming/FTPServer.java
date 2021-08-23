package ftp_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/** [파일 서비스 프로그래밍]
 * 1. 클라이언트는 특정한 파일명을 서버에 요청한다.
 * 2. 서버는 클라이언트가 요청한 파일을 찾는다. (빨대를 꽂는다)
 * 3. 요청한 파일을 찾으면 파일(리소스폴더 내)에서 데이터를 읽어들인다.
 * 4. 읽어 온 파일의 데이터를 클라이언트에게 전송한다.
 * 5. 클라이언트는 받은 파일의 내용을 화면에 출력하고 저장한다.
 * @author bitcamp
 */
public class FTPServer {
	private static final int PORT = 1453;
	
	// 메소드
	public static void main(String[] args) throws IOException {
		
		
		System.out.println();
		// 필드( 입출력 스트림 )
		InputStream in =null; //명시적으로 해주는 null
		OutputStream out =null;
		DataInputStream din =null;
		DataOutputStream dout =null;
		FileInputStream fin = null;
		
		// 소켓
		ServerSocket serverSoc = null;
		Socket		 clientSoc = null; // accept하면 떨어지는 소켓 
		
		// 호스트 주소
		String			 clientAddr = null; // ip주소 얻기
		
		/*
		 * 클라이언트 접속 대기 및 소켓 생성
		 * 수신/ 송신
		 */
	
		try {
			serverSoc = new ServerSocket(PORT); // 서브소켓 생성자에 port를 넣으면 서버가 열린다
			
			System.out.println(timeStamp() + "클라이언트 접속을 대기중입니다...");
			System.out.println();
			
			clientSoc = serverSoc.accept(); // 쌍으로 소켓 만들기
			clientAddr = clientSoc.getInetAddress().getHostName();// ip 추적
			
			System.out.println(timeStamp() + clientAddr + "< connected" );
			
			/*
			 * 수신(IN) 클라이언트 소켓와 inputStream 연결 , 클라이언트가 보낸 파일명
			 */
			in = clientSoc.getInputStream();
			din = new DataInputStream (in);
			
			/*
			 * 송신(OUT) 서버소스폴더 내 파일을 클라이언트에게 보냄
			 */
			out = clientSoc.getOutputStream();
			dout = new DataOutputStream(out);
			
			// 단계1 : 사용자가 무슨 파일을 필요로 하는지 파악
			String fileName = din.readUTF();
			
			// 단계2 : 파일 데이터를 찾아내고 데이터를 읽는다
			// 데이터를 바이너리로 읽고 바이트로 write
			fin = new FileInputStream("resources\\" + fileName ); // 상대경로, 절대경로는 다른 컴퓨터에서 깨지기 쉽쥬
			
			// 단계3 : 파일 데이터를 읽고 데이터를 보낸다.
			System.out.println("파일을 보내는 중...");
			System.out.println();
			
			// 파일을 읽고 보내기
			while(true) {
				// 읽기
				int data = fin.read();
				if(data == -1) break;
				// 쓰기
				dout.write(data); // 읽은 파일 쓰기
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(din != null) {din.close();}
				if(dout != null) {dout.close();}
				if(clientSoc != null) {clientSoc.close();}
				if(serverSoc != null) {serverSoc.close();}
				if(fin != null) {fin.close();}

			}catch(IOException ex) {ex.printStackTrace();
		}
			
			System.out.println(timeStamp() + "파일 전송 서비스를 종료합니다.");
		}
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
	// 파일 목록 공유 
	// 요청받은 파일 찾기
	// 파일을 InputStream으로 읽어들이기
	// 읽어온 파일을 전송
