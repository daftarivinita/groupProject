$.ajax({
	url: "linechartdata",
	success: function(result){
		
		console.log("Result1: " + result);
		
		var month1 = JSON.parse(result).Month;		
		console.log("Inside month " + month1);
		
		var portfolioBalance = JSON.parse(result).PortfolioBalance;
		
		console.log("Inside portfolio " + portfolioBalance);
		
		drawLineChart(month1, portfolioBalance);
		//console.log("in draw function" + drawLineChart(month, portfolioBalance));
	}
});

/* for line chart */
function drawLineChart(month1, portfolioBalance){
	Highcharts.chart('container', {
	    chart: {
	        type: 'line',
	        width: 500
	    },
	    
	    title: {
	        text: 'Fintech Portfolio 2021'
	    },
	
	    xAxis: {
	        categories: month1
	    },
	    
	    tooltip: {
	        formatter: function() {
		console.log(this);
	          return '<strong>'+this.x+': </strong>'+ this.y;
	        }
	    },
	
	    series: [{
	        data: portfolioBalance
	    }]
	});
}