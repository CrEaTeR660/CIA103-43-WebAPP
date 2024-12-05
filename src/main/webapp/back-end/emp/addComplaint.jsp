<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.complaint.model.*"%>

<%
    ComplaintVO complaintVO = (ComplaintVO) request.getAttribute("complaintVO");
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>申訴資料新增 - addComplaint.jsp</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 30px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .header {
            text-align: center;
            margin-bottom: 30px;
        }

        h3 {
            color: #333;
            font-size: 24px;
            margin: 0;
        }

        .back-home {
            display: inline-block;
            margin-top: 20px;
        }

        .back-home img {
            width: 80px;
            height: 80px;
        }

        h4 {
            color: #0056b3;
            font-size: 18px;
        }

        table {
            width: 100%;
            margin: 20px 0;
            border-collapse: collapse;
            font-size: 16px;
        }

        table th, table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #D9CBCB;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        .button {
            padding: 10px 20px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .button:hover {
            background-color: #4cae4c;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }

    </style>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/datetimepicker/jquery.datetimepicker.css" />
</head>

<body>
    <div class="container">
        <div class="header">
            <h3>申訴資料新增 - addComplaint.jsp</h3>
            <a href="select_page.jsp" class="back-home">
                <img src="<%= request.getContextPath()%>/back-end/emp/images/nomercy.png" alt="回首頁">
            </a>
        </div>

        <h4>資料新增:</h4>

        <c:if test="${not empty errorMsgs}">
            <div class="error-message">
                <font>請修正以下錯誤:</font>
                <ul>
                    <c:forEach var="message" items="${errorMsgs}">
                        <li>${message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/back-end/emp/emp.do" method="post">
    <input type="hidden" name="action" value="insert">
    
    <!-- 只读字段展示 -->
    <label for="complaintId">案件編號:</label>
    <input type="text" id="complaintId" name="complaintId" value="${complaintVO.complaintId}" readonly><br>

    <label for="memberId">會員編號:</label>
    <input type="text" id="memberId" name="memberId" value="${complaintVO.memberId}" readonly><br>

    <label for="caseId">案件編號:</label>
    <input type="text" id="caseId" name="caseId" value="${complaintVO.caseId}" readonly><br>

    <label for="complaintTime">申訴時間:</label>
    <input type="text" id="complaintTime" name="complaintTime" value="${complaintVO.complaintTime}" readonly><br>

    <!-- 允许编辑的案件内容 -->
    <label for="complaintCon">案件內容:</label>
    <textarea id="complaintCon" name="complaintCon">${complaintVO.complaintCon}</textarea><br>

    <label for="complaintStatus">案件狀態:</label>
    <select id="complaintStatus" name="complaintStatus" disabled>
        <option value="0" ${complaintVO.complaintStatus == 0 ? 'selected' : ''}>申訴成功</option>
        <option value="1" ${complaintVO.complaintStatus == 1 ? 'selected' : ''}>申訴失敗</option>
    </select><br>

    <label for="complaintResult">處理結果:</label>
    <textarea id="complaintResult" name="complaintResult" readonly>${complaintVO.complaintResult}</textarea><br>

    <button type="submit">申訴</button>
</form>

    </div>

    <script src="<%= request.getContextPath() %>/back-end/datetimepicker/jquery.js"></script>
    <script src="<%= request.getContextPath() %>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>
    <script>
        $(document).ready(function() {
            $.datetimepicker.setLocale('zh');
            $('#f_date1').datetimepicker({
                timepicker: false,
                format: 'Y-m-d H:i',
                value: '<%=complaintVO != null ? complaintVO.getComplaintTime() : ""%>',
                minDate: 'today' // 限制只能選擇當前日期及之後的日期
            });
        });
    </script>
</body>
</html>
