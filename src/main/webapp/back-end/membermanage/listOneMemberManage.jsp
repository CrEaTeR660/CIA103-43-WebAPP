<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>單一會員詳細資料</title>
</head>
<body>
    <h1>會員詳細資料</h1>

    <!-- 顯示錯誤訊息 -->
    <c:if test="${not empty errorMsg}">
        <p style="color: red;">${errorMsg}</p>
    </c:if>

    <p>會員編號：${member.memberId}</p>
    <p>會員姓名：${member.memberName}</p>
    <p>會員身分證字號：${member.memberUid}</p>
    <p>會員生日：${member.memberBth}</p>
    <p>會員性別：${member.memberGender == 1 ? '男' : '女'}</p>
    <p>會員電子郵件：${member.memberEmail}</p>
    <p>會員手機電話：${member.memberTel}</p>
    <p>會員地址：${member.memberAdd}</p>
    <p>會員等級：${member.memberLvId == 0 ? '一般會員' : '白金會員'}</p>
    <p>會員狀態：${member.memberStatus == 0 ? '正常' : '停權'}</p>

    <br><br>
    <a href="${pageContext.request.contextPath}/back-end/membermanage.do?action=getOne&memberId=${member.memberId}">編輯會員資料</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/back-end/membermanage.do?action=getAll">返回會員列表</a>
</body>
</html>
