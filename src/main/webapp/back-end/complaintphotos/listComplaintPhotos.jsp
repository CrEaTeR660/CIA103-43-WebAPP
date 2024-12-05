<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>查看申訴照片列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">申訴照片列表</h1>
        <div class="card p-4 shadow">
            <!-- 檢查是否有照片 -->
            <c:if test="${not empty photoList}">
                <c:forEach var="photo" items="${photoList}">
                    <div class="mb-4">
                        <p><strong>照片編號:</strong> ${photo.comPicId}</p>
                        <p><strong>申訴編號:</strong> ${photo.comId}</p>
                        <p><strong>圖片檔案名稱:</strong> ${photo.fileName}</p>
                        <p><strong>上傳時間:</strong> ${photo.uploadTime}</p>
                        <!-- 顯示圖片 -->
                        <img src="complaintPhotos?action=view&comPicId=${photo.comPicId}" alt="圖片" class="img-fluid">
                    </div>
                </c:forEach>
            </c:if>
            <!-- 沒有圖片時的提示 -->
            <c:if test="${empty photoList}">
                <p class="text-center text-danger">目前沒有任何申訴照片。</p>
            </c:if>
        </div>
        <div class="text-center mt-3">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/back-end/complaintphotos/select_page.jsp">回到首頁</a>
        </div>
    </div>
</body>
</html>
