let dataUrl = document.getElementById("map-controller")
		.getAttribute("data-url");

// Set initializer function for map on page window
window.initMap = function() {

	let url = window.location.origin + "/rebuapp" + dataUrl

	let jqxhr = $
			.get(
					url,
					function(data) {

						// The location of Uluru
						let uluru = {
							lat : -21.425722,
							lng : -45.948521
						}

						// The map, centered at Uluru
						let map = new google.maps.Map(document
								.getElementById('map'), {
							zoom : 14,
							center : uluru
						})

						// The marker, positioned at Uluru
						let arryMarkers = JSON.parse(data);

						for (let i = 0; i < arryMarkers.result.length; i++) {

							let o = arryMarkers.result[i];

							let origem = {

								lat : parseFloat(o.latOrigem),
								lng : parseFloat(o.lngOrigem)
							}

							let destino = {

								lat : parseFloat(o.latDestino),
								lng : parseFloat(o.lngDestino)
							}

							let marker = new google.maps.Marker({
								position : origem,
								map : map,
								title: "Ponto de Origem"
							})

							let marker1 = new google.maps.Marker({
								position : destino,
								map : map,
								title: "Ponto de Destino"
							})

							marker
									.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png')
							marker1
									.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png')
									console.log(data)
									
							let contentString = "<span style='font-weight: bold; font-size: 120%;'>Ponto de Origem</span><br /><span style='font-weight: bold;'>Data</span>: " + o.data + "<br /><span style='font-weight: bold;'>Descrição</span>: " + o.descricaoEncomenda + "<br /><span style='font-weight: bold;'>Status</span>: " + o.status;
							let contentString1 = "<span style='font-weight: bold; font-size: 120%;'>Ponto de Destino</span><br /><span style='font-weight: bold;'>Data</span>: " + o.data + "<br /><span style='font-weight: bold;'>Descrição</span>: " + o.descricaoEncomenda + "<br /><span style='font-weight: bold;'>Status</span>: " + o.status;

							let infowindow = new google.maps.InfoWindow({
								content : contentString
							});

							let infowindow1 = new google.maps.InfoWindow({
								content : contentString1
							});

							marker.addListener('click', function() {
								infowindow.open(map, marker);
							});

							marker1.addListener('click', function() {
								infowindow1.open(map, marker1);
							});

						}

					}).fail(function(data) {

				// On fail data
				console.error(data)

			})
}
