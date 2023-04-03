package com.opencloudplatform.edm.controller;

import com.opencloudplatform.edm.dao.NoticeBoardDao;
import com.opencloudplatform.edm.domain.NoticeBoard;
import com.opencloudplatform.edm.domain.PageHandler;
import com.opencloudplatform.edm.domain.QnABoard;
import com.opencloudplatform.edm.service.BoardService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session.getAttribute("id")!=null;
	}

	@GetMapping("/list")
	public String list(HttpServletRequest request, Model m, Integer nowPage, Integer pageSize) {
		if(!loginCheck(request))
			return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동


		if(nowPage==null) nowPage=1;		// 현재 페이지와 한 페이지 출력 수가 null값일때 주어진 값
		if(pageSize==null) pageSize=10;
		try {

			int totalCnt = boardService.getCount();	// 게시물 총 갯수
			PageHandler pageHandler = new PageHandler(totalCnt, nowPage, pageSize);
			//pageHandler에 총 갯수와 현재 페이지 페이지 사이즈를 넘겨준다.

			// 한 페이지에 보여줄 글의 갯수
			Map map = new HashMap();
			map.put("offset", (nowPage - 1) * (pageSize));
			map.put("pageSize", pageSize);


			List<QnABoard> list = boardService.getPage(map);
			m.addAttribute("list", list);					//boardlist.jsp로 보내질 값들
			m.addAttribute("ph", pageHandler);
			m.addAttribute("nowPage", nowPage);				// 게시글 read / remove 하고 돌아올 list값을 위해 함께
			m.addAttribute("pageSize", pageSize);

		} catch (Exception e) {

		}
		return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
	}


//	@GetMapping("/list")
//	public String list(HttpServletRequest request, @RequestParam("boardType") String boardType) {
//		if(!loginCheck(request))
//			return "redirect:/login/login?toURL="+request.getRequestURL();
//
//		if (boardType.equals("notice")) {
//			return "noticeBoard";
//		} else if (boardType.equals("qna")) {
//			return "qnaBoard";
//		}
//		return "index";
//	}
	@GetMapping("/notice") //notice 게시판으로 이동
	public String toNoticeBoard() {
		return "noticeboardlist";
	}

	@GetMapping("/qna") //qna 게시판으로 이동
	public String toQnABoard() {
		return "qnaboardlist";
	}

	@GetMapping("/read")
	public String read(Integer idx, Model m, Integer nowPage, Integer pageSize){
		try {
			QnABoard qnaBoard = boardService.read(idx);
			m.addAttribute("qnaBoard", qnaBoard);
			m.addAttribute("nowPage", nowPage);		// read한 후 게시글 목록으로 돌아올 때 보고 있던 화면으로 돌아감
			m.addAttribute("pageSize", pageSize);

			return "qnaboardlist";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	@PostMapping("/remove")
	public String boardRemove(Model m, Integer nowPage, Integer pageSize, Integer idx,
							  HttpSession session, RedirectAttributes rettr) {
		String writer = (String)session.getAttribute("id"); // writer는 내가 쓴 글만 지울 수 있으므로 session에서
		try {
			m.addAttribute("nowPage", nowPage);			//위와 같음
			m.addAttribute("pageSize", pageSize);

			int rowCnt = boardService.remove(idx, writer);

			if(rowCnt!=1)
				throw new Exception("board remove error"); //rowCnt가 1이 아닐 경우에는 삭제 실패

			rettr.addFlashAttribute("msg","DELD_SUCCESS");
			// Model의 addattribute와 비교해서 두번 반복 되지 않는 메소드임.
			// 후에 boardlist.jsp에서 msg로 받아줌.
		} catch (Exception e) {
			rettr.addFlashAttribute("msg","DELD_FAILED");
		}
		return "redirect:/board/list";
	}

	@GetMapping ("/write")
	public String boardWrite(Model m) {
		m.addAttribute("mode","new"); //mode의 값에 따라 게시글"읽기", 게시글 "수정하기"로 나눈다.
		return "boardwrite";
	}


	@PostMapping ("/write")
	public String boardWriteSave(QnABoard qnaBoard, HttpSession session, Model m, RedirectAttributes rattr) {
		String writer = (String)session.getAttribute("id");
		qnaBoard.setWriter(writer);
		try {
			int rowCnt = boardService.write(qnaBoard);
			if(1!=rowCnt)
				throw new Exception("board write failed");

			rattr.addFlashAttribute("msg","WRITE_SUCCESS");
			return "redirect:/board/list"; // 작성하면 무조건 최신글이기 때문에 nowPage/pageSize를 넘겨줄 필요 없음.

		} catch (Exception e) {
			m.addAttribute(qnaBoard);
			rattr.addFlashAttribute("msg", "WRITE_FAILED");
			return "boardwrite";
		}
	}

	@PostMapping("/modify")
	public String boardModifySave(QnABoard qnaBoard, HttpSession session, Model m, RedirectAttributes rattr,
								  Integer nowPage, Integer pageSize) {
		String writer = (String)session.getAttribute("id");
		qnaBoard.setWriter(writer);
		try {
			int rowCnt = boardService.modify(qnaBoard);
			if(1!=rowCnt)
				throw new Exception("board modify failed");

			m.addAttribute("nowPage", nowPage);
			m.addAttribute("pageSize", pageSize);
			rattr.addFlashAttribute("msg","MODIFY_SUCCESS");
			return "redirect:/board/list"; // 작성하면 무조건 최신글이기 때문에 nowPage/pageSize를 넘겨줄 필요 없음.

		} catch (Exception e) {
			m.addAttribute(qnaBoard);			// qnaBoard가 model에 추가 되었기 때문에
			rattr.addFlashAttribute("msg", "MODIFY_FAILED");
			return "boardwrite";				// return boardwrite가 될 때 내가 수정 할 글로 다시 돌아갈 수 있음.
		}
	}
}
