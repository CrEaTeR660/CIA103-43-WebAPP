package com.complaint.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.complaint.model.ComplaintService;
import com.complaint.model.ComplaintVO;

public class ComplaintServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {  //來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("complaintId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入客服案件編號");
			}
//			 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return; //回傳錯誤訊息
			}

			Integer complaintId = null;
			try {
				complaintId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("客服案件編號格式不正確");
			}
//			 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ComplaintService compSvc = new ComplaintService();
			ComplaintVO complaintVO = compSvc.getOneComplaint(complaintId);
			if (complaintVO == null) {
				errorMsgs.add("查無資料");
			}
			 //Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("complaintVO", complaintVO);  //資料庫取出的complaintVO物件,存入req
			String url = "/emp/listOneComplaint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);  //成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {  //來自listAllComplaint.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer complaintId = Integer.valueOf(req.getParameter("complaintId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ComplaintService complaintSvc = new ComplaintService();
			ComplaintVO complaintVO = complaintSvc.getOneComplaint(complaintId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("complaintVO", complaintVO);  //資料庫取出的complaintVO物件,存入req
			String url = "/emp/update_complaint_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) {  //來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer complaintId = Integer.valueOf(req.getParameter("complaintId").trim());

			String complaintCon = req.getParameter("complaintCon");
			String complaintConReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,300}$";
			if (complaintCon == null || complaintCon.trim().length() == 0) {
				errorMsgs.add("案件內容: 請勿空白");
			} else if (!complaintCon.trim().matches(complaintConReg)) {  //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("案件內容: 只能是中、英文字母、數字和_ , 且長度必需在10到300之間");
			}

			Timestamp complaintTime = null;
			try {
				complaintTime = Timestamp.valueOf(req.getParameter("complaintTime").trim());
			} catch (IllegalArgumentException e) {
				complaintTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			byte complaintStatus = 0;
			String statusStr = req.getParameter("complaintStatus").trim();
		    if (statusStr == null || (!statusStr.equals("0") && !statusStr.equals("1"))) {
		        errorMsgs.add("案件狀態: 只能為0（申訴成功）或1（申訴失敗）");
		    } else {
		        complaintStatus = Byte.valueOf(statusStr);
		    }
			
		    String complaintResult = req.getParameter("complaintResult");
			String complaintrsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,300}$";
			if (complaintResult == null || complaintResult.trim().length() == 0) {
				errorMsgs.add("處理結果: 請勿空白");
			} else if (!complaintResult.trim().matches(complaintrsReg)) {  //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("處理結果: 只能是中、英文字母、數字和_ , 且長度必需在10到300之間");
			}

			Integer memberId = Integer.valueOf(req.getParameter("mem_id").trim());

			ComplaintVO complaintVO = new ComplaintVO();
			complaintVO.setComplaintId(complaintId);
//			complaintVO.setMemberId(memberId);
//			complaintVO.setCaseId(caseId);
			complaintVO.setComplaintCon(complaintCon);
			complaintVO.setComplaintTime(complaintTime);
			complaintVO.setComplaintStatus(complaintStatus);
			complaintVO.setComplaintResult(complaintResult);
			
//			 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("compVO", complaintVO);  //含有輸入格式錯誤的complaintVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_complaint_input.jsp");
				failureView.forward(req, res);
				return;  //程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ComplaintService compSvc = new ComplaintService();
			complaintVO = compSvc.updateComplaint(complaintId, complaintCon, complaintTime, complaintStatus, complaintResult);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("complaintVO", complaintVO);  //資料庫update成功後,正確的的complaintVO物件,存入req
			String url = "/emp/listOneComplaint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);  //listOneComplaint.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {  //來自addComplaint.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer complaintId = Integer.valueOf(req.getParameter("complaintId").trim());
			String complaintCon = req.getParameter("complaintCon");
			String complaintConReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{0,300}$";
			if (complaintCon == null || complaintCon.trim().length() == 0) {
				errorMsgs.add("案件內容: 請勿空白");
			} else if (!complaintCon.trim().matches(complaintConReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("案件內容: 只能是中、英文字母、數字和_ , 且長度必需在0到300之間");
			}

			Timestamp complaintTime = null;
			try {
				complaintTime = Timestamp.valueOf(req.getParameter("complaintTime").trim());
			} catch (IllegalArgumentException e) {
				complaintTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			byte complaintStatus = 0;
			String statusStr = req.getParameter("complaintStatus").trim();
		    if (statusStr == null || (!statusStr.equals("0") && !statusStr.equals("1"))) {
		        errorMsgs.add("案件狀態: 只能為0（申訴成功）或1（申訴失敗）");
		    } else {
		        complaintStatus = Byte.valueOf(statusStr);
		    }
			
		    String complaintResult = req.getParameter("complaintResult");
			String complaintrsReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,300}$";
			if (complaintResult == null || complaintResult.trim().length() == 0) {
				errorMsgs.add("處理結果: 請勿空白");
			} else if (!complaintResult.trim().matches(complaintrsReg)) {  //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("處理結果: 只能是中、英文字母、數字和_ , 且長度必需在10到300之間");
			}

//			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

			ComplaintVO complaintVO = new ComplaintVO();
			complaintVO.setComplaintId(complaintId);
//			complaintVO.setMemberId(memberId);
//			complaintVO.setCaseId(caseId);
			complaintVO.setComplaintCon(complaintCon);
			complaintVO.setComplaintTime(complaintTime);
			complaintVO.setComplaintStatus(complaintStatus);
			complaintVO.setComplaintResult(complaintResult);

//			 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("complaintVO", complaintVO);  //含有輸入格式錯誤的complaintVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ComplaintService compSvc = new ComplaintService();
			complaintVO = compSvc.addComplaint(complaintId, complaintCon, complaintTime, complaintStatus, complaintResult);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);  //新增成功後轉交listAllComplaint.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {  //來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer complaintId = Integer.valueOf(req.getParameter("complaintId"));

			/*************************** 2.開始刪除資料 ***************************************/
			ComplaintService compSvc = new ComplaintService();
			compSvc.deleteComplaint(complaintId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/emp/listAllComplaint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}


