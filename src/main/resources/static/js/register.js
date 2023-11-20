function displayMessage(message, success) {
  const messageElement = document.getElementById('farmerRegistrationSuccess');
  messageElement.style.color = success ? 'green' : 'red';
  messageElement.innerText = message;
  messageElement.style.display = 'block';

  if (success) {
    setTimeout(() => {
      messageElement.style.display = 'none';
      if (messageElement.innerText === 'Password match!') {
        // Passwords match, clear the password match message
        messageElement.innerText = '';
      }
      window.location.reload();
    }, 2000);
  }
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
	const phoneNumberInput = document.getElementById(`${userType}-phone-number`).value;
	const registrationMessage = document.getElementById('registrationMessage');

	if (!otpInput) {
		console.error(`Element with ID "${userType}-otp" not found.`);
		return;
	}

	const otpValue = otpInput.value;

	const requestBody = {
		phoneNumber: phoneNumberInput,
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
				registrationMessage.textContent = 'OTP verification failed. Please check your OTP.';
				registrationMessage.style.color = 'red';
				registrationMessage.style.display = 'block';
				document.getElementById(`${userType}OtpErrorMessage`).style.display = 'block';
			} else {
				console.log('OTP verification successful');
				registrationMessage.textContent = 'OTP verified successfully';
				registrationMessage.style.color = 'green';
				registrationMessage.style.display = 'block';
				document.getElementById(`${userType}RegistrationSuccess`).style.display = 'none';
			}
		})
		.catch(error => {
			console.error('Error:', error);
		});
}

function submitRegistration(userType) {
  const name = document.getElementById('inputName').value;
  const surname = document.getElementById('inputSurname').value;
  const country = document.getElementById('farmer-country').value;
  const state = document.getElementById('farmer-state').value;
  const district = document.getElementById('inputDistrict').value;
  const subDistrict = document.getElementById('inputSub-District').value;
  const village = document.getElementById('inputVillage').value;
  const pincode = document.getElementById('inputPincode').value;
  const birthDate = document.getElementById('farmer-birth-date').value;
  const phoneNumber = document.getElementById(userType + '-phone-number').value;
  const password = document.getElementById('inputPassword').value;
  const confirmPass = document.getElementById('inputConfirmPassword').value;

  // Check if any of the required fields are empty
  if (!name || !surname || !country || !state || !district || !subDistrict || !village || !pincode || !birthDate || !phoneNumber || !password || !confirmPass) {
    displayMessage('Please fill in all required fields.', false);
    return;
  }

  // Check if passwords match
  if (password !== confirmPass) {
    displayMessage('Passwords do not match. Please check and try again.', false);
    return;
  }

  // Display password match message
  displayMessage('Password match!', true);

  // Prepare the data to be sent to the registration endpoint
  const registrationData = {
    name,
    surname,
    country,
    state,
    district,
    subDistrict,
    village,
    pincode,
    birthDate,
    phoneNumber,
    password,
  };

  // Make an API request to register the farmer
  fetch('/api/farmers/register', {
    method: 'POST',
    body: JSON.stringify(registrationData),
    headers: {
      'Content-Type': 'application/json',
    }
  })
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        // Registration successful, display message
        displayMessage('Registration successful!', true);
      } else {
        // Registration failed, display message
        displayMessage('Registration failed. Please try again.', false);
      }
    });
}