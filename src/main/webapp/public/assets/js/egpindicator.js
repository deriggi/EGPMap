/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function loadData() {
    // get inicator data from server
    fetchInidicatorData();

   
}

function displayData(indicators){
     for (var i in indicators) {
        var id = 'b-' + i;
        var block = makeBlock(id);
        $('#indicatorcontainer').append(block);
        var chartData = extractData(indicators[i]);
        makeChart(id, indicators[i].indicator, chartData);

    }
    // iterate through and make chart block
}

// TODO: extract the indicator data 
function extractData(indicator){
    var data = [];
    
    data.push(indicator.fy1Q1);
    data.push(indicator.fy1q2);
    data.push(indicator.fy1q3);
    data.push(indicator.fy1q4);
    data.push(indicator.fy2q1);
    data.push(indicator.fy2q2);
    data.push(indicator.fy2q3);
    data.push(indicator.fy2q4);
    
    return data;
}

function fetchInidicatorData() {
    var indicators = [];
    $.getJSON("/TAMISLogin/v1/project/egp/indicators", function(data) {
        for(var d in data){
            indicators.push(data[d]);
        }
        displayData(indicators);
    });
}

function makeBlock(id) {
    return "<div id='" + id + "' style='min-width: 610px; height: 320px; margin: 0 auto; padding-bottom:20px'></div>";

}

function makeChart(id, title, data) {
    $('#'+id).highcharts({
        credits:{enabled:false},
        title: {
            text: title,
            x: -20, //center
            style:{fontSize:10}
        },
        subtitle: {
            text: 'subtitle',
            x: -20
        },
        xAxis: {
            categories: ['fy1q1', 'fy1q2', 'fy1q3', 'fy1q4', 'fy2q1', 'fy2q2', 'fy2q3', 'fy2q4']
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
        },
//        tooltip: {
//            valueSuffix: '°C'
//        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [
//            {
//                name: 'Fy1 target',
//                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
//            }, {
//                name: 'Fy2 target',
//                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
//            }, 
            {
                name: 'Actuals',
                data: data
            }
//            , {
//                name: 'Baseline',
//                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
//            }
        ]
    });
}

$(document).ready(function() {
    loadData();
    
});