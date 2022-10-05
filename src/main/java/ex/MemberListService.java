package ex;

import java.util.Collection;

public class MemberListService {
	
	private MemberDao memberDao; 
	private MemberPrinter printer;
	
	public MemberListService(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void MemberList() {
		Collection<Member> members = memberDao.selectAll();
		for(Member member : members) printer.print(member);
//		members.forEach(m -> printer.print(m)); // jdk version 1.8
	}
	
}
