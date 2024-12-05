package com.member.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.membermanage.model.*;

public class MemberManageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 常量定義 URL 路徑
    private static final String LIST_ALL_URL = "/back-end/membermanage/listAllMemberManage.jsp";
    private static final String UPDATE_MEMBER_URL = "/back-end/membermanage/updateMemberManage.jsp";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("[訊息] 收到 GET 請求，轉交至 POST 處理。");
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("[訊息] 開始處理 POST 請求。");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        System.out.println("[訊息] 請求的動作為：" + action);

        if ("getAll".equals(action)) { // 顯示全部會員
            System.out.println("[訊息] 動作：顯示全部會員列表。");
            try {
                MemberManageService service = new MemberManageService();
                List<MemberManageVO> memberList = service.getAllMembers();
                System.out.println("[訊息] 查詢到會員數量：" + memberList.size());

                for (MemberManageVO member : memberList) {
                    System.out.println("[除錯] 會員編號：" + member.getMemberId() + "，姓名：" + member.getMemberName());
                }

                req.setAttribute("memberList", memberList);
                RequestDispatcher successView = req.getRequestDispatcher(LIST_ALL_URL);
                successView.forward(req, res);
            } catch (Exception e) {
                System.err.println("[錯誤] 顯示全部會員時發生錯誤：" + e.getMessage());
                req.setAttribute("errorMsg", "系統發生錯誤，無法顯示會員列表：" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(LIST_ALL_URL);
                failureView.forward(req, res);
            }
        }

        if ("getOne".equals(action)) { // 查詢單一會員
            System.out.println("[訊息] 動作：查詢單一會員。");
            try {
                Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
                System.out.println("[訊息] 查詢的會員編號為：" + memberId);

                MemberManageService service = new MemberManageService();
                MemberManageVO member = service.getOneMember(memberId);

                if (member == null) {
                    System.out.println("[警告] 查無此會員，會員編號：" + memberId);
                    req.setAttribute("errorMsg", "查無此會員！");
                } else {
                    System.out.println("[訊息] 查詢成功，會員姓名：" + member.getMemberName());
                    req.setAttribute("member", member);
                }
                RequestDispatcher successView = req.getRequestDispatcher(UPDATE_MEMBER_URL);
                successView.forward(req, res);
            } catch (Exception e) {
                System.err.println("[錯誤] 查詢單一會員時發生錯誤：" + e.getMessage());
                req.setAttribute("errorMsg", "查詢失敗：" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(LIST_ALL_URL);
                failureView.forward(req, res);
            }
        }

        if ("update".equals(action)) { // 更新會員等級與狀態
            System.out.println("[訊息] 動作：更新會員資料。");
            try {
                Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
                Byte memberLvId = Byte.valueOf(req.getParameter("memberLvId").trim());
                Byte memberStatus = Byte.valueOf(req.getParameter("memberStatus").trim());

                System.out.println("[訊息] 更新會員編號：" + memberId + "，等級：" + memberLvId + "，狀態：" + memberStatus);

                MemberManageService service = new MemberManageService();
                service.updateMemberLevelAndStatus(memberId, memberLvId, memberStatus);

                System.out.println("[訊息] 會員更新成功，會員編號：" + memberId);
                res.sendRedirect("MemberManageServlet?action=getAll");
            } catch (Exception e) {
                System.err.println("[錯誤] 更新會員資料時發生錯誤：" + e.getMessage());
                req.setAttribute("errorMsg", "更新失敗：" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(UPDATE_MEMBER_URL);
                failureView.forward(req, res);
            }
        }
    }
}
