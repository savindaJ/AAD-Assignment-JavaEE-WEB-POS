# Java EE WEB-POS Project

## Overview

This Java EE WEB-POS project is designed to improve my knowledge of Java Enterprise Edition (Java EE) technologies. It serves as a Point of Sale (POS) system with a web-based user interface. The project utilizes various frontend and backend technologies to provide a robust and user-friendly experience.

## Version

Current Version: 1.4.12

# Login and Registration

To access the full functionality of this Web POS project, users need to register for an account and log in. Follow the steps below to get started:

## Registration

1. Navigate to the registration page by clicking on the "Register" link on the login page.

2. Fill out the required information, including:
  - FirstName
  - LastName
  - Email
  - Password

1. Click the "Register" button to create your account.

2. Upon successful registration, you will be redirected to the login page.

## Login

1. Enter your registered username or email and password on the login page.

2. Click the "Login" button to access the system.

## Important Notes

- Each user must register for a separate account to use the system.

- If you encounter any issues during the registration or login process, please contact [support email or other contact information] for assistance.

- Make sure to keep your login credentials secure and do not share them with others.

## Contributing

If you are contributing to the project, consider providing sample user credentials for testing purposes. You can include a section in your README with test account information for users who want to explore the project without registering.

Sample Test Account:
- FirstName: Savinda
- LastName: Jayasekara
- Email: thantrige32@gmail.com
- Password: abcd1234@ (`This password pattern is important !`)


## Technologies Used

### Frontend
- HTML
- CSS
- JavaScript
- Bootstrap
- jQuery
- Regex
- SweetAlerts Library
- AJAX Requests

### Backend
- Java SE
- Java EE
- Hibernate Framework
- Gson
- Yasson
- Hibernate-Hikari Connection Pool
- Lombok
- MySQL Connector
- Jakarta Servlet
- HTTP Methods
- Tomcat 10

## Project Structure

- **Backend Port:** 8080
- **Backend Endpoint:** [http://localhost:8080/app](http://localhost:8080/app)
- **Frontend Request URL:** WEB-POS>Frontend>db>db.js
    - **Host Variable Changeable**
    - Supports Multiple Users on Different Clients

## Running the Project

1. Ensure that you have Java SE and Tomcat 10 installed.
2. Clone the repository: `git clone [repository-url]`
3. Configure your database settings in `Backend/src/main/resources/hibernate.cfg.xml`.
4. Build and deploy the project using your preferred IDE or build tool.
5. Run Frontend Goto `Frontend/index.html` and run this file your IDE.

## Database Configuration

- Database: [Your Database Name]
- Username: [Your Database Username]
- Password: [Your Database Password]

## Endpoint Details

- **Backend Running Port:** 8080
- **Backend Endpoint URL:** [http://localhost:8080/app](http://localhost:8080/app)

## How to Contribute

If you would like to contribute to this project, please follow these steps:

1. Fork the project.
2. Create a new branch: `git checkout -b feature/your-feature-name`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/your-feature-name`.
5. Submit a pull request.

## License

This project is licensed under the [Your License Name] - see the [LICENSE.md](LICENSE) file for details.

## Acknowledgments

- Mention any libraries, frameworks, or tools that you are using and are grateful for.

Feel free to customize the README according to your project's specific details and structure.
