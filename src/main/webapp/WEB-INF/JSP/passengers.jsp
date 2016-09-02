<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: "yy-mm-dd"
        });
    });
</script>

<div>

    <td><b>${performance}</b></td>

    <p>
        <input type="button" class="buttonCreate" value="Add a new passenger" onClick='addNewPassenger(${idFlight})'>
        &nbsp;&nbsp;&nbsp;
        <input type="submit" class="buttonModifying" value="Modify passenger data"
               onClick='passengerModification(${idFlight})'>
        &nbsp;&nbsp;&nbsp;
        <input type="button" class="buttonDelete" value="Remove the passenger" onClick='removePassenger(${idFlight})'>
    </p>

    <table class="tableFlightAndPassenger">
        <caption align="left">Passengers list</caption>
        <tr>
            <th class="thCheckBox"></th>
            <th class="thColumn">First name</th>
            <th class="thColumn">Last name</th>
            <th class="thColumn">Nationality</th>
            <th class="thColumn">Passport</th>
            <th class="thColumn">Date of birthday</th>
            <th class="thColumn">Sex</th>
            <th class="thColumn">Class</th>
        </tr>
        <c:forEach items="${passengers}" var="passenger">
            <tr>
                <td class="tdColumn"><input type="checkbox" id="${passenger.getId()}"></td>
                <td class="tdColumn">
                    <option value="${passenger.firstName}">${passenger.getFirstName()}</option>
                </td>
                <td class="tdColumn">
                    <option value="${passenger.lastName}">${passenger.getLastName()}</option>
                </td>
                <td class="tdColumn">
                    <option value="${passenger.nationality}">${passenger.getNationality()}</option>
                </td>
                <td class="tdColumn">
                    <option value="${passenger.passport}">${passenger.getPassport()}</option>
                </td>
                <td class="tdColumn">
                    <option name="date" id="datepicker"
                            value="${passenger.dateOfBirthday}">${passenger.getDateOfBirthday()}</option>
                </td>
                <td class="tdColumn">
                    <option value="${passenger.gender}">${passenger.getGender()}</option>
                </td>
                <td class="tdColumn">
                    <option value="${passenger.flightClass}">${passenger.getFlightClass()}</option>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>