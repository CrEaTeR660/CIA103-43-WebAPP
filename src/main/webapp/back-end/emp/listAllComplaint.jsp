<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.complaint.model.*"%>

<%
ComplaintService complaintSvc = new ComplaintService();
List<ComplaintVO> list = complaintSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>所有申訴資料 - listAllComplaint.jsp</title>

    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h3 {
            text-align: center;
            font-size: 28px;
            color: #333;
            margin-top: 30px;
        }

        .back-home {
            display: block;
            text-align: center;
            margin-bottom: 30px;
        }

        .back-home button {
            font-size: 16px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .back-home button:hover {
            background-color: #0056b3;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table th, table td {
            padding: 12px;
            text-align: center;
            border: 1px solid #e0e0e0;
        }

        table th {
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
        }

        table td {
            font-size: 14px;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        .btn-edit {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        .btn-edit:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
        }
    </style>

</head>
<body>

    <h3>所有申訴資料</h3>

    <div class="back-home">
        <!-- 使用 Bootstrap 按钮并添加动态效果 -->
        <button onclick="window.location.href='select_page.jsp'">回首頁</button>
    </div>

    <table>
        <thead>
            <tr>
                <th>申訴編號</th>
                <th>會員編號</th>
                <th>案件編號</th>
                <th>申訴案件內容</th>
                <th>申訴日期</th>
                <th>申訴狀態</th>
                <th>處理結果</th>
                <th>修改</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="complaintVO" items="${list}">
                <tr>
                    <td>${complaintVO.complaintId}</td>
                    <td>${complaintVO.memberId}</td>
                    <td>${complaintVO.caseId}</td>
                    <td>${complaintVO.complaintCon}</td>
                    <td>
                        <fmt:formatDate value="${complaintVO.complaintTime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${complaintVO.complaintStatus == 0}">申訴成功</c:when>
                            <c:when test="${complaintVO.complaintStatus == 1}">申訴失敗</c:when>
                            <c:otherwise>未知</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${complaintVO.complaintResult}</td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/back-end/emp/emp.do">
                            <input type="hidden" name="complaintId" value="${complaintVO.complaintId}">
                            <input type="hidden" name="action" value="getOne_For_Update">
                            <input type="submit" value="修改" class="btn-edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 引入 Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
