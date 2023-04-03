package com.opencloudplatform.edm.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opencloudplatform.edm.dao.AdminDao;
import com.opencloudplatform.edm.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AdminDao admindao;

	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(String adminId, String adminPw, String toURL, boolean rememberId,
						HttpServletRequest request, HttpServletResponse response) throws Exception {



		// 1. id와 pwd를 확인
		if(!loginCheck(adminId, adminPw)) {
			// 2-1   일치하지 않으면, loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

			return "redirect:/login/login?msg="+msg;
		}
		// 2-2. id와 pwd가 일치하면,
		//  세션 객체를 얻어오기
		HttpSession session = request.getSession();
		//  세션 객체에 id를 저장
		session.setAttribute("adminId", adminId);

	//	System.out.println(adminId);

		if(rememberId) {
			//     1. 쿠키를 생성
			Cookie cookie = new Cookie("adminId", adminId); // ctrl+shift+o 자동 import
//		       2. 응답에 저장
			response.addCookie(cookie);
		} else {
			// 1. 쿠키를 삭제
			Cookie cookie = new Cookie("adminId", adminId); // ctrl+shift+o 자동 import
			cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
			response.addCookie(cookie);
		}
//		       3. 홈으로 이동
		toURL = toURL==null || toURL.equals("") ? "/" : toURL;

		return "redirect:"+toURL;
	}

	private boolean loginCheck(String adminId, String adminPw) {
		Admin admin = null;

		try {
			admin = admindao.selectUser(adminId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


        return admin!=null && admin.getAdminPw().equals(adminPw);
	}
}