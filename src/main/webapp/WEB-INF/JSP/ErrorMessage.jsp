<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${ErrorMessage ne null }">
    <table id="ErrorMessage">
        <tr>
            <td class="errorMessage"><c:choose>
                <c:when test="${ErrorMessage eq 'incorrectRole'}">
                    Incorrect role
                </c:when>
                <c:when test="${ErrorMessage eq 'incorrectPassword'}">
                    Incorrect password
                </c:when>
                <c:when test="${ErrorMessage eq 'roleIsEmpty'}">
                    Select role
                </c:when>
                <c:when test="${ErrorMessage eq 'accountNotFound'}">
                    Account not found
                </c:when>
                <c:when test="${ErrorMessage eq 'flightNumberIsEmpty'}">
                    Field "Number" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'cityIsEmpty'}">
                    Field "City" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'dateIsEmpty'}">
                    Field "Date" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'statusIsEmpty'}">
                    Field "Status" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'typeOfFlightIsEmpty'}">
                    Field "Type of flight" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'terminalIsEmpty'}">
                    Field "Terminal" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'gateIsEmpty'}">
                    Field "Gate" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'firstNameIsEmpty'}">
                    Field "First name" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'lastNameIsEmpty'}">
                    Field "Last name" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'nationalityIsEmpty'}">
                    Field "Nationality" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'passportIsEmpty'}">
                    Field "Passport" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'dateOfBirthdayIsEmpty'}">
                    Field "Date of birthday" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'genderIsEmpty'}">
                    Field "Gender" is empty
                </c:when>
                <c:when test="${ErrorMessage eq 'flightClassIsEmpty'}">
                    Field "Class of flight" is empty
                </c:when>
            </c:choose>
            </td>
        </tr>
    </table>
</c:if>









