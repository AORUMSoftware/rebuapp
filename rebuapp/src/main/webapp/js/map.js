let dataUrl = document.getElementById("map-controller").getAttribute("data-url");

// Set initializer function for map on page window
window.initMap = function() {

	let url = window.location.origin + "/rebuapp" + dataUrl
	
	console.log(url)
	
	let jqxhr = $.get(url, function(data) {
		
		// On Sucess data
		console.log(data)
		
		// The location of Uluru
		let uluru = {
			lat : -25.344,
			lng : 131.036
		}
	
		// The map, centered at Uluru
		let map = new google.maps.Map(document.getElementById('map'), {
			zoom : 4,
			center : uluru
		})
	
		// The marker, positioned at Uluru
		let marker = new google.maps.Marker({
			position : uluru,
			map : map
		})
		
	}).fail(function(data) {
		
		// On fail data
		console.error(data)
		
	})
}
