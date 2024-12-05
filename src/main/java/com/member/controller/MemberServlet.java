package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;

public class MemberServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//當類進行序列化（將物件保存為二進制）並在稍後反序列化時，serialVersionUID 用來確保序列化的類與反序列化的類是相同的版本。

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//日期部分要改，因為timeStamp要精準到秒，SimpleDateFormat這個方法可以更靈活處理我只要的年月日
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("memberId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer memberId = null;
			try {
				memberId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷_
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(memberId);
			if (memberVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
			String url = "/back-end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(memberId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
			String url = "/back-end/member/update_member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_member_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());

			byte memberLvId = 0;
			String memberLvIdStr = req.getParameter("memberLvId").trim();
			if (memberLvIdStr == null || (!memberLvIdStr.equals("0") && !memberLvIdStr.equals("1"))) {
				errorMsgs.add("會員等級只能為0跟1");
			} else {
				memberLvId = Byte.valueOf(memberLvIdStr);
			}

			String memberName = req.getParameter("memberName");
			String memberNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memberName == null || memberName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!memberName.trim().matches(memberNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String memberUid = req.getParameter("memberUid");
			String memberUidReg = "^[A-Z][0-9]{9}$"; // 正則表示式：首字母大寫，後接9位數字
			if (memberUid == null || memberUid.trim().isEmpty()) {
				errorMsgs.add("身份證: 請勿空白");
			} else if (!memberUid.trim().matches(memberUidReg)) {
				errorMsgs.add("身份證: 格式錯誤，首字必須是大寫英文字母 (A-Z)，後面需接 9 位數字");
			}

			java.sql.Date  memberBth = null;
			try {
				memberBth = java.sql.Date.valueOf(req.getParameter("memberBth").trim());
			} catch (IllegalArgumentException e) {
				memberBth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Byte memberGender = null;
			String memberGenderStr = req.getParameter("memberGender");

			if (memberGenderStr == null || !memberGenderStr.trim().matches("[1-2]")) {
				errorMsgs.add("會員性別只能為 1（男）或 2（女）");
			} else {
				memberGender = Byte.valueOf(memberGenderStr.trim());
			}

			String memberEmail = req.getParameter("memberEmail");
			if (memberEmail == null || memberEmail.trim().isEmpty()) {
				errorMsgs.add("電子郵件: 請勿空白");
			} else if (!memberEmail.trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				errorMsgs.add("電子郵件: 格式不正確，請輸入有效的電子郵件地址");
			}

			String memberTel = req.getParameter("memberTel");
			// 檢查是否為空，允許空值
			if (memberTel != null && !memberTel.trim().isEmpty()) {
			    memberTel = memberTel.trim();
			    // 檢查是否小於 10 個數字
			    if (!memberTel.matches("^\\d{1,9}$")) {
			        errorMsgs.add("電話號碼格式不正確，長度必須小於 10 個數字");
			    }
			}

			String memberAdd = req.getParameter("memberAdd");
			// 地址可以為空，但若有輸入需檢查長度
			if (memberAdd != null && !memberAdd.trim().isEmpty()) {
				memberAdd = memberAdd.trim(); // 去除多餘空格
				if (memberAdd.length() > 100) {
					errorMsgs.add("地址: 長度不能超過 100 個字");
				}
			}

			String memberAcc = req.getParameter("memberAcc");
			String memberPw = req.getParameter("memberPw");
			// 帳號檢查
			if (memberAcc == null || memberAcc.trim().isEmpty()) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (memberAcc.length() > 25) {
				errorMsgs.add("帳號: 長度不得超過 25 個字元");
			} else if (!memberAcc.matches("^[a-zA-Z0-9_]+$")) {
				errorMsgs.add("帳號: 僅允許英文字母、數字和底線");
			}

			// 密碼檢查
			if (memberPw == null || memberPw.trim().isEmpty()) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (memberPw.length() > 25) {
				errorMsgs.add("密碼: 長度不得超過 25 個字元");
			} else if (!memberPw.matches("^[a-zA-Z0-9@#$%^&+=]+$")) {
				errorMsgs.add("密碼: 僅允許英文字母、數字和特殊符號 @#$%^&+=");
			}

			byte memberStatus = 0;
			String memberStr = req.getParameter("memberStatus").trim();
			if (memberStr == null || (!memberStr.equals("0") && !memberStr.equals("1"))) {
				errorMsgs.add("案件狀態: 只能為0（申訴成功）或1（申訴失敗）");
			} else {
				memberStatus = Byte.valueOf(memberStr);
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMemberId(memberId);
			memberVO.setMemberLvId(memberLvId);
			memberVO.setMemberName(memberName);
			memberVO.setMemberUid(memberUid);
			memberVO.setMemberBth(memberBth);
			memberVO.setMemberGender(memberGender);
			memberVO.setMemberEmail(memberEmail);
			memberVO.setMemberTel(memberTel);
			memberVO.setMemberAdd(memberAdd);
			memberVO.setMemberAcc(memberAcc);
			memberVO.setMemberPw(memberPw);
			memberVO.setMemberStatus(memberStatus);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return; // 
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(memberId, memberLvId, memberName, memberUid, memberBth, memberGender, memberEmail, memberTel,memberAdd, memberAcc, memberPw, memberStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
			String url = "/back-end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());

			byte memberLvId = 0;
			String memberLvIdStr = req.getParameter("memberLvId").trim();
			if (memberLvIdStr == null || (!memberLvIdStr.equals("0") && !memberLvIdStr.equals("1"))) {
				errorMsgs.add("會員等級只能為0跟1");
			} else {
				memberLvId = Byte.valueOf(memberLvIdStr);
			}

			String memberName = req.getParameter("memberName");
			String memberNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memberName == null || memberName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!memberName.trim().matches(memberNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String memberUid = req.getParameter("memberUid");
			String memberUidReg = "^[A-Z][0-9]{9}$"; // 正則表示式：首字母大寫，後接9位數字
			if (memberUid == null || memberUid.trim().isEmpty()) {
				errorMsgs.add("身份證: 請勿空白");
			} else if (!memberUid.trim().matches(memberUidReg)) {
				errorMsgs.add("身份證: 格式錯誤，首字必須是大寫英文字母 (A-Z)，後面需接 9 位數字");
			}

			java.sql.Date  memberBth = null;
			try {
				memberBth = java.sql.Date.valueOf(req.getParameter("memberBth").trim());
			} catch (IllegalArgumentException e) {
				memberBth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Byte memberGender = null;
			String memberGenderStr = req.getParameter("memberGender");

			if (memberGenderStr == null || !memberGenderStr.trim().matches("[1-2]")) {
				errorMsgs.add("會員性別只能為 1（男）或 2（女）");
			} else {
				memberGender = Byte.valueOf(memberGenderStr.trim());
			}

			String memberEmail = req.getParameter("memberEmail");
			if (memberEmail == null || memberEmail.trim().isEmpty()) {
				errorMsgs.add("電子郵件: 請勿空白");
			} else if (!memberEmail.trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				errorMsgs.add("電子郵件: 格式不正確，請輸入有效的電子郵件地址");
			}

			String memberTel = req.getParameter("memberTel");
			// 檢查是否為空，允許空值
			if (memberTel != null && !memberTel.trim().isEmpty()) {
			    memberTel = memberTel.trim();
			    // 檢查是否小於 10 個數字
			    if (!memberTel.matches("^\\d{1,9}$")) {
			        errorMsgs.add("電話號碼格式不正確，長度必須小於 10 個數字");
			    }
			}

			String memberAdd = req.getParameter("memberAdd");
			// 地址可以為空，但若有輸入需檢查長度
			if (memberAdd != null && !memberAdd.trim().isEmpty()) {
				memberAdd = memberAdd.trim(); // 去除多餘空格
				if (memberAdd.length() > 100) {
					errorMsgs.add("地址: 長度不能超過 100 個字");
				}
			}

			String memberAcc = req.getParameter("memberAcc");
			String memberPw = req.getParameter("memberPw");
			// 帳號檢查
			if (memberAcc == null || memberAcc.trim().isEmpty()) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (memberAcc.length() > 25) {
				errorMsgs.add("帳號: 長度不得超過 25 個字元");
			} else if (!memberAcc.matches("^[a-zA-Z0-9_]+$")) {
				errorMsgs.add("帳號: 僅允許英文字母、數字和底線");
			}

			// 密碼檢查
			if (memberPw == null || memberPw.trim().isEmpty()) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (memberPw.length() > 25) {
				errorMsgs.add("密碼: 長度不得超過 25 個字元");
			} else if (!memberPw.matches("^[a-zA-Z0-9@#$%^&+=]+$")) {
				errorMsgs.add("密碼: 僅允許英文字母、數字和特殊符號 @#$%^&+=");
			}

			byte memberStatus = 0;
			String memberStr = req.getParameter("memberStatus").trim();
			if (memberStr == null || (!memberStr.equals("0") && !memberStr.equals("1"))) {
				errorMsgs.add("案件狀態: 只能為0（申訴成功）或1（申訴失敗）");
			} else {
				memberStatus = Byte.valueOf(memberStr);
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMemberId(memberId);
			memberVO.setMemberLvId(memberLvId);
			memberVO.setMemberName(memberName);
			memberVO.setMemberUid(memberUid);
			memberVO.setMemberBth(memberBth);
			memberVO.setMemberGender(memberGender);
			memberVO.setMemberEmail(memberEmail);
			memberVO.setMemberTel(memberTel);
			memberVO.setMemberAdd(memberAdd);
			memberVO.setMemberAcc(memberAcc);
			memberVO.setMemberPw(memberPw);
			memberVO.setMemberStatus(memberStatus);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(memberId, memberLvId, memberName, memberUid, memberBth, memberGender, memberEmail, memberTel,memberAdd, memberAcc, memberPw, memberStatus);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			/*************************** 2.開始刪除資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.deleteMember(memberId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 
			successView.forward(req, res);
		}
	}
}
