<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.complaintphotos.model.ComplaintPhotosVO" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>查看申訴照片</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">查看申訴照片</h1>
        <div class="card p-4 shadow">
            <%
                ComplaintPhotosVO vo = (ComplaintPhotosVO) request.getAttribute("complaintPhoto");
                if (vo != null) {
            %>
                <p><strong>照片編號:</strong> <%= vo.getComPicId() %></p>
                <p><strong>申訴編號:</strong> <%= vo.getComId() %></p>
                <p><strong>圖片檔案名稱:</strong> <%= vo.getFileName() %></p>
                <p><strong>上傳時間:</strong> <%= vo.getUploadTime() %></p>
                <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(vo.getComPic()) %>" alt="圖片" class="img-fluid">
            <%
                } else {
            %>
                <p class="text-center text-danger">找不到對應的圖片資料。</p>
            <%
                }
            %>
        </div>
        <div class="text-center mt-3">
            <a class="btn btn-secondary" href="<%= request.getContextPath() %>/back-end/complaintphotos/listComplaintPhotos.jsp">回到圖片列表</a>
        </div>
    </div>
</body>
</html>
