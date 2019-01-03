var removeNonNumericCharacters = function (elementId) {
    var returnString = "";
    var text = document.getElementById(elementId).value;
    var regex = /[0-9۰-۹]/;
    var anArray = text.split('');
    for (var i = 0; i < anArray.length; i++) {
        if (!regex.test(anArray[i])) {
            anArray[i] = '';
        }
    }
    for (var i = 0; i < anArray.length; i++) {
        returnString += anArray[i];
    }
    document.getElementById(elementId).value = returnString;
};

var removeNumericCharacters = function (elementId) {
    var returnString = "";
    var text = document.getElementById(elementId).value;
    var regex = /[0-9۰-۹]/;
    var anArray = text.split('');
    for (var i = 0; i < anArray.length; i++) {
        if (regex.test(anArray[i])) {
            anArray[i] = '';
        }
    }
    for (var i = 0; i < anArray.length; i++) {
        returnString += anArray[i];
    }
    document.getElementById(elementId).value = returnString;
};


var convertFromUTCz2JalaliInTehranTimeZone = function(dateTime) {
    return moment(dateTime).tz('Asia/Tehran').format('jYYYY-jM-jD HH:mm');
};


var covertFromJalaliInTehranTimeZone2UTCz = function(dateTime) {
    return moment(dateTime, 'jYYYY-jM-jD HH:mm')
        .tz('Asia/Tehran')
        .utc()
        .format('YYYY-MM-DDTHH:mm:00.000Z');
};