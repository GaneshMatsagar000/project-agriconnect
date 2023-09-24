// Show Registration Options
function showRegistrationOptions() {
	document.getElementById('registrationOptions').style.display = 'block';
}

// Sample API endpoint for fetching countries
const countryApiEndpoint = '/api/countries';

// Sample API endpoint for fetching states by country
const stateApiEndpoint = '/api/states?countryId=';

// Sample API endpoint for fetching districts by state
const districtApiEndpoint = '/api/districts?stateId=';

// Sample API endpoint for fetching sub-districts by district
const subDistrictApiEndpoint = '/api/subdistricts?districtId=';

// Sample API endpoint for fetching villages by sub-district
const villageApiEndpoint = '/api/villages?subdistrictId=';

// JavaScript code for populating the country dropdowns and handling form submission
// Note: Replace the following API endpoints with your actual API endpoints

// Sample API endpoint for registering a farmer
const registerFarmerApiEndpoint = '/api/farmers/register';

// Sample API endpoint for registering a driver
const registerDriverApiEndpoint = '/api/register/driver';

// Function to populate a dropdown
function populateDropdown(dropdown, data) {
	dropdown.innerHTML = '';
	const defaultOption = document.createElement('option');
	defaultOption.value = '';
	defaultOption.textContent = 'Select Option';
	dropdown.appendChild(defaultOption);
	data.forEach(item => {
		const option = document.createElement('option');
		option.value = item.id;
		option.textContent = item.name;
		dropdown.appendChild(option);
	});
}

// Function to fetch data and populate a dropdown
function fetchDataAndPopulateDropdown(endpoint, dropdown) {
	fetch(endpoint)
		.then(response => response.json())
		.then(data => populateDropdown(dropdown, data))
		.catch(error => console.error('Error fetching data:', error));
}

// Function to populate country dropdowns
function populateCountryDropdowns() {
	const farmerCountryDropdown = document.getElementById('farmer-country');
	const driverCountryDropdown = document.getElementById('driver-country');

	// Fetch countries and populate dropdowns
	fetchDataAndPopulateDropdown(countryApiEndpoint, farmerCountryDropdown);
	fetchDataAndPopulateDropdown(countryApiEndpoint, driverCountryDropdown);
}

// Function to handle form submission for registration
function submitRegistration(userType) {
	const fullName = document.getElementById(`${userType}-fullname`).value;
	const password = document.getElementById(`${userType}-password`).value;
	const confirmPassword = document.getElementById(`${userType}-confirm-password`).value;
	const countryId = document.getElementById(`${userType}-country`).value;
	const stateId = document.getElementById(`${userType}-state`).value;
	const districtId = document.getElementById(`${userType}-district`).value;
	const subDistrictId = document.getElementById(`${userType}-subdistrict`).value;
	const villageId = document.getElementById(`${userType}-village`).value;
	const pincode = document.getElementById(`${userType}-pincode`).value;

	// Perform form validation here
	

	// Create an object with the user's data
	const userData = {
		fullName,
		password,
		confirmPassword,
		countryId,
		stateId,
		districtId,
		subDistrictId,
		villageId,
		pincode,
	};
	
	

	// Determine the registration API endpoint based on user type
	const registerApiEndpoint = userType === 'farmer' ? '/api/farmers/register' : '/api/register/driver';

	// Make an API request to register the user
	fetch(registerApiEndpoint, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(userData),
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				// Registration successful
				// Display a success message or redirect the user
				console.log(`${userType} registration successful`);
				// Display success message to the user
				showRegistrationSuccessMessage();
			} else {
				// Registration failed
				// Display an error message or handle the error appropriately
				console.error(`${userType} registration failed: ${data.message}`);
				// Display error message to the user
				showRegistrationErrorMessage(data.message);
			}
		})
		.catch(error => {
			// Handle API request error
			console.error(`Error registering ${userType}:`, error);
			// Display error message to the user
			showRegistrationErrorMessage('An error occurred while registering. Please try again.');
		});
}

function showRegistrationSuccessMessage() {
	// Display a success message to the user
	const successMessage = document.getElementById('registration-success-message');
	successMessage.textContent = 'Registration successful!';
	successMessage.style.color = 'green';
	successMessage.style.display = 'block';
}

function showRegistrationErrorMessage(message) {
	// Display an error message to the user
	const errorMessage = document.getElementById('registration-error-message');
	errorMessage.textContent = `Registration failed: ${message}`;
	errorMessage.style.color = 'red';
	errorMessage.style.display = 'block';
}

// Show Registration Fields for the Selected User Type
function showRegistration(userType) {
	document.getElementById('userType').value = userType;

	// Get references to the registration forms
	const farmerForm = document.getElementById('farmerForm');
	const driverForm = document.getElementById('driverRegistrationForm');

	// Hide all registration forms
	farmerForm.style.display = 'none';
	driverForm.style.display = 'none';

	// Show the selected registration form
	if (userType === 'farmer') {
		farmerForm.style.display = 'block';
	} else if (userType === 'driver') {
		driverForm.style.display = 'block';
	}
}

// Initialize the page
populateCountryDropdowns();

// Get references to the HTML select elements
const countrySelectFarmer = document.getElementById('farmer-country');
const stateSelectFarmer = document.getElementById('farmer-state');
const districtSelectFarmer = document.getElementById('farmer-district');
const subdistrictSelectFarmer = document.getElementById('farmer-subdistrict');
const villageSelectFarmer = document.getElementById('farmer-village');

// Function to populate the states dropdown based on the selected country for Farmer
countrySelectFarmer.addEventListener('change', () => {
	const selectedCountry = countrySelectFarmer.value;
	// Call your API to get states for the selected country
	fetch(`${stateApiEndpoint}${selectedCountry}`)
		.then((response) => response.json())
		.then((data) => {
			// Clear existing options
			stateSelectFarmer.innerHTML = '<option value="">Select State</option>';
			districtSelectFarmer.innerHTML = '<option value="">Select District</option>';
			subdistrictSelectFarmer.innerHTML = '<option value="">Select Sub-District</option>';
			villageSelectFarmer.innerHTML = '<option value="">Select Village</option>';

			// Populate states dropdown
			data.forEach((state) => {
				const option = document.createElement('option');
				option.value = state.id;
				option.text = state.name;
				stateSelectFarmer.appendChild(option);
			});
		})
		.catch((error) => {
			console.error('Error fetching states:', error);
		});
});

// Function to populate the districts dropdown based on the selected state for Farmer
stateSelectFarmer.addEventListener('change', () => {
	const selectedState = stateSelectFarmer.value;
	// Call your API to get districts for the selected state
	fetch(`${districtApiEndpoint}${selectedState}`)
		.then((response) => response.json())
		.then((data) => {
			// Clear existing options
			districtSelectFarmer.innerHTML = '<option value="">Select District</option>';
			subdistrictSelectFarmer.innerHTML = '<option value="">Select Sub-District</option>';
			villageSelectFarmer.innerHTML = '<option value="">Select Village</option>';

			// Populate districts dropdown
			data.forEach((district) => {
				const option = document.createElement('option');
				option.value = district.id;
				option.text = district.name;
				districtSelectFarmer.appendChild(option);
			});
		})
		.catch((error) => {
			console.error('Error fetching districts:', error);
		});
});

// Function to populate the subdistricts dropdown based on the selected district for Farmer
districtSelectFarmer.addEventListener('change', () => {
	const selectedDistrict = districtSelectFarmer.value;
	// Call your API to get subdistricts for the selected district
	fetch(`${subDistrictApiEndpoint}${selectedDistrict}`)
		.then((response) => response.json())
		.then((data) => {
			// Clear existing options
			subdistrictSelectFarmer.innerHTML = '<option value="">Select Sub-District</option>';
			villageSelectFarmer.innerHTML = '<option value="">Select Village</option>';

			// Populate subdistricts dropdown
			data.forEach((subdistrict) => {
				const option = document.createElement('option');
				option.value = subdistrict.id;
				option.text = subdistrict.name;
				subdistrictSelectFarmer.appendChild(option);
			});
		})
		.catch((error) => {
			console.error('Error fetching subdistricts:', error);
		});
});

// Function to populate the villages dropdown based on the selected subdistrict for Farmer
subdistrictSelectFarmer.addEventListener('change', () => {
	const selectedSubdistrict = subdistrictSelectFarmer.value;
	// Call your API to get villages for the selected subdistrict
	fetch(`${villageApiEndpoint}${selectedSubdistrict}`)
		.then((response) => response.json())
		.then((data) => {
			// Clear existing options
			villageSelectFarmer.innerHTML = '<option value="">Select Village</option>';

			// Populate villages dropdown
			data.forEach((village) => {
				const option = document.createElement('option');
				option.value = village.id;
				option.text = village.name;
				villageSelectFarmer.appendChild(option);
			});
		})
		.catch((error) => {
			console.error('Error fetching villages:', error);
		});
});

// Function to handle form submission for Farmer registration
function submitFarmerRegistration() {
	submitRegistration('farmer');
}

// Function to handle form submission for Driver registration
function submitDriverRegistration() {
	submitRegistration('driver');
}

// Function to send OTP for Farmer or Driver
function sendOTP(userType) {
    const phoneNumberInput = document.getElementById(`${userType}-phone-number`).value;

    fetch('/api/send-otp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userType: userType,
            phoneNumber: phoneNumberInput,
        }),
    })
    .then(response => {
        // Check the response status
        if (!response.ok) {
            throw new Error(`Error sending OTP: ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        const otpSuccessMessage = document.getElementById('otpSuccessMessage');
        if (data.message === 'OTP sent successfully') {
            console.log(`OTP sent to ${userType}`);
            // Show the success message
            otpSuccessMessage.textContent = 'OTP sent successfully';
        } else {
            console.error(`Error sending OTP: ${data.message}`);
            // Display an error message if OTP sending fails
            otpSuccessMessage.textContent = `Error sending OTP: ${data.message}`;
            otpSuccessMessage.style.color = 'red';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        // Display an error message if there's a network issue or unexpected error
        const otpSuccessMessage = document.getElementById('otpSuccessMessage');
        otpSuccessMessage.textContent = 'OTP sent successfully';
        otpSuccessMessage.style.color = 'green';
    });
}

function verifyOTP(userType) {
    const otpInput = document.getElementById(`${userType.charAt(0).toUpperCase() + userType.slice(1)}-otp`);
    const phoneNumberInput = document.getElementById(`${userType}-phone-number`).value; // Get the phone number input field value
    const registrationMessage = document.getElementById('registrationMessage');

    if (!otpInput) {
        console.error(`Element with ID "${userType}-otp" not found.`);
        return;
    }

    const otpValue = otpInput.value;

    const requestBody = {
        phoneNumber: phoneNumberInput, // Change userType to phoneNumber
        otp: otpValue,
    };

    fetch('/api/verify-otp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
    })
    .then(response => response.json())
    .then(data => {
        console.log('API Response:', data);
        if (data.error) {
            console.log('Incorrect OTP');
            registrationMessage.textContent = 'OTP verified successfully';
            registrationMessage.style.color = 'red';
            registrationMessage.style.display = 'block';
            document.getElementById(`${userType}OtpErrorMessage`).style.display = 'block';
        } else {
            console.log('OTP verification successful');
            registrationMessage.textContent = 'OTP verified successfully';
            registrationMessage.style.color = 'green';
            registrationMessage.style.display = 'block';
            // Hide the "Farmer Registration done!" message if it's displayed
            document.getElementById(`${userType}RegistrationSuccess`).style.display = 'none';
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}


//#######################################

function toggleEllipsisItems() {
	console.log("Toggle ellipsis items function called");
	const ellipsisItems = document.querySelector('.ellipsis-items');
	if (window.getComputedStyle(ellipsisItems).display === 'none') {
		ellipsisItems.style.display = 'block';
	} else {
		ellipsisItems.style.display = 'none';
	}
}

function toggleLanguageMenu() {
	const languageMenu = document.getElementById('languageMenu');
	if (window.getComputedStyle(languageMenu).display === 'none') {
		languageMenu.style.display = 'block';
	} else {
		languageMenu.style.display = 'none';
	}
}

function changeLanguage(language) {
	// Implement code to change the website language here
	alert('Language changed to ' + language);
}

function toggleNotifications() {
	// Implement code to show notifications here
	alert('Showing notifications');
}

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

function showAgricultureServices() {
	const agricultureServices = document.getElementById('agricultureServices');
	const seeServicesButton = document.getElementById('seeServicesButton');

	if (window.getComputedStyle(agricultureServices).display === 'none') {
		agricultureServices.style.display = 'block';
		seeServicesButton.innerText = 'Hide Agriculture Services';
	} else {
		agricultureServices.style.display = 'none';
		seeServicesButton.innerText = 'See Agriculture Services';
	}
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

var apiUrl = 'http://localhost:8080/api/tractorowners/register';
var username = 'ganesh';
var password = 'ganesh';

// Create a Basic Authentication header
var headers = new Headers();
headers.set('Authorization', 'Basic ' + btoa(username + ':' + password));

// Send the request with the headers
fetch(apiUrl, {
	method: 'POST',
	headers: headers,
	// ...
})
	.then(function(response) {
		// Handle the response
	})
	.catch(function(error) {
		// Handle errors
	});

// Function to toggle visibility of the notices overlay
function toggleNoticesOverlay() {
	var overlay = document.getElementById('noticesOverlay');
	if (window.getComputedStyle(overlay).display === 'none') {
		overlay.style.display = 'block';
	} else {
		overlay.style.display = 'none';
	}
}

// Attach click event listener to the "Notices" button
document.getElementById('noticesButton').addEventListener('click', function() {
	toggleNoticesOverlay();
});

// Function to toggle visibility of overlays
function toggleOverlay(overlayId) {
	var overlay = document.getElementById(overlayId);
	if (window.getComputedStyle(overlay).display === 'none') {
		overlay.style.display = 'block';
	} else {
		overlay.style.display = 'none';
	}
}

document.getElementById('privacyButton').addEventListener('click', function() {
	toggleOverlay('privacyOverlay');
});

document.getElementById('termsButton').addEventListener('click', function() {
	toggleOverlay('termsOverlay');
});

//########################################
// Function to toggle visibility of the footer overlay
function toggleFooterOverlay() {
	var footerOverlay = document.getElementById('footerOverlay');
	if (window.getComputedStyle(footerOverlay).display === 'none') {
		footerOverlay.style.display = 'block';
	} else {
		footerOverlay.style.display = 'none';
	}
}

function openFooterOverlay(target) {
	var footerOverlay = document.getElementById('footerOverlay');
	var footerOverlayContent = document.getElementById('footerOverlayContent');

	// Define content for each target
	var contentMap = {
		home: 'this is just home i design it later.',
		aboutUs: 'AgricultureApp, spearheaded by Ganesh Balnath Matsagar, is a revolutionary mobile application designed to transform the agricultural landscape. Our commitment to addressing the challenges of accessibility, communication, transparency, and efficiency in the farming sector is unwavering. With features such as effortless connectivity, real-time visibility, transparent pricing, and secure transactions, we aim to empower farmers and tractor owners alike. AgricultureApp is not just an app; it\'s a community-driven platform that values your feedback and strives to make farming more accessible, productive, and profitable. Join us in this agricultural revolution. Contact us at ganeshmatsagar000@gmail.com or +9112653772 to learn more about how we can collaborate and drive change together.',
		help: 'Help content is displayed here.',
		faq: 'Frequently Asked Questions (FAQ) content.',
		feedback: 'Feedback content is shown in this section.',
		terms: ' Our commitment to user-friendliness and community building sets us apart. With AgriConnect, you can navigate the app with ease, even if you re not tech-savvy. Join our growing community of farmers and tractor operators, experience the benefits of transparent pricing, real-time connectivity, and data-driven insights, all while contributing to the economic growth of farming communities. In the agricultural sector, trust and community are essential. AgriConnect fosters a sense of belonging and reliability among its users. Our platform is designed to be user-friendly, ensuring that you can efficiently access the tools and services you need. Moreover, our review and rating system encourages trust-building among users, promoting high-quality service delivery within the agricultural community. Join Ganesh Balnath Matsagar and the AgriConnect revolution today! Contact us at ganeshmatsagar000@gmail.com or call +9112653772 to learn more and be part of this agricultural transformation. Embrace the future of farming with AgriConnect.',
		copyright: 'Copyright Policy content.',
		hyperlinking: 'Hyperlinking Policy content.',
		privacy: 'At AgriConnect, your privacy is our priority. We employ cutting-edge technologies like blockchain for transparent and secure transactions while safeguarding your personal data. Trust in our platform\'s privacy measures as you embark on a journey to enhance your agricultural experience. Privacy matters, especially when it comes to your financial transactions and personal data. AgriConnect is committed to ensuring that your information remains confidential and secure. Our use of blockchain technology guarantees the transparency and immutability of your transaction history while keeping your identity and data safe. So, you can connect with fellow farmers and tractor operators, make seamless payments, and access valuable insights with peace of mind. Join AgriConnect today and experience the future of secure and private agricultural collaboration.',
		website: 'Website Policy content.',
	};

	// Get the content based on the target
	var content = contentMap[target] || 'Content not found for this link.';

	// Set the content in the footer overlay
	footerOverlayContent.innerHTML = content;

	toggleFooterOverlay();
}

function closeFooterOverlay() {
	var footerOverlay = document.getElementById('footerOverlay');
	footerOverlay.style.display = 'none';
}

// Attach click event listeners to the footer links
var footerLinks = document.querySelectorAll('.footer p');
footerLinks.forEach(function(link) {
	link.addEventListener('click', function() {
		openFooterOverlay(link.getAttribute('data-target'));
	});
});

function showFarmerRegistrationForm() {
	var booktracktor = document.getElementById("booktracktor");
	farmerForm.style.display = "block";
}

function showTractorRegistrationForm() {
	var tractorForm = document.getElementById("tractorForm");
	tractorForm.style.display = "block";
}

function closeForm(formId) {
	document.getElementById(formId).style.display = "none";
}

function submitFarmerForm(event) {
	// Handle form submission logic here
	event.preventDefault();
	// Display success message
	document.getElementById("farmerSuccessMessage").style.display = "block";
	// Close the form after a brief delay (e.g., 2 seconds)
	setTimeout(function() {
		closeForm('booktracktor');
		// Hide the success message again
		document.getElementById("farmerSuccessMessage").style.display = "none";
	}, 2000);
}

function submitTractorForm(event) {
	// Handle form submission logic here
	event.preventDefault();
	// Display success message
	document.getElementById("tractorSuccessMessage").style.display = "block";
	// Close the form after a brief delay (e.g., 2 seconds)
	setTimeout(function() {
		closeForm('tractorForm');
		// Hide the success message again
		document.getElementById("tractorSuccessMessage").style.display = "none";
	}, 2000);
}

//########################################################


//#########################################3
