$.ajax({
	url: "piechartdata",
	success: function(result){
		
		var series =[];
		var data = [];
		for(var i = 0; i < result.length; i++){
			console.log("Result " + result);
			var object = {};
			object.name = result[i].stock.toUpperCase();
			object.y = result[i].investment;
			data.push(object);
		}
		var seriesObject = {
			colorByPoint: true,
			name: 'Stocks',
			data: data
		}
		series.push(seriesObject)
		drawPieChart(series);
	}
});

function drawPieChart(series){
	Highcharts.chart('container', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Fintech market shares in November, 2021'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    accessibility: {
        point: {
            valueSuffix: '%'
        }
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
            }
        }
    },
    series: series
});
	
	
}
