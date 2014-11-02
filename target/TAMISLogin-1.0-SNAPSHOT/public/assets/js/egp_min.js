
function clearContainer(){
	$('#dynamiccontainer').empty();
}

function makeRow(panelTitle, panelDescription, rowid){
	//	$("<div class='row'  style='margin-top:10px' ><div class='large-6 columns'><div class='panel' id='"+rowid+"'><h5>" + panelTitle + "</h5><p >" + panelDescription + "</p></div></div><div class='large-6 columns'></div></div>").appendTo('#dynamiccontainer');
	var p = document.createElement("p");
	var text = document.createTextNode("hello there");
	p.appendChild(text)
	document.body.appendChild(p)
        
	// $("<p> hi all </p>").appendTo('#dynamiccontainer');


}

function makeChartContainer(id){
	return "<div id=\"" + id + "\" style=\"min-width: 310px; max-width: 800px; height: 400px; \"></div>";
}

function getActuals(indicator){
	var actualSeries = []
	pushIfThere(actualSeries, indicator.py1actual)
	pushIfThere(actualSeries, indicator.py2actual)
	pushIfThere(actualSeries, indicator.py3actual)
	pushIfThere(actualSeries, indicator.py4actual)

	return actualSeries;
}

function getTargets(indicator){
	var targetSeries = []
	pushIfThere(targetSeries, indicator.py1target)
	pushIfThere(targetSeries, indicator.py2target)
	pushIfThere(targetSeries, indicator.py3target)
	pushIfThere(targetSeries, indicator.py4target)
	return targetSeries;
}



function cleanString(dirty){
	return dirty.replace(/[^0-9\\.]/g, '' )
}

function pushIfThere(series, item){
	
	var cleaned = cleanString(item)
	
	if( cleaned.length > 0 ){
		series.push(parseInt(item))
	}else{
		series.push(null)
		
	}
	return series
}

function populateChart(id, actuals, targets) {
	$('#' + id).highcharts({
		title: {
			text: '',
                x: -20 //center
            },
            credits:{enabled: false},
            subtitle: {
            	text: '',
            	x: -20
            },
            xAxis: {
            	categories: ['PY1', 'PY2', 'PY3', 'PY4']
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
            series: [{
            	name: 'actual',
            	data: actuals
            },
            {
            	name: 'target',
            	data: targets
            } 

            ]
        });
}

var EGPHolder = (function loadegp(){
	var egp = null;
	var findicators = [];
	var dindicators = [];
	return {
		
		getFindicators: function(){
                        alert('returning ' + findicators.length)
			return findicators;
		},

		getDindicators: function(){
			return dindicators;
		},
		showIndicators: function(indicators){
                        var callCount = 0;
                        alert( ' in show indicators');
			for (var indexa in indicators){
                                alert(indexa)
				var tempIndicator = indicators[indexa]
				var actuals = getActuals(tempIndicator)
				var targets = getTargets(tempIndicator)
				

                                alert('making a row')
				makeRow(tempIndicator.indicator, '', 'r'+indexa);
                                callCount++;
//				
//				$( makeChartContainer('v' + indexa)).appendTo('#r'+indexa) 
//				 populateChart('v' + indexa, actuals, targets )

			}
                        alert('called makerow this many times: ' + callCount)
		},

		setupIndicators :function(){
                        alert('in setup indicators');
			if (egp !== null){
                                alert('egp is not null, is size ' + egp.length);
				for (var index in egp){
                                        
					var tempIndicator = egp[index];
                                        
					if (tempIndicator.indicator.indexOf("State F") !== -1){
						findicators.push(tempIndicator);
						
					}
					else if (tempIndicator.indicator.indexOf("D6") === 0 || tempIndicator.indicator.indexOf("D7") === 0 ){
						dindicators.push(tempIndicator);
					}
                                        
				}
                                alert('processed ' + findicators.length);
				
                                
			}
                        else{
                            alert("egp data is null");
                        }
		},

		setEgpData: function(data){
			egp = data;
		},
                        
		setupEgp : function(){
			// $.getJSON('http://107.23.36.208:8090/TAMISLogin/v1/project/frp/mande', function(data){
			$.getJSON('/TAMISLogin/v1/project/frp/mande', function(data){
				EGPHolder.setEgpData(data);
				alert('successfully received data of size ' + egp.length);
				EGPHolder.setupIndicators();
                                alert('setup called');
				EGPHolder.showIndicators(EGPHolder.getFindicators());
                                alert('done calling show');
			});
		}

		};

	})();



	$(document).ready(function(){
		
		EGPHolder.setupEgp();

		$('#findicators').click(function(){
			clearContainer();
			EGPHolder.showIndicators(EGPHolder.getFindicators());


		});

		$('#dindicators').click(function(){
			clearContainer();
			EGPHolder.showIndicators(EGPHolder.getDindicators());
		});


		// EGPHolder.showIndicators(EGPHolder.getFindicators());
		// EGPHolder.setupIndicators();
		
	});


