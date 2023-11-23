



//#######################################

//########################################################


//#########################################
const fetchSearchResults = async (searchTerm) => {
    try {
        const response = await fetch(`https://agri-connect-qj8x.onrender.com/api/workrequests/all?search=${searchTerm}`);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error.message);
        throw error;
    }
};

const displaySearchResults = (results) => {
    const searchResultsDiv = document.getElementById('searchResults');
    searchResultsDiv.innerHTML = ''; // Clear previous results

    if (results.length === 0) {
        searchResultsDiv.innerHTML = '<p>No results found.</p>';
        return;
    }

    // Display the results in a table
    const table = document.createElement('table');
    table.classList.add('table'); // Add Bootstrap table class

    // Define the columns to be displayed
    const columnsToDisplay = ['id', 'farmerName', 'farmAddress', 'workDetails', 'fieldArea', 'TractorNeed', 'paymentMode', 'acceptRequest', 'declineRequest'];

    // Add column names in the first row
    const headerRow = table.insertRow();
    columnsToDisplay.forEach(column => {
        const th = document.createElement('th');
        th.textContent = column;
        headerRow.appendChild(th);
    });

    // Add data rows
    results.forEach(result => {
        const row = table.insertRow();
        columnsToDisplay.forEach(column => {
            const cell = row.insertCell();

            if (column === 'acceptRequest' || column === 'declineRequest') {
                // Create buttons for accept and decline
                const button = document.createElement('button');
                button.textContent = column === 'acceptRequest' ? 'Accept' : 'Decline';
                button.classList.add(column === 'acceptRequest' ? 'btn-success' : 'btn-danger');
                button.addEventListener('click', () => handleButtonClick(column, result));
                cell.appendChild(button);
            } else {
                // Display other data
                cell.textContent = result[column];
            }
        });
    });

    searchResultsDiv.appendChild(table);

    // Display the modal
    const searchModal = document.getElementById('searchModal');
    searchModal.style.display = 'block';
};

const handleButtonClick = async (action, rowData) => {
    if (action === 'acceptRequest') {
        // Implement the logic for accepting the request
        // For example, update the database

        // Redirect to acceptFarmerRequest.html
        window.location.href = 'acceptFarmerRequest.html';
    } else if (action === 'declineRequest') {
        // Implement the logic for declining the request
        // For example, update the user account
        // Remove the row from the table
        removeTableRow(rowData);
    }
};

const removeTableRow = (rowData) => {
    const table = document.querySelector('table');
    const rows = table.getElementsByTagName('tr');

    // Find the index of the row to be removed
    let rowIndex = -1;
    for (let i = 0; i < rows.length; i++) {
        if (rows[i].textContent.includes(rowData.id.toString())) {
            rowIndex = i;
            break;
        }
    }

    // Remove the row from the table
    if (rowIndex !== -1) {
        table.deleteRow(rowIndex);
    }
};

const searchData = async () => {
    try {
        const searchTerm = document.getElementById('searchInput').value;
        const results = await fetchSearchResults(searchTerm);
        displaySearchResults(results);
    } catch (error) {
        console.error('Error in search:', error.message);
        // Handle the error, e.g., show a user-friendly message
    }
};

const closeForm = () => {
    const searchModal = document.getElementById('searchModal');
    searchModal.style.display = 'none';
};

// Close the modal if the user clicks outside of it
window.onclick = (event) => {
    const searchModal = document.getElementById('searchModal');
    if (event.target === searchModal) {
        searchModal.style.display = 'none';
    }
};


//##################################################################

document.addEventListener('DOMContentLoaded', function() {
	// Show the loading screen container initially
	const loadingContainer = document.getElementById('loading-container');
	loadingContainer.classList.add('loaded');

	// Hide the loading screen and show the content after 2 seconds
	setTimeout(function() {
		loadingContainer.style.display = 'none';
	}, 1000);


});

//##################################################################

//#################################################################


// Function to show the Driver Registration form
function driveWithAgriculture() {
	// Hide the ellipsis menu
	document.querySelector('.ellipsis-items').style.display = 'none';

	// Show the Driver Registration form
	document.getElementById('driverRegistrationForm').style.display = 'block';
}

// Function to close the Driver Registration form
document.getElementById('closeFormButton').addEventListener('click', function() {
	// Show the ellipsis menu
	document.querySelector('.ellipsis-items').style.display = 'block';

	// Hide the Driver Registration form
	document.getElementById('driverRegistrationForm').style.display = 'none';
});

// Rest of your registration logic (sendOTP, verifyOTP, submitRegistration, etc.)

//#######################################################################3


function showbooktracktor() {

	window.location.href = 'workRequest.html';

}
function showtractorForm() {
	window.location.href = 'acceptFarmerRequest.html';
}

function openRegisterPage() {
	window.location.href = '/register';
}

function openLoginPage() {
	window.location.href = '/login';
}

function openRegistrationOptions() {
	document.getElementById('registration-options').style.display = 'block';
}

function openFarmerRegistration() {
	window.location.href = '/register';
}

function openDriverRegistration() {
	window.location.href = '/driverregister';
}



// Function to show the AgriConnect Profile card
function AgriConnectProfile() {
	fetch('http://localhost:8080/api/farmers/1')
		.then(response => response.json())
		.then(data => {
			// Check if the user profile data is available
			if (data) {
				// Assign the user profile data to the following variables
				const Name = data.name;
				const phoneNumber = data.phoneNumber;



				// Update the form with the user profile data
				document.querySelector('#AgriConnectProfileForm h1').textContent = `${Name}`;
				document.querySelector('#AgriConnectProfileForm p').textContent = `Phone: ${phoneNumber}`;
				document.querySelector('#AgriConnectProfileForm img').src = 'https://englishtribuneimages.blob.core.windows.net/gallary-content/2019/12/Desk/2019_12$largeimg_729038734.jpg';

				// Show the form
				document.querySelector('#AgriConnectProfileForm').style.display = 'block';

				// Add an event listener to the close button element
				document.getElementById('closeFormButton').addEventListener('click', function(event) {
					// Prevent the ellipsis menu button from being called
					event.stopPropagation();

					// Hide the form
					document.getElementById('AgriConnectProfileForm').style.display = 'none';
				});
			}
		})
		.catch(error => {
			// Handle the error
			console.log('Error fetching user profile data:', error);
		});
}

// Function to manually trigger the click event on the "AgriConnect Profile" button
function triggerAgriConnectProfileClick() {
	document.getElementById('AgriConnectProfileForm').click();
}


// Call the function to update user profile data when the page loads
updateProfileData();






