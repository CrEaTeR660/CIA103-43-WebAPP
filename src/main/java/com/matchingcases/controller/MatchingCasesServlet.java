package com.matchingcases.controller;

import com.matchingcases.model.MatchingCasesService;
import com.matchingcases.model.MatchingCasesVO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MatchingCasesServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MatchingCasesService service;

    @Override
    public void init() throws ServletException {
        service = new MatchingCasesService();
    }

    // 顯示所有案件
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<MatchingCasesVO> allCases = service.getAllMatchingCases();
            request.setAttribute("allCases", allCases);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/back-end/matchingcases/listMatchingCases.jsp");
            dispatcher.forward(request, response);
        } else if ("view".equals(action)) {
            int caseId = Integer.parseInt(request.getParameter("caseId"));
            MatchingCasesVO caseDetails = service.getMatchingCaseById(caseId);
            request.setAttribute("caseDetails", caseDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/back-end/matchingcases/viewMatchingCase.jsp");
            dispatcher.forward(request, response);
        }
    }

    // 新增案件
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int memId = Integer.parseInt(request.getParameter("memId"));
            int receiverId = Integer.parseInt(request.getParameter("receiverId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String budget = request.getParameter("budget");
            String caseTot = request.getParameter("caseTot");

            // 轉換資料
            MatchingCasesVO newCase = new MatchingCasesVO();
            newCase.setMemId(memId);
            newCase.setReceiverId(receiverId);
            newCase.setTitle(title);
            newCase.setDescription(description);
            newCase.setBudget(new java.math.BigDecimal(budget));
            newCase.setCaseTot(Integer.parseInt(caseTot));

            // 新增案件
            matchingCasesService.addMatchingCase(newCase);

            // 跳轉回案件列表頁面
            response.sendRedirect(request.getContextPath() + "/back-end/matchingcases/matchingCasesList.jsp");
        }
    }
}
