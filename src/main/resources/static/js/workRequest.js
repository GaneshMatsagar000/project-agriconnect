

function showbooktracktor() {
	const booktracktor = document.getElementById('booktracktor');
	const tractorForm = document.getElementById('tractorForm');

	booktracktor.style.display = 'block';
	tractorForm.style.display = 'none';
}

function showTractorRegistration() {
	const booktracktor = document.getElementById('booktracktor');
	const tractorForm = document.getElementById('tractorForm');

	booktracktor.style.display = 'none';
	tractorForm.style.display = 'block';
}

function acceptOrderNearMe() {
	// Replace 'YOUR_API_ENDPOINT' with the actual API endpoint URL
	const apiEndpoint = '/api/tractorowners/enable-accept-order';

	// Make an API request (example using the fetch API)
	fetch(apiEndpoint, {
		method: 'POST', // You can change this to 'GET' if needed
		headers: {
			'Content-Type': 'application/json',
			// Add any required headers here
		},
		body: JSON.stringify({
			// Add any request data or parameters here
		}),
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then((data) => {
			// Handle the API response data here
			console.log('API Response:', data);
			// You can perform any additional actions here
		})
		.catch((error) => {
			console.error('Error:', error);
			// Handle errors here
		});
}

function toggleOtherInput(select) {
	const otherInput = document.getElementById('OtherFieldArea');
	if (select.value === 'other') {
		otherInput.style.display = 'block';
		otherInput.setAttribute('name', 'FieldArea'); // Set the name to match your form
	} else {
		otherInput.style.display = 'none';
		otherInput.removeAttribute('name'); // Remove the name when not using "Other"
	}
}

function toggleOtherWorkDetails(select) {
	const otherInput = document.getElementById('otherWorkDetails');
	if (select.value === 'other') {
		otherInput.style.display = 'block';
		otherInput.setAttribute('name', 'workDetails'); // Set the name to match your form
	} else {
		otherInput.style.display = 'none';
		otherInput.removeAttribute('name'); // Remove the name when not using "Other"
	}
}

function toggleOtherTractorName(select) {
	const otherInput = document.getElementById('otherTractorName');
	if (select.value === 'other') {
		otherInput.style.display = 'block';
		otherInput.setAttribute('name', 'tractorName'); // Set the name to match your form
	} else {
		otherInput.style.display = 'none';
		otherInput.removeAttribute('name'); // Remove the name when not using "Other"
	}
}

let map;
let geocoder;
let placesService;
let autocomplete;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 0, lng: 0 }, // Initial center
        zoom: 8, // Initial zoom level
    });

    geocoder = new google.maps.Geocoder();
    placesService = new google.maps.places.PlacesService(map);

    // Create the "Current Location" button and add it to the map
    const currentLocationButton = document.getElementById('currentLocationButton');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(currentLocationButton);

    // Create the "Search Location" button and add it to the map
    const searchLocationButton = document.getElementById('searchLocationButton');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(searchLocationButton);

    // Add click event listeners to the buttons
    currentLocationButton.addEventListener('click', showCurrentLocation);
    searchLocationButton.addEventListener('click', showSearchContainer);

    // Enable Places Autocomplete for the input field
    autocomplete = new google.maps.places.Autocomplete(document.getElementById('locationSearchInput'));
    autocomplete.addListener('place_changed', handlePlaceSelection);
}


function showCurrentLocation() {
  // Use geolocation to get the user's current location
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) {
      const userLocation = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };

      // Create a marker on the map to represent the user's current location
      const userMarker = new google.maps.Marker({
        position: userLocation,
        map: map,
        title: "Your Location"
      });

      // Pan the map to the user's current location
      map.panTo(userLocation);

      // Fetch additional information about the farmer's location (e.g., area, area picture, village, sub-district) from your data source or API
      // You can make an API call here to retrieve the data

      // Once you have the data, you can display it in an info window on the map
      const farmerInfo = {
        area: "",
        areaPinCode: "",
        village: "",
        subDistrict: "",
      };

      // Make an API call to retrieve the farmer's information using the Google Maps Geocoding API
      const geocodingRequest = {
        latLng: userLocation,
      };

      geocoder.geocode(geocodingRequest, function (results, status) {
  if (status === google.maps.GeocoderStatus.OK) {
    const place = results[0];

    // Get the farmer's information from the place object
    const area = place.components.find((component) => component.types.includes("sublocality_level_1")).long_name;
    const areaPinCode = place.components.find((component) => component.types.includes("postal_code")).short_name;
    const village = place.components.find((component) => component.types.includes("locality")).long_name;
    const subDistrict = place.components.find((component) => component.types.includes("administrative_area_level_3")).long_name;

    // Update the farmerInfo variable with the data
    farmerInfo.area = area;
    farmerInfo.areaPinCode = areaPinCode;
    farmerInfo.village = village;
    farmerInfo.subDistrict = subDistrict;

    // Create an info window to display the farmer's information
    const farmerInfoWindow = new google.maps.InfoWindow({
      content: `
        <h2>Farmer Information</h2>
        <p>Area: ${farmerInfo.area}</p>
        <p>Area Pin Code: ${farmerInfo.areaPinCode}</p>
        <p>Village: ${farmerInfo.village}</p>
        <p>Sub-District: ${farmerInfo.subDistrict}</p>
      `
    });

    // Open the info window near the user's location marker
    farmerInfoWindow.open(map, userMarker);
  } else {
    // Handle the error
    alert("Error retrieving farmer information: " + status.error_message);
  }
});

    });
  } else {
    alert("Geolocation is not supported by this browser.");
  }
}

function showSearchContainer() {
    const searchContainer = document.getElementById('searchContainer');
    searchContainer.style.display = 'block';
}

function handlePlaceSelection() {
    // Get the selected place from the autocomplete input
    const place = autocomplete.getPlace();

    // Check if a valid place is selected
    if (place.geometry) {
        // Create a marker on the map for the selected place
        const placeMarker = new google.maps.Marker({
            position: place.geometry.location,
            map: map,
            title: place.name
        });

        // Pan the map to the selected place's location
        map.panTo(place.geometry.location);

        // Update the input field with the selected place's name or address
        document.getElementById('locationSearchInput').value = place.name || place.formatted_address;
    }
}


function submitFarmerForm(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get the form data
    const formData = {
        farmerName: document.getElementById('farmerName').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        farmAddress: document.getElementById('farmAddress').value,
        workDetails: document.getElementById('workDetails').value,
        otherWorkDetails: document.getElementById('otherWorkDetails').value,
        FieldArea: document.getElementById('FieldArea').value,
        OtherFieldArea: document.getElementById('OtherFieldArea').value,
        WhenTractorWillCome: document.getElementById('WhenTractorWillCome').value,
        paymentMode: document.getElementById('paymentMode').value,
        latitude: 0, // Replace with the actual latitude from the map
        longitude: 0, // Replace with the actual longitude from the map
    };

    // Replace 'YOUR_API_ENDPOINT' with the actual API endpoint URL
    const apiEndpoint = '/api/workrequests/submit';

    // Make an API request (example using the fetch API)
    fetch(apiEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then((data) => {
        // Handle the API response data here
        console.log('API Response:', data);
        // You can perform any additional actions here, such as showing a success message
        document.getElementById('farmerSuccessMessage').textContent = data;
    })
    .catch((error) => {
        console.error('Error:', error);
        // Handle errors here, such as showing an error message
        document.getElementById('farmerSuccessMessage').textContent = 'Error submitting the form';
    });
}

document.getElementById('submitBtn').addEventListener('click', function (event) {
    // Prevent the form from submitting
    event.preventDefault();

    // Display the success message
    const successMessage = document.getElementById('temporarySuccessMessage');
    successMessage.style.display = 'block';
    successMessage.textContent = 'Request sent successfully!';

    // Hide the form
    document.getElementById('booktracktor').style.display = 'none';

   
    
});




function closeForm(formId) {
	document.getElementById(formId).style.display = "none";
	setTimeout(function () {
        window.location.href = '/index.html'; // Change '/home' to your desired URL
    }, 1000);
}

