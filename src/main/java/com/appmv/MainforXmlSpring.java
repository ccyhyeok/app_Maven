package com.appmv;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import ex.ChangePasswordService;
import ex.MemberRegisterService;

public class MainforXmlSpring {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args)throws IOException {
		
		ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		MemberRegisterService regSvc = 
				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		
		ChangePasswordService changePwdSvc =
				ctx.getBean("changePwdSvc", ChangePasswordService.class);
	}

}
