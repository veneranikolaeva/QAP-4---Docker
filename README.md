# Project Setup & Usage

## Supported Search APIs

### Members:

- **Search by name:**  
  `GET /members/search?name=<name>`  
- **Search by phone number:**  
  `GET /members/search?phone=<phone_number>`  
- **Search by start date:**  
  `GET /members/search?startDate=<YYYY-MM-DD>`  

---

### Tournaments:

- **Search by start date:**  
  `GET /tournaments/search?startDate=<YYYY-MM-DD>`  
  - **Search by location:**  
  `GET /tournaments/search?location=<location>`  
- **Find all members in a specific tournament:**  
  `GET /tournaments/{tournamentId}/members`  

---

## How to Run the Project in Docker

### Prerequisites:

- Docker installed and running.

### Instructions:

1. **Build the Docker Image**
'docker build -t golfapi .'
2. **Run the Docker Container**
'docker run -d -p 8080:8080 --name golfapi_container golfapi'
