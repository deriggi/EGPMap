
$(document).foundation();

$(document).ready(function() {
    setupMap();
    getDashboard();
    getTraining();
});

// var doc = document.documentElement;
// doc.setAttribute('data-useragent', navigator.userAgent);


var map;
function setupMap() {

    map = L.mapbox.map('map', 'johnderiggi.map-pcso5skt').setView([31.917323, 35.177742], 9);

}

function getDashboard() {
//    $.getJSON('/TAMISLogin/v1/project/egp/dashboard', function(data) {
    $.getJSON('http://107.23.36.208:8090/TAMISLogin/v1/project/egp/dashboard', function(data) {

        data = data[0];
        JD.set('contractdates', data.contractdates);
        JD.set('contractnumber', data.contractnumber);
        JD.set('contractvalue', data.contractvalue);
        JD.set('projectnumber', data.projectnumber);
        JD.set('chiefofparty', data.chiefofparty);
        JD.set('mainprojectoffice', data.mainprojectoffice);
        JD.set('hoprojectteamdirector', data.hoprojectteamdirector);
        JD.set('hofinance', data.hofinance);
        JD.set('hoprojectmanager', data.hoprojectmanager);
        JD.set('associate', data.associate);
        JD.set('usaidcor', data.usaidcor);
        JD.set('homeofficetamisbackstop', data.homeofficetamisbackstop);

    });
}

function getTraining() {
//    $.getJSON('/TAMISLogin/v1/project/egp/training', function(data) {
    $.getJSON('http://107.23.36.208:8090/TAMISLogin/v1/project/egp/training', function(data) {

        for (var dindex in data) {
            console.log(data[dindex].latitude);
            if (data[dindex].latitude.length !== 0) {
                var lat = parseFloat(data[dindex].latitude);
                var lon = parseFloat(data[dindex].longitude);
//                var latlng = L.latLng(lat, lon);
                var marker = L.marker([lat, lon]).addTo(map);
                var content = "Topic: <b>" + data[dindex].primarytopics + "</b><br/>";
                content += "Start Date: <b>" + data[dindex].startdate + "</b><br/>";
                content += "Venue: <b>" + data[dindex].venue + "</b><br/>";
                content += "Male: <b>" + data[dindex].male + "</b><br/>";
                content += "Female: <b>" + data[dindex].female + "</b><br/>";
                content += "Days: <b>" + data[dindex].totaldays + "</b><br/>";

                marker.bindPopup(content);
            }
        }


    });
}

// a closure for setting element values
var JD = (function() {

    return{
        get: function(id) {
            return document.getElementById(id);

        },
        set: function(id, val) {
            this.get(id).innerHTML = val;
        }
    };

})();


      