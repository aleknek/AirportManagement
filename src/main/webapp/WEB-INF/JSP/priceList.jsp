<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <form action="${CURRENT_MAPPING}/priceList" method="get">
        <table class="tableSearch">
            <tr>
                <td>Choose flight:</td>
                <td>
                    <select id="currentFlight" name="chooser">
                        <c:forEach var="currentFlight" items="${flights}">
                            <c:choose>
                                <c:when test="${currentFlight.id eq flight.id}">
                                    <option selected="selected" value="${currentFlight.id}">${flight.getNumber()} ${flight.city.getName()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${currentFlight.id }">${currentFlight.getNumber()} ${currentFlight.city.getName()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach></select></td>
                <td><input type="submit" value="Choose" id="button"></td>
            </tr>
        </table>
    </form>

    <div>
        <table class="tablePrice">
            <body>
            <tr>
                <th class="thPrice">Flight class</th>
                <th class="thPrice">Price, $</th>
            </tr>
            <c:forEach var="priceLists" items="${priceLists}">
                <c:if test="${priceLists ne null}">
                    <tr>
                        <td class="tdPrice">${priceLists.flightClass}</td>
                        <td class="tdPrice">${priceLists.price}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </body>
        </table>
    </div>
</div>

