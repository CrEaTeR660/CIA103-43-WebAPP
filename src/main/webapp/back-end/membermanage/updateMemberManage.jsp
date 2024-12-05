<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>修改會員資料</title>
</head>
<body>
    <h1>修改會員資料</h1>

    <!-- 顯示錯誤訊息 -->
    <c:if test="${not empty errorMsg}">
        <p style="color: red;">${errorMsg}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/back-end/membermanage.do" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="memberId" value="${member.memberId}">
        
        <p>會員姓名：${member.memberName}</p>
        <p>會員身分證字號：${member.memberUid}</p>
        <p>會員生日：${member.memberBth}</p>
        <p>會員性別：${member.memberGender == 1 ? '男' : '女'}</p>
        <p>會員電子郵件：${member.memberEmail}</p>
        <p>會員手機電話：${member.memberTel}</p>
        <p>會員地址：${member.memberAdd}</p>
        
        <!-- 修改會員等級 -->
        <p>會員等級： 
            <select name="memberLvId">
                <option value="0" ${member.memberLvId == 0 ? 'selected' : ''}>一般會員</option>
                <option value="1" ${member.memberLvId == 1 ? 'selected' : ''}>白金會員</option>
            </select>
        </p>

        <!-- 修改會員狀態 -->
        <p>會員狀態： 
            <select name="memberStatus">
                <option value="0" ${member.memberStatus == 0 ? 'selected' : ''}>正常</option>
                <option value="1" ${member.memberStatus == 1 ? 'selected' : ''}>停權</option>
            </select>
        </p>

        <button type="submit">確認修改</button>
    </form>

    <br><br>
    <a href="${pageContext.request.contextPath}/back-end/membermanage.do?action=getAll">返回會員列表</a>
</body>
</html>
