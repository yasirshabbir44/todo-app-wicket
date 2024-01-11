# Todo Task Management Application

## Overview

The Todo Application is a task management tool designed to help organize and prioritize the tasks efficiently in Project and assigned it each user.



## Features

- **Add Task:** Users can add new tasks with a title, description, due date, and priority with each Project
- **Mark as Completed:** Tasks can be marked as completed by checking the corresponding checkbox.
- **Delete Task:** Remove unwanted tasks from the list.
- **Priority Categories:** Tasks are categorized based on priority (High, Medium, Low).
- **Due Dates:** Set due dates for tasks to prioritize your workload.
- **Responsive UI:** The application provides a responsive and user-friendly interface.

## Technologies Used

- **Java 17:** The latest version of Java for application development.
- **Apache Wicket:** A lightweight and component-based Java web framework.
- **Lombok:** A library to reduce boilerplate code in Java, making code more concise and readable.
- **Postgresql:** DBMS for data storage
- **Hibernate:** Database layer to persist data in efficent way
- **Guice:** Guice used for Dependency injection make it easy and clean code
- **Bootstrap:** Bootstrap framework use  to enhance the styling and layout of Application

## Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yasirshabbir44/todo-app-wicket
   cd todp-app-wicket
2. **Build and Run:**
   ```bash
   mvn clean install
   mvn jetty:run

**Access the Application:**

Please make sure you have a database running locally with the following config:

- Host: localhost
- Port: 5432
- Database Name: tests
- User: sylvain
- Password:

Open your web browser and navigate to http://localhost:8080.

<img alt="Main Page UI" height="800" src="./doc/main-page.png" width="1000"/>


**Challenges:**
Implementing first Apache Wicket project can indeed be challenging, especially if you find limited online resources. Here are some common challenges that developers might face during the implementation of an Apache Wicket project:

- Sparse Documentation and Tutorials : Limited availability of comprehensive documentation or tutorials can make it difficult for newcomers to grasp the concepts and best practices of Apache Wicket.
- Community Support : A smaller community might mean fewer forums, discussion groups, or Stack Overflow threads where you can seek help when encountering issues.
- Learning Curve: Wicket has a unique programming model, and developers coming from other frameworks might find it challenging to adapt to the component-based architecture and page-oriented approach.
- Integration with Other Technologies : Integrating Apache Wicket with other libraries or frameworks may pose challenges, especially if there are limited examples or guidelines available for specific integrations.
- Customization Complexity : Customizing the look and feel of your Wicket application might be more challenging if there are limited resources on how to modify or extend existing components.


### Strengths
- Implemented Single page to perform all operation during task management to avoid multiple clicks or navigating in to mutliple screens.
- Added Priority of each Task so that get to know which one is higher priority.
- Add User Image along with name with Each task panel so that It will more readable.
- Main page showing sliders of images of how it works.
- On Single click of Done move into Completed and vice versa.
- Usage of AJAX using Java
- Showing more information on UI Panel of task so that it's more informative like dueDate, createdDate, assiged User information, building name etc.

### Improvements

- Pagination can also be introduced to avoid infinite-scroll.
- Can also perform CRUD operation on User(Person) and Project(Building).
- Can be sort Task by User, Priority adn task status wise.
- Alert or success dialog or confirmation can be added on time of Task Deletion.
- Can be edit Task also.
- Drag and Drop of Panel

### Inspiration or helping material
- https://wicket.apache.org/
- https://stackoverflow.com/
- https://laborasyon.com/iframe/miendo 
- https://chat.openai.com/
- https://www.atlassian.com/software/jira