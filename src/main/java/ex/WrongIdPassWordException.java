package ex;

public class WrongIdPassWordException extends RuntimeException {
	public WrongIdPassWordException() {
		System.out.println("비밀번호가 일치하지 않습니다.");
	}
}
