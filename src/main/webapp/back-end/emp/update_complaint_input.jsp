<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.complaint.model.*"%>

<%
    ComplaintVO complaintVO = (ComplaintVO) request.getAttribute("complaintVO");
%>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>申訴資料修改 - 更新申訴案件</title>

    <!-- 引入 Google 字型 -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- 引入 Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- 引入 FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f7f7f7;
            padding-top: 40px;
        }

        h3 {
            color: #333;
            font-weight: 700;
            font-size: 36px;
        }

        h4 {
            color: #007BFF;
        }

        .container {
            max-width: 900px;
            margin-top: 30px;
        }

        .card {
            margin-top: 20px;
            padding: 30px;
            border-radius: 15px;
            background-color: #ffffff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        .btn-primary {
            background-color: #007BFF;
            border-color: #007BFF;
            color: white;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
            transform: translateY(-2px);
        }

        .btn-info {
            background-color: #17a2b8;
            border-color: #17a2b8;
            color: white;
        }

        .btn-info:hover {
            background-color: #138496;
            border-color: #117a8b;
            transform: translateY(-2px);
        }

        .alert-danger {
            margin-bottom: 20px;
            border-radius: 8px;
            background-color: #f8d7da;
            color: #721c24;
            padding: 15px;
        }

        .form-group label {
            font-weight: 500;
        }

        .form-control {
            width: 100%;
            height: 35px;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

    <div class="container">
        <div class="row mb-4 text-center">
            <div class="col-12">
                <h3>申訴資料修改</h3>
                <h4>
                    <a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/images/back1.gif" width="100" height="32" border="0"> 回首頁</a>
                </h4>
            </div>
        </div>

        <!-- 错误消息显示区域 -->
        <c:if test="${not empty errorMsgs}">
            <div class="alert alert-danger">
                <strong>請修正以下錯誤:</strong>
                <ul>
                    <c:forEach var="message" items="${errorMsgs}">
                        <li>${message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <!-- 申訴資料修改表單 -->
        <div class="card">
            <form method="post" action="emp.do" name="form1">
                <div class="form-group">
                    <label for="complaintIdInput">申訴編號:</label>
                    <input type="text" class="form-control" name="complaintId" value="<%=complaintVO.getComplaintId()%>" readonly />
                </div>

                <div class="form-group">
                    <label for="memberIdInput">會員編號:</label>
                    <input type="text" class="form-control" name="memberId" value="<%=complaintVO.getMemberId()%>" readonly />
                </div>

                <div class="form-group">
                    <label for="caseIdInput">案件編號:</label>
                    <input type="text" class="form-control" name="caseId" value="<%=complaintVO.getCaseId()%>" readonly />
                </div>

                <div class="form-group">
                    <label for="complaintConInput">申訴案件內容:</label>
                    <textarea class="form-control" name="complaintCon" rows="4" readonly><%=complaintVO.getComplaintCon()%></textarea>
                </div>

                <div class="form-group">
                    <label for="complaintTimeInput">申訴日期:</label>
                    <input type="text" class="form-control" name="complaintTime" value="<%=complaintVO.getComplaintTime()%>" readonly />
                </div>

                <div class="form-group">
                    <label for="complaintStatusInput">申訴狀態:</label><br>
                    <input type="radio" name="complaintStatus" value="0" <c:if test="${complaintVO.complaintStatus == 0}">checked</c:if> /> 處理成功
                    <input type="radio" name="complaintStatus" value="1" <c:if test="${complaintVO.complaintStatus == 1}">checked</c:if> /> 處理失敗
                </div>

                <div class="form-group">
                    <label for="complaintResultInput">處理結果:</label>
                    <input type="text" class="form-control" name="complaintResult" value="<%=complaintVO.getComplaintResult()%>" />
                </div>

                <input type="hidden" name="action" value="update">
                <button type="submit" class="btn btn-primary">送出修改</button>
            </form>
        </div>
    </div>

    <!-- 引入 Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
