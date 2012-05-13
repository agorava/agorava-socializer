$(function() {
	triggerAll();
});

function triggerAll() {
	triggerTooltips ();
}

function triggerAllData(data) {
	if(data.status == 'success') { 
		triggerTooltips ();
	}
}

function triggerTooltips () {
	$(".tooltip-top").tooltip({placement: 'top'});
	$(".tooltip-bottom").tooltip({placement: 'bottom'});
	$(".tooltip-left").tooltip({placement: 'left'});
	$(".tooltip-right").tooltip({placement: 'right'});
}