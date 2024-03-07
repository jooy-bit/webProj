package org.iclass.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.iclass.dao.DemoMemberDao;
import org.iclass.vo.DemoMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	private DemoMemberDao dao = DemoMemberDao.getInstance();
	
	// password 해싱
	public void join(DemoMember member) {
		try {
			String password = encrypt(member.getPassword());
			member.setPassword(password);
			dao.join(member);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
				
	}
	// password 해싱
	public DemoMember login(Map<String,String> map) {
		DemoMember result=null;
		try {
			String password = encrypt(map.get("password"));
			map.put("password", password);
			result = dao.login(map);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	//암호화 : 평문 -> 알고리즘 -> 암호문(가독x)
	//복호화 : 암호문 -> 알고리즘 -> 평문
	// 해싱 : 평문 -> 해싱함수 -> 같은 평문에 대해 가독 불가능 같은 문자열 (암호문)
	//암호화된 문자열이 다시 평문으로 복구는 못합니다
	//sha256 해싱함수는 암호문 문자열 256비트 16진수 64개 문자로 만듭니다
	public String encrypt(String text) throws NoSuchAlgorithmException {
		//자바에서는 MessageDigest 클래스가 해싱함수를 제공
		//1) 실행 객체 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        //2) 평문을 저장. byte배열로 변환하여 저장
        md.update(text.getBytes());
        //md.digest() 메소드가 해싱함수 실행합니다. 해싱결과가 byte[] 바이트배열

        return bytesToHex(md.digest());
    }
	//byte[] 1 byte by byte 로 16진수로 바꾸기 
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));//"%02x" 이게 16진수
        }
        return builder.toString();
    }

}
