<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>전화번호관리 정보시스템</title>
    <style>
        .input-section, .output-section, .selected-section {
            margin: 10px;
            padding: 10px;
            border: 1px solid #000;
        }
        .input-section input {
            margin-bottom: 5px;
        }
        .output-section div, .selected-section div {
            margin-bottom: 5px;
        }
        .selected-section div {
            color: red;
        }
    </style>
</head>
<body>
    <h2>전화번호 관리 시스템</h2>

    <!-- 1) 입력 기능 -->
    <div class="input-section">
        <form action="insertPhonebook" method="post">
            <label>이름: <input type="text" name="name" required></label><br>
            <label>전화번호: <input type="text" name="hp" required></label><br>
            <label>메모: <input type="text" name="memo"></label><br>
            <button type="submit">전화번호 입력</button>
        </form>
    </div>

    <!-- 2) 전체 출력 기능 / 3) 검색 출력 기능 -->
    <div class="output-section">
        <form action="searchPhonebook" method="get">
            <label>검색: <input type="text" name="search"></label>
            <button type="submit">검색</button>
        </form>

        <c:forEach var="entry" items="${phonebookList}">
            <div>
                <a href="selectPhonebook?id=${entry.id}">
                    ${entry.name} - ${entry.hp}
                </a>
            </div>
        </c:forEach>
    </div>

    <!-- 4) 선택된 항목 출력 기능 -->
    <div class="selected-section">
        <c:if test="${selectedEntry != null}">
            <div>이름: ${selectedEntry.name}</div>
            <div>전화번호: ${selectedEntry.hp}</div>
            <div>메모: ${selectedEntry.memo}</div>
            <form action="updatePhonebook" method="post">
                <input type="hidden" name="id" value="${selectedEntry.id}">
                <input type="text" name="name" value="${selectedEntry.name}" required>
                <input type="text" name="hp" value="${selectedEntry.hp}" required>
                <input type="text" name="memo" value="${selectedEntry.memo}">
                <button type="submit">수정</button>
                <button type="submit" formaction="deletePhonebook">삭제</button>
            </form>
        </c:if>
    </div>
</body>
</html>