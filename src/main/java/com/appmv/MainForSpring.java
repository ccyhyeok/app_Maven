package com.appmv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.format.Printer;

import ex.AppCtx;
import ex.Assembler;
import ex.ChangePasswordService;
import ex.DuplicateMemberException;
import ex.MemberNotFoundException;
import ex.MemberRegisterService;
import ex.RegisterRequest;
import ex.VersionPrinter;
import ex.WrongIdPassWordException;

public class MainForSpring {
	// FrontController 느낌??

	private static ApplicationContext ctx = null;
	// Web에서 ContextListener 썻던것 처럼
	// Bean 객체 생성.

	public static void main(String[] args) throws IOException{

		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		// 가장 먼저 얻어내야할 듯..!!

		//		MemberRegisterService regSvc =
		//				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		//
		//		ChangePasswordService changePwdSvc =
		//				ctx.getBean("changePwdSvc", ChangePasswordService.class);

		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			System.out.println("명령어를 입력하세요: ");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				// equalsIgnoreCase : 대소문자 구별없이 boolean으로 상태 표현해줌
				// EX) APPLE, apple은 같음
				System.out.println("종료합니다");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			//			} else if (command.equals("list")) {
			//				processListCommand();
			//				continue;
			//			}
			//			else if(command.equals("version")) {
			//				processVersionCommand();
			//				continue;
			//			}
			printHelp();
		} 

	} // END MAIN

	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		//		MemberRegisterService regSvc = assembler.getMemberRegisterService();

		MemberRegisterService regSvc =
				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		// 

		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다. \n");
			return;
		}

		try {
			regSvc.regist(req);
			System.out.println("등록했습니다. \n");
		} catch(DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다. \n");

		}

	}

	private static void processChangeCommand(String[] arg) {	
		if(arg.length!=4) {
			printHelp();
			return;
		}
		//		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();

		ChangePasswordService changePwdSvc =
				ctx.getBean("changePwdSvc", ChangePasswordService.class);

		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다. \n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다. \n");
		} catch (WrongIdPassWordException e){
			System.out.println("이메일과 암호가 일치하지 않습니다. \n");
		}
	}

	private static void processVersionCommand() {
		VersionPrinter versionPrinter = 
				ctx.getBean("versionPrint", VersionPrinter.class);
		
		versionPrinter.print();
	}


	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
		System.out.println("명령어 사용법: ");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}


}


























