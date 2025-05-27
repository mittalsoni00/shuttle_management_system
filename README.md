# Shuttle Management System 🚌

A Spring Boot-based web application that manages shuttle details including user authentication, booking, and shuttle data, integrated with a MySQL backend. This project is deployed on Render and uses a Render-hosted database.

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- MySQL
- Maven
- Docker
- Render (Deployment Platform)

---

## 🔐 JWT Configuration

JWT is used for secure authentication.

🌍 Port Binding for Deployment

Important for deploying to Render:

Render sets a dynamic port via an environment variable $PORT. Update application.properties to: server.port=${PORT:8080}
This ensures compatibility with Render’s container-based deployment.

🚀 Deployment on Render

Steps Taken:
	1.	Created a Web Service on Render (from GitHub repo)
	2.	Configured Environment Variables (Optional if credentials are hardcoded)
	3.	Exposed dynamic port using server.port=${PORT:8080}
	4.	Set the build command: ./mvnw clean install
	5.	Set the start command: java -jar target/<your-jar-file>.jar
	6.	Fixed exit status 1 issue: Previously the app failed because it didn’t bind to the correct port.
	7.	Deployment successful 🎉


 📂 How to Run Locally
1.	Clone the repo: git clone https://github.com/<your-username>/shuttle-management-system.git

2.	Navigate to the project: cd shuttle-management-system
3.	Build and run: ./mvnw spring-boot:run
   App will be available at http://localhost:8080

   📌 Contributors
	•	Mittal Soni

 ✅ Status
	•	Backend complete
	•	JWT Authentication implemented
	•	MySQL configured on Railway
	•	Successfully deployed on Render
	•	Easy Access of APIs: Swagger-UI
 
