document.addEventListener('DOMContentLoaded', function() {
	const step1 = document.getElementById('step-1');
	const step2 = document.getElementById('step-2');
	const sendOtpMessage = document.getElementById('send-otp-message');
	const verifyOtpMessage = document.getElementById('verify-otp-message');
	let phoneNumberInput;  // Add this line to store the phone number

	document.getElementById('get-otp-button').addEventListener('click', function() {
		const phoneNumber = document.getElementById('phone-number').value;

		// Store the phone number
		phoneNumberInput = phoneNumber;

		// Call your API to get OTP
		sendOtpMessage.textContent = 'Sending OTP...';
		sendOtpMessage.style.color = 'black';

		fetch('/api/send-otp', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				phoneNumber: phoneNumber,
			}),
		})
			.then(response => {
				if (!response.ok) {
					throw new Error(`Error sending OTP: ${response.statusText}`);
				}
				return response.text(); // Read the response as text
			})
			.then(data => {
				if (data === 'OTP sent successfully') {
					sendOtpMessage.textContent = 'OTP sent successfully';
					sendOtpMessage.style.color = 'green';
					step1.style.display = 'none';
					step2.style.display = 'block';
				} else {
					sendOtpMessage.textContent = `Error: ${data}`;
					sendOtpMessage.style.color = 'red';
				}
			})
			.catch(error => {
				sendOtpMessage.textContent = 'Error sending OTP';
				sendOtpMessage.style.color = 'red';
				console.error('Error:', error);
			});
	});

	document.getElementById('verify-otp-button').addEventListener('click', function() {
		const otp = document.getElementById('otp').value;

		// Call your API to verify OTP
		verifyOtpMessage.textContent = 'Verifying OTP...';
		verifyOtpMessage.style.color = 'black';

		fetch('/api/verify-otp', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				phoneNumber: phoneNumberInput,  // Use the stored phone number
				otp: otp,
			}),
		})
			.then(response => {
				if (!response.ok) {
					throw new Error(`Error verifying OTP: ${response.statusText}`);
				}
				return response.json(); // Parse the response as JSON
			})
			.then(data => {
				console.log('Response Data:', data);  // Log the response data

				if (data.message === 'OTP verified successfully') {
					verifyOtpMessage.textContent = 'OTP verified your login...';
					verifyOtpMessage.style.color = 'green';
					// You can redirect the user to the dashboard or another page here.
				} else {
					verifyOtpMessage.textContent = `Error: ${data.message}`;
					verifyOtpMessage.style.color = 'red';
				}
			})
			.catch(error => {
				console.error('Error:', error);
				verifyOtpMessage.textContent = 'Error verifying OTP';
				verifyOtpMessage.style.color = 'red';
			});
	});
});


// Add this code to handle My Profile link click
myProfileLink.addEventListener('click', function() {
	const phoneNumber = document.getElementById('phone-number').value;

	// Call your API to check if the user is already registered
	fetch(`/api/farmers/${phoneNumber}`)
		.then(response => response.json())
		.then(data => {
			if (data.ok) {
				// User is registered, display profile information
				const profileInfo = `Name: ${data.name}, Surname: ${data.surname}, Phone Number: ${data.phone}, Village: ${data.village}, Subdistrict: ${data.subdistrict}`;
				alert(profileInfo); // You can replace this with displaying a modal or navigating to a profile page
			} else {
				// User is not registered, display an error message
				alert('User not registered. Please register first.');
			}
		})
		.catch(error => {
			console.error('Error checking user registration:', error);
			alert('Error checking user registration. Please try again.');
		});
});



	