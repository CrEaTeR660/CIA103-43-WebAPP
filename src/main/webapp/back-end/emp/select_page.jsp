<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.complaint.model.*"%>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>申訴案件管理-首頁</title>

    <!-- 引入 Google 字型 -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- 引入 Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- 引入 Bootstrap JS 需要的依賴 (jQuery 和 Popper.js) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- 引入 FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to right, #4facfe, #00f2fe); /* 背景漸層 */
            font-family: 'Roboto', sans-serif;
        }

        h3 {
            color: #ffffff;
            font-weight: 700;
            font-size: 36px;
        }

        h4 {
            color: #333;
            font-weight: 500;
        }

        .container {
            max-width: 960px;
            margin-top: 40px;
        }

        .card {
            margin-top: 20px;
            padding: 30px;
            border-radius: 15px;
            background-color: #ffffff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        .sidebar {
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            height: 100%;
            background-color: #f8f9fa;
            padding: 20px;
            box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
        }

        .sidebar img {
            width: 100%;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .sidebar h4 {
            color: #333;
            font-weight: 600;
            margin-bottom: 20px;
        }

        .sidebar .btn {
            width: 100%;
            margin-bottom: 15px;
            padding: 12px;
            border-radius: 8px;
        }

        .btn-primary {
            background-color: #007BFF;
            border-color: #007BFF;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
            transform: translateY(-2px);
        }

        .btn-info {
            background-color: #17a2b8;
            border-color: #17a2b8;
            color: #fff;
        }

        .btn-info:hover {
            background-color: #138496;
            border-color: #117a8b;
            transform: translateY(-2px);
        }

        .content-area {
            margin-left: 270px; /* 留出侧边栏的空间 */
            padding: 20px;
        }

        .alert-danger {
            margin-bottom: 20px;
            border-radius: 8px;
            background-color: #f8d7da;
            color: #721c24;
            padding: 15px;
        }
    </style>
</head>

<body>

    <!-- 侧边栏 -->
    <div class="sidebar">
        <img src="<%= request.getContextPath() %>/back-end/emp/images/c8763.gif" alt="Sidebar Image" class="img-fluid">
        <h4>申訴案件管理</h4>
        <!-- 申訴案件查詢 -->
        <ul class="list-unstyled">
            <li><a href="addComplaint.jsp" class="btn btn-success"><i class="fas fa-plus-circle"></i> 新增案件</a></li>
            <li><a href="listAllComplaint.jsp" class="btn btn-info"><i class="fas fa-list"></i> 查看全部案件</a></li>
        </ul>
    </div>

    <!-- 主要内容区 -->
    <div class="content-area">
        <div class="container">
            <!-- 頁面標題 -->
            <div class="row mb-4 text-center">
                <div class="col-12">
                    <h3>申訴案件管理</h3>
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

            <!-- 申訴資料查詢 -->
            <div class="card">
                <h4>申訴資料查詢:</h4>
                <ul class="list-unstyled">
                    <!-- 查詢申訴編號 -->
                    <li>
                        <form method="post" action="emp.do">
                            <div class="form-group">
                                <label for="complaintIdInput"><b>輸入申訴編號:</b></label>
                                <input type="text" name="complaintId" class="form-control" id="complaintIdInput">
                            </div>
                            <input type="hidden" name="action" value="getOne_For_Display">
                            <button type="submit" class="btn btn-primary">送出</button>
                        </form>
                    </li>

                    <!-- 查詢會員編號 -->
                    <li>
                        <form method="post" action="emp.do">
                            <div class="form-group">
                                <label for="memberIdInput"><b>輸入會員編號:</b></label>
                                <input type="text" name="memberId" class="form-control" id="memberIdInput">
                            </div>
                            <input type="hidden" name="action" value="getOne_For_Display">
                            <button type="submit" class="btn btn-primary">送出</button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</body>
</html>
