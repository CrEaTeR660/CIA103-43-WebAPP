<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員標籤管理</title>
    <link rel="stylesheet" href="styles.css"> <!-- 假設有一個 CSS 樣式表 -->
    <style>
        /* 表格樣式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .btn {
            padding: 5px 10px;
            color: white;
            background-color: #007bff;
            border: none;
            cursor: pointer;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn-danger {
            background-color: #dc3545;
        }
        .btn-info {
            background-color: #17a2b8;
        }
    </style>
</head>
<body>
    <h2>會員標籤管理</h2>

    <!-- 顯示標籤列表 -->
    <table>
        <thead>
            <tr>
                <th>標籤名稱</th>
                <th>標籤類型</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <!-- 從 request 中獲取 tags，並顯示 -->
            <c:forEach var="tag" items="${tags}">
                <tr>
                    <td>${tag.tagName}</td>
                    <td>${tag.tagTypeNo}</td>
                    <td>
                        <!-- 查看標籤詳細資料 -->
                        <a href="viewTagDetails.jsp?tagId=${tag.tagId}" class="btn btn-info">查看詳情</a>
                        
                        <!-- 刪除標籤 -->
                        <form action="removeTag" method="post" style="display:inline;">
                            <input type="hidden" name="tagId" value="${tag.tagId}">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('確定要刪除這個標籤嗎？');">刪除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr>

    <!-- 新增標籤的表單 -->
    <h3>新增標籤</h3>
    <form action="addTag" method="post">
        <label for="tagName">標籤名稱:</label>
        <input type="text" id="tagName" name="tagName" required>
        <br><br>
        <label for="tagTypeNo">標籤類型:</label>
        <input type="number" id="tagTypeNo" name="tagTypeNo" required>
        <br><br>
        <button type="submit" class="btn">新增標籤</button>
    </form>

</body>
</html>
