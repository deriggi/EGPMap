
function clearContainer() {
    $('.chartrow').remove();
}

//function makeRow(panelTitle, panelDescription, rowid){
//	$('nav').after("<div class='row'  style='margin-top:10px' ><div class='large-4 panel columns '><h5>" + panelTitle + "</h5><p id=" + rowid + ">" + panelDescription + "</p></div></div>");
//
//}

function makePanel(panelTitle, panelDescription, rowid) {
    var parta = "<div class='large-6 panel columns' style='background-color:#fff;  border-width:0;' >";
    if(panelTitle && panelTitle.length > 1){
        parta += "<h6 style='height:60px'>" + panelTitle + "</h6>"; 
    }
    
    parta += "<p style='' id=" + rowid + ">" + panelDescription + "</p></div>";
    
    return parta;
    
}

function makeBigPanel(panelTitle, panelDescription, rowid) {
    return "<div class='large-12 panel columns' style='background-color:#fff; border-width:0;' ><h5 style=''>" + panelTitle + "</h5><div style='height:400px' id=" + rowid + ">" + +"</div></div>";
}

function makeSmallPanel(panelTitle, panelDescription, rowid) {
    return "<div class='large-5 panel columns' style='background-color:#fff; border-width:0;'><div style='' id=" + rowid + ">" + +"</div></div>";
}

function makeRowElement() {
    return "<div class='row chartrow' style='margin-top:10px; border-bottom-style:dotted; border-bottom-width:0.7px; border-bottom-color:#595959; ' ></div>";
}

function makeChartContainer(id) {
    return "<div id=\"" + id + "\" style=\" max-width: 800px; height: 220px; \"></div>";
}

function getIndicatorHeader() {
    return "<div class=\"row chartrow\" style='margin-top:10px;' ><div class= \"large-12 panel columns\" style=\"background-color:#fff; border-width:0\" ><h3>Jordan FRP II FAF/PPR Indicators</h3></div></div>";
}
function getTradeHeader() {
    return "<div class=\"row chartrow\" style='margin-top:10px;' ><div class= \"large-12 panel columns\" style=\"background-color:#fff; border-width:0\" ><h3>Jordan Trade </h3></div></div>";
}
function getTrainingHeader() {
    return "<div class=\"row chartrow\" style='margin-top:10px;' ><div class= \"large-12 panel columns\" style=\"background-color:#fff; border-width:0\" ><h3>Jordan FRP II Training Data</h3></div></div>";
}

function getActuals(indicator) {
    var actualSeries = [];
    pushIfThere(actualSeries, indicator.py1actual);
    pushIfThere(actualSeries, indicator.py2actual);
    pushIfThere(actualSeries, indicator.py3actual);
    pushIfThere(actualSeries, indicator.py4actual);
    pushIfThere(actualSeries, indicator.py5actual);

    return actualSeries;
}

function getTargets(indicator) {
    var targetSeries = [];
    pushIfThere(targetSeries, indicator.py1target);
    pushIfThere(targetSeries, indicator.py2target);
    pushIfThere(targetSeries, indicator.py3target);
    pushIfThere(targetSeries, indicator.py4target);
    pushIfThere(targetSeries, indicator.py5target);
    return targetSeries;
}



function cleanString(dirty) {
    return dirty.replace(/[^0-9\\.]/g, '');
}

function pushIfThere(series, item) {
    var cleaned = cleanString(item);

    if (cleaned.length > 0) {
        var parsed = parseInt(cleaned);
        series.push(parsed);
    } else {
        series.push(null);
    }
    return series;
}

function make3dPie(genderData, id, holeSize, titleText) {
    $('#' + id).highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        credits: {enabled: false},
        title: {
            text: titleText
        },
        subtitle: {
            text: ''
        },
        plotOptions: {
            pie: {
                innerSize: holeSize,
                depth: 45
            }
        },
        series: [{
                name: 'Attendance',
                data: genderData
//            data: [
//                ['Bananas', 8],
//                ['Kiwi', 3],
//                ['Mixed nuts', 1],
//                ['Oranges', 6],
//                ['Apples', 8],
//                ['Pears', 4], 
//                ['Clementines', 4],
//                ['Reddish (bag)', 1],
//                ['Grapes (bunch)', 1]
//            ]
            }]
    });

}


function populateChart(id, actuals, targets, categories, seriesnames, title) {
    var series;
    series = [{
            name: seriesnames[0],
            data: targets
        },
        {
            name: seriesnames[1],
            data: actuals
        }

    ];

    if (categories === null) {
        categories = ['PY1', 'PY2', 'PY3', 'PY4', 'PY5'];
    }

    $('#' + id).highcharts({
        title: {
            text: title,
            x: -20 //center
        },
        credits: {enabled: false},
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: categories
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
        tooltip: {
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: series
    });
}

function makeGenderByYearChart(id, categories, data) {
    $('#' + id).highcharts({
        chart: {
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                viewDistance: 25,
                depth: 40
            },
            marginTop: 80,
            marginRight: 40
        },
        title: {
            text: 'Total fruit consumption, grouped by gender'
        },
        xAxis: {
            categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
        },
        yAxis: {
            allowDecimals: false,
            min: 0,
            title: {
                text: 'Number of fruits'
            }
        },
        tooltip: {
            headerFormat: '<b>{point.key}</b><br>',
            pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                depth: 40
            }
        },
        series: [{
                name: 'John',
                data: [5, 3, 4, 7, 2],
                stack: 'male'
            }, {
                name: 'Joe',
                data: [3, 4, 4, 2, 5],
                stack: 'male'
            }, {
                name: 'Jane',
                data: [2, 5, 6, 2, 1],
                stack: 'female'
            }, {
                name: 'Janet',
                data: [3, 0, 4, 4, 3],
                stack: 'female'
            }]
    });
}

function populateTrainingWorkshopChart(id, chartObj) {
    $('#' + id).highcharts({
        title: {
            text: 'Number of Events Per Quarter',
            x: -20 //center
        },
        credits: {enabled: false},
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: chartObj.cat
        },
        yAxis: {
            title: {
                text: 'Number of Events'
            },
            plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
        },
        tooltip: {
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
                name: 'training',
                data: chartObj.t
            },
            {
                name: 'workshops',
                data: chartObj.w
            }

        ]
    });
}



var EGPHolder = (function loadegp() {
    var egp = null;
    var training = null;
    var findicators = [];
    var dindicators = [];
    var showWb = true;

    var trainings = [];
    var workshops = [];
    var trainingQuarterCount = {};
    var trainingYearMaleCount = {};
    var trainingYearFemaleCount = {};

    var workshopQuarterCount = {};
    var maleTotal = 0;
    var femaleTotal = 0;
    var maleTrainingTotal = 0;
    var femaleTrainingTotal = 0;
    var maleWorkshopTotal = 0;
    var femaleWorkshopTotal = 0;

    return {
        setShowWb: function(showit) {
            showWb = showit;
        },
        getShowWb: function() {
            return showWb;
        },
        getMaleTotal: function() {
            return maleTotal;
        },
        getFemaleTotal: function() {
            return femaleTotal;
        },
        addToMaleTrainingTotal: function(inc) {
            maleTrainingTotal += inc;
        },
        getMaleTrainingTotal: function() {
            return maleTrainingTotal;
        },
        getFemaleTrainingTotal: function() {
            return femaleTrainingTotal;
        },
        addToFemaleTrainingTotal: function(inc) {
            femaleTrainingTotal += inc;
        },
        addToMaleWorkshopTotal: function(inc) {
            maleWorkshopTotal += inc;
        },
        addToFemaleWorkshopTotal: function(inc) {
            femaleWorkshopTotal += inc;
        },
        getMaleWorkshopTotal: function() {
            return maleWorkshopTotal;
        },
        getFemaleWorkshopTotal: function() {
            return femaleWorkshopTotal;
        },
        hasTraining: function() {
            if (training === null) {
                return false;
            }
            return true;
        },
        getFindicators: function() {
            return findicators;
        },
        getDindicators: function() {
            return dindicators;
        },
        showWb: function() {
            $("footer").before($(getTradeHeader()));
            
            var rowElement = $(makeRowElement());
            var narpanel = $(makePanel('', "Jordan trade sector is growing rapidly, emerging as a stable alternative in a region of instability in recent years. This World Bank <a href =\"http://www.doingbusiness.org/data/exploreeconomies/jordan/\"> \"Doing Business Report\"</a> numbers reflect the time to import and export as it is a key measure in international trade. <br/><br/>Time delays affect international trade negatively in particular perishable goods. Hard- and soft-infrastructure play an important role however the regional instability may also impact the score. Jordan's time to import and export is much better than that of other countries in the region and is closer to that of OECD countries", 'wb2'));
            rowElement.append(narpanel);
            var panel = $(makePanel('', '', 'wb1'));
            rowElement.append(panel);
            panel.append($(makeChartContainer('wb1')));

            $("footer").before(rowElement);
            populateChart('wb1', [17, 14, 13, 13], [19, 18, 15, 15], ['2009', '2010', '2011', '2012', '2013'], ['import time', 'export time'], 'World Bank: Number of Days to Export and Import');
        },
        showIndicators: function(indicators) {
            var rowElement = 0;
            $("footer").before($(getIndicatorHeader()));

            for (var indexa in indicators) {

                var tempIndicator = indicators[indexa];
                var actuals = getActuals(tempIndicator);
                var targets = getTargets(tempIndicator);


//                            makeRowElement(tempIndicator.indicator, '', 'r'+indexa);
                if (indexa % 2 === 0) {

                    rowElement = $(makeRowElement());
                }
                var panel = $(makePanel(tempIndicator.indicator, '', 'r' + indexa));

                rowElement.append(panel);

                panel.append($(makeChartContainer('v' + indexa)));
                $("footer").before(rowElement);
                populateChart('v' + indexa, actuals, targets, null, ['targets', 'actuals']);
            }

        },
        setupIndicators: function() {
            if (egp !== null) {
                for (var index in egp) {
                    var tempIndicator = egp[index];
                    if (tempIndicator.indicator.indexOf("State F") !== -1) {
                        findicators.push(tempIndicator);

                    }
                    else if (tempIndicator.indicator.indexOf("D6") === 0 || tempIndicator.indicator.indexOf("D7") === 0 || tempIndicator.indicator.indexOf("D5") === 0) {
                        dindicators.push(tempIndicator);
                    }
                }
            }
        },
        setupTrainingArrays: function() {
            if (training !== null) {
                for (var index in training) {
                    var tempTraining = training[index];
                    var mt = parseInt(parseInt(tempTraining.n_ofmalepax));
                    var ft = parseInt(parseInt(tempTraining.n_offemalepax));

                    maleTotal += mt;
                    femaleTotal += ft;
                    console.log('inc male total is ' + maleTotal + ' after adding by ' + mt);
                    if (tempTraining.eventtype.indexOf("Training Course") !== -1) {
                        trainings.push(tempTraining);

                        if (!trainingQuarterCount[tempTraining.quarter]) {
                            trainingQuarterCount[tempTraining.quarter] = 0;
                        }

                        trainingQuarterCount[tempTraining.quarter]++;
                        EGPHolder.addToMaleTrainingTotal(mt);
                        EGPHolder.addToFemaleTrainingTotal(ft);
                    }
                    else if (tempTraining.eventtype.indexOf("Workshop") === 0) {
                        workshops.push(tempTraining);
                        if (!workshopQuarterCount[tempTraining.quarter]) {
                            workshopQuarterCount[tempTraining.quarter] = 0;
                        }
                        workshopQuarterCount[tempTraining.quarter]++;
                        EGPHolder.addToMaleWorkshopTotal(mt);
                        EGPHolder.addToFemaleWorkshopTotal(ft);
                    }

                    var year = tempTraining.quarter.substring(0, 4);
                    if (!trainingYearMaleCount[tempTraining.quarter]) {
                        trainingYearMaleCount[tempTraining.quarter] = 0;
                    }
                    if (!trainingYearFemaleCount[tempTraining.quarter]) {
                        trainingYearFemaleCount[tempTraining.quarter] = 0;
                    }

                    // now increment
                    trainingYearFemaleCount[tempTraining.quarter] += ft;
                    trainingYearMaleCount[tempTraining.quarter] += mt;

                }
            }
        },
        showTrainingInfo: function() {
           $("footer").before($(getTrainingHeader()));

            var rowElement = $(makeRowElement());
            var elementId = 'r1';
            var panel = $(makeBigPanel('', '', elementId));
            rowElement.append(panel);
            $('footer').before(rowElement);
            var chartsTraining = EGPHolder.convertToChartData();
            populateTrainingWorkshopChart(elementId, chartsTraining);
        },
        showAllGenderChart: function() {
            var rowElement = $(makeRowElement());
            var elementId = 'r2';
            var panel = $(makeBigPanel('', '', elementId));

            rowElement.append(panel);

            $('footer').before(rowElement);
//            alert(EGPHolder.getMaleTotal());
//            alert(EGPHolder.getFemaleTotal());
            var chartsTraining = [['Male', EGPHolder.getMaleTotal()], ['Female', EGPHolder.getFemaleTotal()]];
            make3dPie(chartsTraining, elementId, 100, 'Attendance by Gender for All Event Types');

        },
        showTrainingGenderCharts: function() {
            var rowElement = $(makeRowElement());
            var panelr3 = $(makeSmallPanel("", '', 'r3'));
            var panelr4 = $(makeSmallPanel("", '', 'r4'));
//           var panelr5 = $(makeSmallPanel("Training By Gender", '', 'r5'));

            rowElement.append(panelr3);
            rowElement.append(panelr4);
//           rowElement.append(panelr5);

            $('footer').before(rowElement);
            var trainingTraining = [['Male', EGPHolder.getMaleTrainingTotal()], ['Female', EGPHolder.getFemaleTrainingTotal()]];
            var workshopTraining = [['Male', EGPHolder.getMaleWorkshopTotal()], ['Female', EGPHolder.getFemaleWorkshopTotal()]];
            make3dPie(trainingTraining, 'r3', 100, 'Training Attendence by Gender');
            make3dPie(workshopTraining, 'r4', 100, 'Workshop Attendence by Gender');
        },
        convertToChartData: function() {

            // get sorted keys, use to build stuffx
            var keys = [];
            for (var key in workshopQuarterCount) {
                keys.push(key);

            }
            keys.sort();

            // create a data array and a category array
            var categories = [];
            var workshopsdata = [];
            var trainingdata = [];

            for (var index in keys) {
                categories.push(keys[index]);
                workshopsdata.push(workshopQuarterCount[keys[index]]);
                trainingdata.push(trainingQuarterCount[keys[index]]);
            }

            return {cat: categories, t: trainingdata, w: workshopsdata};

        },
        convertTo3DChartData: function() {

            // get sorted keys, use to build stuffx
            var keys = [];
            for (var key in trainingYearMaleCount) {
                keys.push(key);

            }
            keys.sort();

            // create a data array and a category array
            var categories = [];
            var data = [];
            var femaleData = [];

            for (var index in keys) {
                categories.push(keys[index]);

                data.push([toString(keys[index]), trainingYearMaleCount[keys[index]]]);

                femaleData.push([toString(keys[index]), trainingYearFemaleCount[keys[index]]]);
            }

            return {md: maleData, w: workshopsdata};



        },
        setupEgp: function() {
            // $.getJSON('http://107.23.36.208:8090/TAMISLogin/v1/project/frp/mande', function(data){
            $.getJSON('/TAMISLogin/v1/project/frp/mande', function(data) {
                egp = data;
                $('#datatable').show();

                EGPHolder.setupIndicators();
                EGPHolder.showIndicators(EGPHolder.getFindicators());
            });
        },
        setupFrpTraining: function() {
            $('#chartsitem > a').text('loading..')
            $.getJSON('/TAMISLogin/v1/project/frp/training', function(data) {
                training = data;
                $('#chartsitem > a').text('Charts');
                EGPHolder.setupTrainingArrays();

            });
        }

    };

})();



$(document).ready(function() {
    $(document).foundation();
    EGPHolder.setupEgp();
    EGPHolder.setupFrpTraining();

    $('#findicators').click(function() {
        clearContainer();
        EGPHolder.setShowWb(false);
        $('#datatable').show();

        EGPHolder.showIndicators(EGPHolder.getFindicators());


    });

    $('#dindicators').click(function() {
        clearContainer();
        EGPHolder.setShowWb(false);

        EGPHolder.showIndicators(EGPHolder.getDindicators());
    });

    $('#chartsitem').click(function() {
        
        if (EGPHolder.hasTraining()) {
            $('#datatable').hide();
            clearContainer();
            EGPHolder.showTrainingInfo();
            EGPHolder.showAllGenderChart();
            EGPHolder.showTrainingGenderCharts();
        }
    });

    $('#tradeitem').click(function() {
        clearContainer();
        $('#datatable').hide();
        EGPHolder.showWb();
    });



    // EGPHolder.showIndicators(EGPHolder.getFindicators());
    // EGPHolder.setupIndicators();

});


