function displayArrivals() {
    var form = '<form id="flightsForm" action="'
        + 'http://'
        + document.location.host
        + '/flights" method="post"><input type="hidden" name="typeOfFlight" /></form>';
    $("body").append(form);
    $('#flightsForm input').val('arrival');
    $('#flightsForm').submit();
}

function displayDepartures() {
    var form = '<form id="flightsForm" action="'
        + 'http://'
        + document.location.host
        + '/flights" method="post"><input type="hidden" name="typeOfFlight" /></form>';
    $("body").append(form);
    $('#flightsForm input').val('departure');
    $('#flightsForm').submit();
}

function flightModification() {
    var item = $("input[type=checkbox]:checked");
    if (item.length == 0) {
        alert("Select a flight");
        return;
    }
    if (item.length > 1) {
        alert("You can choose only one flight");
        return;
    }
    var id = $(item).attr("id");
    var form = '<form id="flightModificationForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/flightModifying" method="get"><input type="hidden" name="id" /></form>';
    $("body").append(form);
    $('#flightModificationForm input').val(id);
    $('#flightModificationForm').submit();
}

function passengerModification(idFlight) {
    var item = $("input[type=checkbox]:checked");
    if (item.length == 0) {
        alert("Select the passenger");
        return;
    }
    if (item.length > 1) {
        alert("You can choose only one passenger");
        return;
    }
    var idFlightAndIdPassenger = idFlight+",";
    idFlightAndIdPassenger = idFlightAndIdPassenger + $(item).attr("id");
    var form = '<form id="passengerModificationForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/passengerModifying" method="get"><input type="hidden" name="idFlightAndIdPassenger" /></form>';
    $("body").append(form);
    $('#passengerModificationForm input').val(idFlightAndIdPassenger);
    $('#passengerModificationForm').submit();
}


function removeFlight() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Select a flight");
        return;
    }
    var idFlights = "";
    for (var i = 0; i < items.length; i++) {
        idFlights += $(items[i]).attr("id");
        if (i < items.length - 1) {
            idFlights += ",";
        }
    }
    var form = '<form id="removeFlightForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/flights" method="post"><input type="hidden" name="idFlights" /></form>';
    $("body").append(form);
    $('#removeFlightForm input').val(idFlights);
    $('#removeFlightForm').submit();
}

function removePassenger(idFlight) {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Select a passenger");
        return;
    }
    var idFlightAndIdPassengers = idFlight+",";

    for (var i = 0; i < items.length; i++) {
        idFlightAndIdPassengers += $(items[i]).attr("id");
        if (i < items.length - 1) {
            idFlightAndIdPassengers += ",";
        }
    }
    var form = '<form id="removePassengerForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/passengers" method="post"><input type="hidden" name="idFlightAndIdPassengers" /></form>';
    $("body").append(form);
    $('#removePassengerForm input ').val(idFlightAndIdPassengers);
    $('#removePassengerForm').submit();
}


function addNewPassenger(idFlight) {
    var form = '<form id="addNewPassengerForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/passengerCreating" method="get"><input type="hidden" name="idFlight" /></form>';
    $("body").append(form);
    $('#addNewPassengerForm input').val(idFlight);
    $('#addNewPassengerForm').submit();
}


function viewPassengers() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Select a flight");
        return;
    }
    if (items.length > 1) {
        alert("You can choose only one flight");
        return;
    }
    var id = $(items).attr("id");
    var form = '<form id="viewPassengersForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/passengers" method="get"><input type="hidden" name="idFlight" /></form>';
    $("body").append(form);
    $('#viewPassengersForm input').val(id);
    $('#viewPassengersForm').submit();
}

function viewTypeSearch(chooserTypeSearch) {
    var form = '<form id="SearchForm" action="'
        + 'http://'
        + document.location.host
        + '/staff/search" method="get"><input type="hidden" name="chooserTypeSearch" /></form>';
    $("body").append(form);
    $('#SearchForm input').val(chooserTypeSearch.value);
    $('#SearchForm').submit();
}






