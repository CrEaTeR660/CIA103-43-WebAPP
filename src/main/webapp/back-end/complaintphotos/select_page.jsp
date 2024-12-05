<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>申訴圖片管理系統</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome (用於圖標) -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f9f9f9, #e9ecef);
            min-height: 100vh;
        }
        .card {
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }
        footer {
            margin-top: 50px;
            background-color: #343a40;
            color: white;
            padding: 10px 0;
        }
        footer a {
            color: #6c757d;
            text-decoration: none;
        }
        footer a:hover {
            color: white;
        }
    </style>
</head>
<body>
    <!-- 頂部導航欄 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">圖片管理系統</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="切換導航">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/back-end/complaintphotos/addComplaintPhoto.jsp">新增照片</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/back-end/complaintphotos/listComplaintPhotos.jsp">查看照片列表</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 主內容區 -->
    <div class="container mt-5">
        <h1 class="text-center mb-5">申訴圖片管理系統</h1>
        <div class="row g-4 justify-content-center">
            <!-- 卡片：新增申訴照片 -->
            <div class="col-md-4">
                <div class="card text-center border-success">
                    <div class="card-body">
                        <i class="fas fa-plus-circle fa-3x text-success mb-3"></i>
                        <h5 class="card-title">新增申訴照片</h5>
                        <p class="card-text">上傳新的申訴照片，並記錄其相關資訊。</p>
                        <a href="<%= request.getContextPath() %>/back-end/complaintphotos/addComplaintPhoto.jsp" class="btn btn-success">新增照片</a>
                    </div>
                </div>
            </div>
            <!-- 卡片：查看照片列表 -->
            <div class="col-md-4">
                <div class="card text-center border-primary">
                    <div class="card-body">
                        <i class="fas fa-list fa-3x text-primary mb-3"></i>
                        <h5 class="card-title">查看申訴照片列表</h5>
                        <p class="card-text">瀏覽所有已上傳的申訴照片列表，並管理它們。</p>
                        <a href="<%= request.getContextPath() %>/back-end/complaintphotos/listComplaintPhotos.jsp" class="btn btn-primary">查看列表</a>
                    </div>
                </div>
            </div>
            <!-- 卡片：查看單張照片 -->
            <div class="col-md-4">
                <div class="card text-center border-info">
                    <div class="card-body">
                        <i class="fas fa-image fa-3x text-info mb-3"></i>
                        <h5 class="card-title">查看單張申訴照片</h5>
                        <p class="card-text">檢視特定的申訴照片及其詳細資訊。</p>
                        <a href="<%= request.getContextPath() %>/back-end/complaintphotos/viewComplaintPhoto.jsp" class="btn btn-info">查看照片</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 頁面底部 -->
    <footer class="text-center">
        <div class="container">
            <p>© 2024 申訴圖片管理系統 | <a href="mailto:support@example.com">聯絡我們</a></p>
        </div>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
