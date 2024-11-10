# qp-assessment
Grocery Booking API

This project implements a Grocery Booking API that enables users to browse, book grocery items, and allows admins to manage the grocery inventory. The system has two primary roles: Admin and User, each with unique responsibilities and accessible endpoints.

Table of Contents
	Features
	Technologies
	Getting Started
	API Endpoints
	Authentication and Authorization
	Database Configuration
	
Features
	Admin Responsibilities
		Add, view, remove, update grocery items
		Manage inventory levels of items
	User Responsibilities
		View available grocery items
		Place an order with multiple items
	Technologies
		Backend: Java with Spring Boot
		Database: PostgreSQL
		Authentication & Authorization: JWT Tokens
		API Documentation: RESTful APIs
		
Getting Started
Prerequisites
	Java 17 or higher
	PostgreSQL database
	Maven (for dependency management)
	
API Endpoints
1. User Creation and Login
	Create a New User
		Endpoint: POST /open/save
		Description: Registers a new user.
		Request Body: 
		{
		  "userName": "string",
		  "firstName": "string",
		  "lastName": "string",
		  "role": "string",
		  "mobileNumber": "string",
		  "emailId": "string"
		}

	User Login
		Endpoint: POST /open/login
		Description: Authenticates a user.
		Request Body:
		{
		  "userName": "string",
		  "password": "string"
		}
	Note: Default password for initial setup is Admin@123.
		
2. Admin Responsibilities
	Add New Grocery Items
		Endpoint: POST /api/items
		Request Body:
		[
		  {
			"name": "string",
			"description": "string",
			"price": "decimal",
			"quantity": "int"
		  }
		]
		Authorization: Requires ROLE_ADMIN.	

	View Existing Grocery Items
		Endpoint: GET /api/items/page
		Query Parameters: filterText (optional)
		Authorization: Requires ROLE_ADMIN.
		
	Remove Grocery Items
		Endpoint: PUT /api/items/{key}
		Path Variable: key - Item identifier
		Request Body:
		{
		  "activeFlag": "char"// Y/N
		}
		Authorization: Requires ROLE_ADMIN.
		
	Update Item Details
		Endpoint: POST /api/items/update/{key}
		Path Variable: key - Item identifier
		Request Body:
		{
		  "name": "string",
		  "price": "decimal"
		}
		Authorization: Requires ROLE_ADMIN.
		
		
	Manage Inventory Levels
		Endpoint: PATCH /api/items/{key}/inventory
		Path Variable: key - Item identifier
		Request Body:		
		{
		  "quantity": "int"
		}
		Authorization: Requires ROLE_ADMIN.
		
3. User Responsibilities
	View Available Grocery Items
		Endpoint: GET /api/user
		Query Parameters: filterText (optional)
		Authorization: Requires ROLE_USER.

	Book Multiple Grocery Items in a Single Order
		Endpoint: POST /api/user
		Request Body:
		{
		  "items": [
			{
			  "itemId": "long",
			  "quantity": "int"
			}
		  ]
		}
		Authorization: Requires ROLE_USER.
		
		
Authentication and Authorization
	JWT Token: This API uses JWT tokens for secure authentication and authorization. Users need to log in using their credentials to receive a token, which must be included in the Authorization header for protected endpoints.
	
Database Configuration
	Database: PostgreSQL
	Database Name: qp_assessment
