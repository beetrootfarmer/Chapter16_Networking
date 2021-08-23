package com.framework;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.io.IOException;
/**
 * 설정 데이터 파일을 읽어와 초기화
 * 
 */
public abstract class TcpApplication {
	/**
	 * 설정에 필요한 필드
	 */
	public static String IP;
	public static int PORT; // new해서 가져다 쓰지 않기 때문에 static, 상수는 대문자로 써야해
	public static String CONFIG = "config/applicationContext.ini";

	/**
	 * 
	 * 어플리케이션 초기화
	 */
	public void init() {
		// 설정 데이터로부터 필요한 항목을 추출 // config에서 설정데이터 가져오기
		Properties settings = new Properties();
		try {
			// load(inputStream) 메소드로 CONFIG 읽기
			settings.load(new FileInputStream(CONFIG));
			IP = settings.getProperty("IP");
			PORT = Integer.parseInt(settings.getProperty("PORT"));
			
			System.out.println("IPAddress: " + IP);
			System.out.println("PORT: " + PORT);
			
			System.out.println(TcpApplication.timeStamp());

		} catch (IOException e) {
			System.out.println("설정파일(applicationContext.ini)을 찾을 수 없습니다.");
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}

	}

	/*
	 * 구현 클래스에서 오버라이딩 할 추상메서드
	 */
	public abstract void start(); // 추상 메서드가 하나라도 있으면 클래스는 추상클래스로

	/*
	 * 현재시간을 리턴하는 메소드 반환타입 : String
	 */
	public static String timeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
		return format.format(new Date());
	}

}
