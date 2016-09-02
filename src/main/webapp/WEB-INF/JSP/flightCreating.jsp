<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<script src="/resources/js/jquery.datetimepicker.full.min.js"></script>

<script>
    $(function () {
        $("#datetimepicker").datetimepicker({
            dateFormat: "yyyy/MM/dd HH:mm"
        });
    });
</script>

<form method="post" action="${CURRENT_MAPPING}/flightCreating">
    <table class="tableCreatingAndModifying">
        <tr>
            <td class="column">Flight</td>
            <td>
                <input type="text" name="flight" value="${flightNumber}">
            </td>
        </tr>
        <tr>
            <td class="column">City</td>
            <td>
                <select id="currentCity" name="selectedCity">
                    <option value=""></option>
                    <c:forEach var="currentCity" items="${cities}">
                        <c:choose>
                            <c:when test="${currentCity.getName() eq city}">
                                <option selected="selected"
                                        value="${city}">${city}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${currentCity.getName()}">${currentCity.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="column">Date</td>
            <td>
                <input id="datetimepicker" name = "date" type="text" value="${date}">
            </td>
        </tr>
        <tr>
            <td class="column">Terminal</td>
            <td>
                <input type="text" name="terminal" value="${terminal}">
            </td>
        </tr>
        <tr>
            <td class="column">Gate</td>
            <td>
                <input type="text" name="gate" value="${gate}">
            </td>
        </tr>
        <tr>
            <td class="column">Status</td>
            <td>
                <select id="currentStatus" name="selectedStatus">
                    <option value=""></option>
                    <c:forEach var="currentStatus" items="${statuses}">
                        <c:choose>
                            <c:when test="${currentStatus.getName() eq status}">
                                <option selected="selected"
                                        value="${status}">${status}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${currentStatus.getName()}">${currentStatus.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="column">Flight type</td>
            <td>
                <select id="currentType" name="selectedType">
                    <option value=""></option>
                    <c:forEach var="currentType" items="${typesOfFlights}">
                        <option value="${currentType.name()}">${currentType.name()}</option>
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
