# Submarine Probe Control API

## Overview
This Spring Boot-based REST API allows controlling a remotely operated submersible probe. The probe moves on a defined grid, receives commands to move forward or backward, turns left or right, and avoids obstacles. It also keeps track of visited coordinates.

## Features
- Define a grid representing the ocean floor.
- Initialize the probe at a given (x, y) coordinate and direction.
- Move the probe forward (F) and backward (B).
- Rotate the probe left (L) and right (R).
- Prevent the probe from moving outside the grid.
- Avoid obstacles.
- Track and return visited coordinates.

## Technologies Used
- **Java 17**
- **Spring Boot** (Spring Web, Spring Boot Starter)
- **JUnit 5 & MockMvc** (for unit testing)
- **Postman or cURL** (for manual testing)

## Installation & Setup
### **1. Clone the Repository**
```sh
 git clone https://github.com/krsnastudy/probe.git
 cd probe
```

### **2. Build the Project**
```sh
mvn clean install
```

### **3. Run the Application**
```sh
mvn spring-boot:run
```

The application will start at **http://localhost:8080**.

## API Endpoints

### **1. Initialize the Probe**
**Request:**
```http
POST /probe/start
```
**Headers:**
```
Content-Type: application/json
```
**Body:**
```json
{
  "x": 0,
  "y": 0,
  "direction": "N",
  "obstacles": [[2,2], [3,3]]
}
```
**Response:**
```
Probe initialized at (0, 0) facing N
```

### **2. Move the Probe**
**Request:**
```http
POST /probe/move
```
**Headers:**
```
Content-Type: application/json
```
**Body:**
```json
{
  "commands": "FFRFF"
}
```
**Response:**
```
Final Position: (2, 2) facing E | Visited: [0,0, 0,1, 0,2, 1,2, 2,2]
```

## Running Tests
To run tests execute:
```sh
mvn test
```

## Future Enhancements
- Support for different grid sizes.
- Improved logging and error handling.
- Database persistence for probe movements.

## Jar File Location
/probe/jarFile/probe-0.0.1-SNAPSHOT.jar
