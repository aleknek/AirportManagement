<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type=href="/resources/css/jquery.datetimepicker.css">

<script type="text/javascript">
    $(function () {
        $('#datetimepicker1').datetimepicker({
            language: 'pt-BR'
        });
    });
</script>

<div>

    <input type="button" class="buttonDisplayDepartures" value="Departures" onClick='displayDepartures()'>
    <input type="button" class="buttonDisplayArrivals" value="Arrivals" onClick='displayArrivals()'>

    <p>
        <c:if test="${CURRENT_ROLE eq 1}">
            <input type="button" class="buttonView" value="View the list of passengers"
                   onClick='viewPassengers()'>
            <input type="button" class="buttonCreate" value="Add a new flight"
                   onClick='location.href="${CURRENT_MAPPING}/flightCreating"'>
            <input type="button" class="buttonModifying" value="Modify the data of the flight"
                   onClick='flightModification()'>
            <input type="button" class="buttonDelete" value="Remove flight" onClick='removeFlight()'>
        </c:if>
    </p>

    <table class="tableFlightAndPassenger">
        <c:if test="${typeOfFlight eq 'departure'}">
            <caption align="left">Flights list departures</caption>
        </c:if>
        <c:if test="${typeOfFlight eq 'arrival'}">
            <caption align="left">Flights list arrivals</caption>
        </c:if>
        <tr>
            <c:if test="${CURRENT_ROLE eq 1}">
                <th class="thCheckBox"></th>
            </c:if>
            <th class="thColumn">Flight</th>
            <th class="thColumn">Destination</th>
            <th class="thColumn">Date</th>
            <th class="thColumn">Terminal</th>
            <c:if test="${typeOfFlight eq 'departure'}">
                <th class="thColumn">Gate</th>
            </c:if>
            <th class="thColumn">Status</th>
        </tr>
        <c:forEach items="${flights}" var="flight">
            <tr>
                <c:if test="${CURRENT_ROLE eq 1}">
                    <td class="tdColumn">
                        <input type="checkbox" id="${flight.getId()}">
                    </td>
                </c:if>
                <td class="tdColumn">
                    <option value="${flight.getNumber()}">${flight.getNumber()}</option>
                </td>
                <td class="tdColumn">
                    <option value="${flight.city.getName()}">${flight.city.getName()}</option>
                </td>
                <td class="tdColumn">
                    <option name="date" id="datetimepicker"
                            value="${flight.dateOfDeparture}">${flight.dateOfDeparture}</option>
                </td>
                <td class="tdColumn">
                    <option value="${flight.getTerminal()}">${flight.getTerminal()}</option>
                </td>
                <c:if test="${typeOfFlight eq 'departure'}">
                    <td class="tdColumn">
                        <option value="${flight.getGate()}">${flight.getGate()}</option>
                    </td>
                </c:if>
                <td class="tdColumn">
                    <option value="${flight.statusOfFlight.getName()}">${flight.statusOfFlight.getName()}</option>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>