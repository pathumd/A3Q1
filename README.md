# COMP 3005 A - Assignment 3 Question 1
Author: Pathum Danthanarayana, 101181411<br>
Date: March 18, 2024

## Demonstration Video
YouTube link: https://youtu.be/j9sB9-wgKYI

## Database setup
### Creating the database
1. To create the ```StudentManagementSystem``` database, begin by opening pgAdmin4.
2. After connecting to the Postgres service (i.e. Postgres 16), right click on it (from the tree on the left panel), and hover over ```Create``` and click ```Database...```.
3. In the ```Database``` field, name the database ```StudentManagementSystem```, and click ```Save```.

### Creating the students table
1. To create the students table, right click on the newly created database from the tree, and click ```Query Tool```.
2. A new tab will open with a window to enter an SQL query. Copy and paste the following SQL query into the window, and click the play button to execute the query:

```
-- Create students TABLE
CREATE TABLE IF NOT EXISTS students (
	student_id SERIAL PRIMARY KEY,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	email TEXT UNIQUE NOT NULL,
	enrollment_date DATE
);
```

3. Once the students table is created, the initial data provided for the assignment can be inserted into the table. Clear the query window (remove the query pasted from step 2), copy and paste the query below, and click the play button to execute the query:

```
-- Insert initial data
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');
```

4. The database has now been successfully set up.

## Running the program
Before running the program, navigate to ```Main.java``` and on line 18,
change the specified username, password, and port to your username and password for PostgreSQL,
as well as the port that PostgreSQL currently running on.
(Note: To find which port PostgreSQL is running on, return to pgAdmin4, right-click on your current PostgreSQL version (i.e. PostgreSQL 16),
and click ```Properties...```. Under the ```Connection``` section, the port number should be listed beside ```Port```)

To run the application, navigate to ```Main.java```, and run the main method found within 
this class.
Note: If you would like only a certain operation to be performed, the rest of the method invocations
can be uncommented. By default, running the Main class will do the following:
- Invoke ```getAllStudents()``` to retrieve all students in the students table
- Invoke ```addStudent()``` followed by ```getAllStudents()``` to add a new student to the table and retrieve all students from the table to see the change
- Invoke ```updateStudentEmail()``` followed by ```getAllStudents()``` to update a particular student's email and retrieve all students from the table to see the change
- Invoke ```deleteStudent()``` followed by ```getAllStudents()``` to delete a particular student from the table and retrieve all students from the table to see the change

### Main.java
The Main class is the class used to run the application. It creates an instance of the StudentDBConnector class
and invokes each of the 4 methods that were asked to be implemented for the assignment.

### StudentDBConnector.java
The StudentDBConnector class is responsible for implementing the connection to the StudentManagementSystem database, 
as well as the 4 requested methods.

#### connectToDB():
- Attempts to establish a connection to the StudentManagementSystem using the database's name, the port number that PostgreSQL is
running on, as well as the credentials (username and password) for PostgreSQL.
A message will be printed if the connection was successful or unsuccessful.

#### getAllStudents():
- Returns the information of each student in the students table.
- To do this, a simple ```SELECT * FROM students``` query is used to retrieve all students in the table.
- To print each row from the returned result, the ResultSet of the executed query/statement is used to iterate 
through each tuple until the ResultSet's cursor does not have a next tuple (meaning no more tuples exist in the ResultSet).
- While iterating through each tuple in the ResultSet, each of its attributes are printed.

#### addStudent():
- Adds a new student into the students table, based on the provided first name, last name, email, and enrollment date of the student.
- To do this, a prepared statement is used to add placeholders for the values being inserted into the table (since the provided arguments can't be directly added into SQL string):
```
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)
```
- After the prepared statement is defined, the specified arguments are added to the statement by invoking the appropriate
set method (i.e. setString(), setDate()) on the statement object and passing the parameter index and value.

#### updateStudentEmail():
- Updates the email of a student from the students table, based on the provided student ID and email.
- Similarly to ```addStudent()```, a prepared statement is used to pass the specified arguments (new email and student ID of the student) into the statement:
```
UPDATE students SET email=? WHERE student_id=?
```
- Here, only the tuple from the students table matching the specified student ID will have their email set to the specified email.

#### deleteStudent():
- Deletes a student from the students table, based on the provided student ID.
- Since the specified student ID needs to be included in the statement, a prepared statement is used:
```
DELETE FROM students WHERE student_id=?
```
- Here, only the tuple from the students table matching the specified student ID will be deleted.
