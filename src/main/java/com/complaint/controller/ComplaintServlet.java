	package com.complaint.controller;
	
	import java.io.*;
	import java.sql.Timestamp;
	import java.util.*;
	
	import javax.servlet.*;
	import javax.servlet.http.*;
	
	import com.complaint.model.ComplaintService;
	import com.complaint.model.ComplaintVO;
	
	public class ComplaintServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;
	
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}
	
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
	
			if ("getOne_For_Display".equals(action)) { // 來自 select_page.jsp 的請求
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	
				String str = req.getParameter("complaintId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入申訴案件編號");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	
				Integer complaintId = null;
				try {
					complaintId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("客服案件編號格式不正確，請輸入數字");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	
				ComplaintService compSvc = new ComplaintService();
				ComplaintVO complaintVO = compSvc.getOneComplaint(complaintId);
				if (complaintVO == null) {
					errorMsgs.add("查無資料");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	
				req.setAttribute("complaintVO", complaintVO);
				String url = "/back-end/emp/listOneComplaint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
	
			if ("getOne_For_Update".equals(action)) { // 來自 listAllComplaint.jsp 的請求
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	
				Integer complaintId = Integer.valueOf(req.getParameter("complaintId"));
	
				ComplaintService complaintSvc = new ComplaintService();
				ComplaintVO complaintVO = complaintSvc.getOneComplaint(complaintId);
	
				req.setAttribute("complaintVO", complaintVO);
				String url = "/back-end/emp/update_complaint_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
	
			if ("update".equals(action)) { // 來自 update_complaint_input.jsp 的請求
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	
				Integer complaintId = Integer.valueOf(req.getParameter("complaintId").trim());
				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				Integer caseId = Integer.valueOf(req.getParameter("caseId").trim());
	
				String complaintCon = req.getParameter("complaintCon");
	
				String complaintConReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,300}$";
				if (complaintCon == null || complaintCon.trim().length() == 0) {
					errorMsgs.add("案件內容: 請勿空白");
				} else if (!complaintCon.trim().matches(complaintConReg)) {
					errorMsgs.add("案件內容: 只能是中、英文字母、數字和_ , 且長度必須在10到300之間");
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
				} else if (!complaintResult.trim().matches(complaintrsReg)) {
					errorMsgs.add("處理結果: 只能是中、英文字母、數字和_ , 且長度必須在10到300之間");
				}
	
				ComplaintVO complaintVO = new ComplaintVO();
				complaintVO.setComplaintId(complaintId);
				complaintVO.setMemberId(memberId);
				complaintVO.setCaseId(caseId);
				complaintVO.setComplaintCon(complaintCon);
				complaintVO.setComplaintTime(complaintTime);
				complaintVO.setComplaintStatus(complaintStatus);
				complaintVO.setComplaintResult(complaintResult);
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("complaintVO", complaintVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_complaint_input.jsp");
					failureView.forward(req, res);
					return;
				}
	
				ComplaintService compSvc = new ComplaintService();
				complaintVO = compSvc.updateComplaint(complaintId, memberId, caseId, complaintCon, complaintTime,
						complaintStatus, complaintResult);
	
				req.setAttribute("complaintVO", complaintVO);
				String url = "/back-end/emp/listOneComplaint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
	
			if ("insert".equals(action)) { // 来自 addComplaint.jsp 的请求
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
	
				// 接收请求参数，保留其他资料不变
				Integer complaintId = Integer.valueOf(req.getParameter("complaintId").trim());
				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				Integer caseId = Integer.valueOf(req.getParameter("caseId").trim());
	
				String complaintCon = req.getParameter("complaintCon");
				if (complaintCon == null || complaintCon.trim().isEmpty()) {
					errorMsgs.add("案件内容: 请勿空白");
				}
	
				Timestamp complaintTime = Timestamp.valueOf(req.getParameter("complaintTime").trim());
				byte complaintStatus = Byte.valueOf(req.getParameter("complaintStatus").trim());
				String complaintResult = req.getParameter("complaintResult");
	
				ComplaintVO complaintVO = new ComplaintVO();
				complaintVO.setComplaintId(complaintId);
				complaintVO.setMemberId(memberId);
				complaintVO.setCaseId(caseId);
				complaintVO.setComplaintCon(complaintCon);
				complaintVO.setComplaintTime(complaintTime);
				complaintVO.setComplaintStatus(complaintStatus);
				complaintVO.setComplaintResult(complaintResult);
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("complaintVO", complaintVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addComplaint.jsp");
					failureView.forward(req, res);
					return;
				}
	
				ComplaintService compSvc = new ComplaintService();
				complaintVO = compSvc.addComplaint(complaintId, memberId, caseId, complaintCon, complaintTime,
						complaintStatus, complaintResult);
	
				// 成功插入数据后，重定向到 listAllComplaint.jsp
				String url = req.getContextPath() + "/back-end/emp/listAllComplaint.jsp";
				res.sendRedirect(url);
			}
	
		}
	}
