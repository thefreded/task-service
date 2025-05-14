# Quarkus Task Service

A simple RESTful API service built with Quarkus that provides basic CRUD operations for task management.

## Environment Variables

This service requires the following environment variables:

| Variable | Description |
|----------|-------------|
| `KEYCLOAK_URL` | Base URL of your Keycloak server |
| `KEYCLOAK_REALM` | Keycloak realm name |
| `KEYCLOAK_CLIENT_ID` | Client ID for your application in Keycloak |
| `KEYCLOAK_CLIENT_SECRET` | Client secret for your application in Keycloak |
| `KEYCLOAK_SCOPE` | OAuth scope for the client |
| `KEYCLOAK_AUDIENCE` | Target audience for the client |
| `DB_TYPE` | Database type (only postgresql support) |
| `DB_URL` | JDBC connection URL for your database |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |

## Quick Start

1. Set the environment variables listed above
2. Build the application: `./mvnw clean package`
3. Run the application: `./mvnw quarkus:dev`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | List all tasks |
| GET | `/api/tasks/{id}` | Get a specific task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update an existing task |
| DELETE | `/api/tasks/{id}` | Delete a task |


All endpoints require authentication via Keycloak.