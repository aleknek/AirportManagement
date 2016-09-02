<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css">
<script src="/resources/js/jquery.datetimepicker.full.min.js"></script>

<script>
    $(function () {
        $("#datetimepicker").datetimepicker({
            dateFormat: "yyyy/MM/dd HH:mm"
        });
    });
</script>

<div>
    <h3>
        <left>To modify, enter new values and press the "Apply" button.</left>
    </h3>

    <form method="post" action="${CURRENT_MAPPING}/flightModifying">

        <td><b>${performance}</b></td>

        <table class="tableCreatingAndModifying">
            <input type="hidden" name="id" value="${flight.getId()}"/>
            <tr>
                <td class="column">Flight</td>
                <td>
                    <input type="text" name="flight" value="${flight.getNumber()}">
                </td>
            </tr>
            <tr>
                <td class="column">City</td>
                <td>
                    <select id="currentCity" name="selectedCity">
                        <c:forEach var="currentCity" items="${cities}">
                            <c:choose>
                                <c:when test="${currentCity.getName() eq flight.getCity().getName()}">
                                    <option selected="selected"
                                            value="${flight.getCity().getName()}">${flight.getCity().getName()}</option>
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
                    <input id="datetimepicker" name="date" type="text" value="${flight.getDateOfDeparture()}">
                </td>
            </tr>
            <tr>
                <td class="column">Terminal</td>
                <td>
                    <input type="text" name="terminal" value="${flight.getTerminal()}">
                </td>
            </tr>
            <tr>
                <td class="column">Gate</td>
                <td>
                    <input type="text" name="gate" value="${flight.getGate()}">
                </td>
            </tr>
            <tr>
                <td class="column">Status</td>
                <td>
                    <select id="currentStatus" name="selectedStatus">
                        <c:forEach var="currentStatus" items="${statuses}">
                            <c:choose>
                                <c:when test="${currentStatus.getName() eq flight.getStatusOfFlight().getName()}">
                                    <option selected="selected"
                                            value="${flight.getStatusOfFlight().getName()}">${flight.getStatusOfFlight().getName()}</option>
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
                <td></td>
                <td><input type="submit" value="Apply"></td>
            </tr>
        </table>
        <jsp:include page="/WEB-INF/JSP/ErrorMessage.jsp"/>
    </form>
</div>
