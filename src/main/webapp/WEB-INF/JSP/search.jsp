<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <form action="${CURRENT_MAPPING}/search" method="get">
        <table class="tableSearch">
            <tr>
                <td>Search by:</td>
                <td>
                    <select id="currentTypeSearch" name="selectedTypeSearch"
                            onchange="viewTypeSearch(selectedTypeSearch)">
                        <c:forEach var="currentTypeSearch" items="${typesSearch}">
                            <c:choose>
                                <c:when test="${currentTypeSearch eq chooserTypeSearch}">
                                    <option selected="selected"
                                            value="${chooserTypeSearch}">${chooserTypeSearch}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${currentTypeSearch}">${currentTypeSearch}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
    </form>

    <form action="${CURRENT_MAPPING}/search" method="post">

        <table class="tableSearch">

            <c:if test="${chooserTypeSearch eq 'flight number'}">
                <td class="column">Flight number</td>
                <td><input type="text" name="flightNumber"></td>
            </c:if>

            <c:if test="${chooserTypeSearch eq 'price'}">
                <td class="column">Price</td>
                <td><input type="text" name="price"></td>
            </c:if>

            <c:if test="${chooserTypeSearch eq 'first and second name'}">
                <td class="column">First name</td>
                <td><input type="text" name="firstName"></td>

                <td class="column">Second name</td>
                <td><input type="text" name="secondName"></td>
            </c:if>

            <c:if test="${chooserTypeSearch eq 'passport'}">
                <td class="column">Passport</td>
                <td><input type="text" name="passport"></td>
            </c:if>

            <c:if test="${chooserTypeSearch eq 'arrival port'}">
                <td class="column">Arrival port</td>
                <td>
                    <select id="currentCityArrival" name="arrivalPort">
                        <option value=""></option>
                        <c:forEach var="currentCityArrival" items="${cities}">
                            <option value="${currentCityArrival.getName()}">${currentCityArrival.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </c:if>

            <c:if test="${chooserTypeSearch eq 'departure port'}">
                <td class="column">Departure port</td>
                <td>
                    <select id="currentCityDeparture" name="departurePort">
                        <option value=""></option>
                        <c:forEach var="currentCityDeparture" items="${cities}">
                            <option value="${currentCityDeparture.getName()}">${currentCityDeparture.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </c:if>

            <c:if test="${chooserTypeSearch eq 'price' or chooserTypeSearch eq 'first and second name'
            or chooserTypeSearch eq 'passport'}">
                <td class="column">Type of flight</td>
                <td><select id="currentTypeOfFlight" name="currentTypeOfFlight">
                    <c:forEach var="currentTypeOfFlight" items="${typesOfFlight}">
                        <option value="${currentTypeOfFlight.name()}">${currentTypeOfFlight.name()}</option>
                    </c:forEach>
                </select>
                </td>
            </c:if>

            <td></td>
            <td><input type="submit" value="Search" id="button"></td>
        </table>
        <jsp:include page="/WEB-INF/JSP/ErrorMessage.jsp"/>
    </form>
</div>

