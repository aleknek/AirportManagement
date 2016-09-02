<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: "yy-mm-dd"
        });
    });
</script>

<div>

    <h3>
        <left>To modify, enter new values and press the "Apply" button.</left>
    </h3>

    <form method="post" action="${CURRENT_MAPPING}/passengerModifying">

        <td><b>${performance}</b></td>

        <input type="hidden" name="idFlight" value="${idFlight}"/>

        <table class="tableCreatingAndModifying">
            <input type="hidden" name="id" value="${passenger.getId()}"/>
            <tr>
                <td class="column">First name</td>
                <td>
                    <input type="text" name="firstName" value="${passenger.getFirstName()}">
                </td>
            </tr>
            <tr>
                <td class="column">Last name</td>
                <td>
                    <input type="text" name="lastName" value="${passenger.getLastName()}">
                </td>
            </tr>
            <tr>
                <td class="column">Nationality</td>
                <td>
                    <input type="text" name="nationality" value="${passenger.getNationality()}">
                </td>
            </tr>
            <tr>
                <td class="column">Passport</td>
                <td>
                    <input type="text" name="passport" value="${passenger.getPassport()}">
                </td>
            </tr>
            <tr>
                <td class="column">Date of birthday</td>
                <td>
                    <input type="text" name="dateOfBirthday" id="datepicker" value="${passenger.getDateOfBirthday()}">
                </td>
            </tr>
            <tr>
                <td class="column">Sex</td>
                <td>
                    <select id="currentGender" name="selectedGender">
                        <c:forEach var="currentGender" items="${genders}">
                            <c:choose>
                                <c:when test="${currentGender.name() eq passenger.getGender().name()}">
                                    <option selected="selected"
                                            value="${passenger.getGender().name()}">${passenger.getGender().name()}</option>
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
                        <c:forEach var="currentClass" items="${classes}">
                            <c:choose>
                                <c:when test="${currentClass.name() eq passenger.getFlightClass().name()}">
                                    <option selected="selected"
                                            value="${passenger.getFlightClass().name()}">${passenger.getFlightClass().name()}</option>
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
                <input type="submit" value="Apply">
            </td>
        </table>
        <jsp:include page="/WEB-INF/JSP/ErrorMessage.jsp"/>
    </form>
</div>
