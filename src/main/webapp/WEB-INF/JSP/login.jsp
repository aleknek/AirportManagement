<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="login_form_container">
    <form action="/login" method="post">

        <table id="login_table">
            <tr>
                <td colspan="2" class="caption">Entrance</td>
            </tr>
            <tr>
                <td>Name:</td>
                <td>
                    <input type="text" name="username" class="text" value=""/>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input type="password" name="password" class="text"/>
                </td>
            </tr>
            <tr>
                <td>Choose role:</td>
                <td><select name="role">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr height="60">
                <td>&nbsp</td>
                <td>
                    <input type="submit" value="come in" id="button">
                </td>
            </tr>
        </table>
        <jsp:include page="/WEB-INF/JSP/ErrorMessage.jsp"/>
    </form>
</div>