# Kanban Board Project

## Overview
This project is a Kanban board application that helps users manage tasks and boards. It allows users to create boards, tasks, and subtasks, and transition it between different statuses like *Inactive*, *To do*, *In progress* and *Done*.

## Features
- **Boards**: Create and manage multiple boards for different projects or teams.
- **Tasks**: Add tasks to boards, each task can have a unique description and status.
- **Subtasks**: Break down tasks into smaller, manageable subtasks.
- **Transitions**: Move boards and tasks between statuses, such as:
  - Inactive
  - To do
  - In progress
  - Done

## Getting Started
1. Clone the repository
   ```
   git clone <repository-url>
   ```
2. Install dependencies
   ```
   mvn clean install
   ```
3. Run the application

   3.1. Make a doker image
   ```
   docker build -t "tag name" "path to docker file"
   ```
   3.2. Check the images to get the id
   ```
   docker images
   ```
   3.2. Create the container
   ```
   docker run -p 8080:8080 --name "container name" "image id"
   ```

## Technologies Used
- Java (Spring Boot)
- H2 Database

## Contribution
Feel free to submit issues and pull requests!
