<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: "yy-mm-dd"
        });
    });
</script>

<form method="post" action="${CURRENT_MAPPING}/passengerCreating">

    <td><b>${performance}</b></td>

    <input type="hidden" name="idFlight" value="${idFlight}"/>

    <table class="tableCreatingAndModifying">
        <tr>
            <td class="column">First name</td>
            <td>
                <input type="text" name="firstName" value="${firstName}">
            </td>
        </tr>
        <tr>
            <td class="column">Last name</td>
            <td>
                <input type="text" name="lastName" value="${lastName}">
            </td>
        </tr>
        <tr>
            <td class="column">Nationality</td>
            <td>
                <input type="text" name="nationality" value="${nationality}">
            </td>
        </tr>
        <tr>
            <td class="column">Passport</td>
            <td>
                <input type="text" name="passport" value="${passport}">
            </td>
        </tr>
        <tr>
            <td class="column">Date of birthday</td>
            <td>
                <input type="text" name="dateOfBirthday" id="datepicker" value="${dateOfBirthday}">
            </td>
        </tr>
        <tr>
            <td class="column">Sex</td>
            <td>
                <select id="currentGender" name="selectedGender">
                    <option value=""></option>
                    <c:forEach var="currentGender" items="${genders}">
                        <c:choose>
                            <c:when test="${currentGender.name() eq gender}">
                                <option selected="selected"
                                        value="${gender}">${gender}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${currentGender.name()}">${currentGender.name()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="column">Class</td>
            <td>
                <select id="currentClass" name="selectedClass">
                    <option value=""></option>
                    <c:forEach var="currentClass" items="${classes}">
                        <c:choose>
                            <c:when test="${currentClass.name() eq flightClass}">
                                <option selected="selected"
                                        value="${flightClass}">${flightClass}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${currentClass.name()}">${currentClass.name()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <td></td>
        <td>
            <input type="submit" value="Create">
        </td>
    </table>
    <jsp:include page="/WEB-INF/JSP/ErrorMessage.jsp"/>
</form>
