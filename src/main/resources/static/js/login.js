console.log("Login for employees");

document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm"); // Assuming the form has the id "loginForm"
    const mailInput = document.getElementById("mail");
    const passwordInput = document.getElementById("password");

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevents the default form submission behavior

        const username = mailInput.value;
        const password = passwordInput.value;

        if (username && password) {
            fetch("http://localhost:2020/api/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    mail: username,
                    password: password
                })
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('Network response was not ok.');
                })
                .then(data => {
                    if (data) {
                        window.sessionStorage.setItem('employee', JSON.stringify(data));
                        window.location.href = 'myprofile.html';
                        alert(data); // Assuming your server returns a plain string for success or failure
                        // Redirect or perform other actions based on the response
                    } else {
                        console.error('Error:', error);
                        alert('Login failed. Please try again.');
                    }
                })
                .catch(error => {
                    console.error('error:', error);
                    alert('Login failed. Please try again.');
                });
        } else {
            alert('Please enter both username and password.');
        }
        });
    });

