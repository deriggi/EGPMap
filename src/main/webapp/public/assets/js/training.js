
$(document).foundation();

$(document).ready(function() {
    getTraining();
    // setupChart();
    $('#stacked').click(function(){
        
        var t = ChartDataHolder.getTitles();
        var s = ChartDataHolder.getSeries();

        setupChart(t, s, 'normal')
    })

    $('#percent').click(function(){
        
        var t = ChartDataHolder.getTitles();
        var s = ChartDataHolder.getSeries();

        
        setupChart(t, s, 'percent')
    })

    $('#side-by-side').click(function(){
        
        var t = ChartDataHolder.getTitles();
        var s = ChartDataHolder.getSeries();

        setupChart(t, s, null)
        

    })
});

// var doc = document.documentElement;
// doc.setAttribute('data-useragent', navigator.userAgent);


function getTraining() {
    $.getJSON('/TAMISLogin/v1/project/egp/training', function(data) {
//    $.getJSON('http://107.23.36.208:8090/TAMISLogin/v1/project/egp/training', function(data) {

        var trainingTitles = [];
        var males = [];
        var females = [];
        for (var dindex in data) {
            if(data[dindex].eventTitle !== undefined  && data[dindex].eventTitle.length !== 0  ){
            trainingTitles.push(data[dindex].eventTitle);
            
            males.push(parseInt(data[dindex].male));
            females.push( parseInt(data[dindex].female) ) ;

            }
        }

        var Men = {name:'Male', data:males}
        var Women = {name:'Female', data:females}
        var series = [];
        
        series.push(Women);
        series.push(Men);

        ChartDataHolder.setSeries(series)
        ChartDataHolder.setTitles(trainingTitles)
        
        
        setupChart(trainingTitles, series, 'normal')

    });
}


var ChartDataHolder = (function(){
    var series = [];
    var titles = [];
    var chart = null;
    return {
        setType: function(t){
            
            chart.options.plotOptions.series.stacking = t
            chart.destroy()
            alert(t)
        },

        setChart : function(c){
            chart = c;
        },

        setSeries : function(s){
            series = s
        },

        setTitles : function(t){
            titles = t
        },

        getSeries: function(){
            return series;
        },

        getTitles: function(){
            return titles;
        }
    };

})();


function setupChart(trainingTitles, dataSeries, stackingOption){


    var chart =  new Highcharts.Chart({
            credits:{enabled: false},

            chart: {
                type: 'bar',
                renderTo: 'container'
            },
            title: {
                text: 'Gender Composition at Training Events'
            },
            xAxis: {
                categories: trainingTitles
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Number of Attendees'
                }
            },
            legend: {
                reversed: true
            },

            plotOptions: {
                series: {
                    stacking: stackingOption
                }
            },

            series:dataSeries

        });
    ChartDataHolder.setChart(chart)
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


      