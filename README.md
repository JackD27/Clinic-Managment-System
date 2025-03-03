# CS 5010 Semester Project

This repo represents the coursework for CS 5010!

**Name:** Jackson Davis

**Email:** davis.jackso@northeastern.edu

**Preferred Name:** Jackson

### About/Overview

Give a general overview of the problem and how your program solve the problem
A Clinic Management System it seems life.
Add a patient, add a staff member, assign staff to a patient,
remove a patient, remove a staff member, and list all patients in a room etc.

### List of Features

Full UI capabilities in the view/GUI, including:
- Ability to add a new Patient and Staff member.
- Ability to see patients in specific rooms. With a layout of the clinic.
- Remove Patients and Staff members.
- See the staff members that are assigned to a specific patient.
- Add visit records to a patient.

### How to Run

Download milestone-4-jackson-davis-solo.jar and clinicfile.txt

Load the clinicfile.txt file into the program by clicking "File" and 
"Load Clinic File" button.

Then you can use the program as you wish.

For my argument in cmd line, I used the following:
```
java -jar milestone-4-jackson-davis-solo.jar
```
### How to Use the Program

The UI is pretty solid, just follow the instructions in GUI.

### Example Runs

I logged my example runs in the /res folder. I have a few examples of adding patients and staff members, and then assigning staff to patients.

[NEW] Added runs for Miles-tone 3 under run3_logs, you can see that the command to make a map was made.
See the command that was issued when seeing the list of patients within the past 365 days and longer than 365 days.

### Design/Model Changes
#### Milestone 2
10/16/2024
- I changed a lot, pretty much redid the whole program. Dumbed it down a bit, and made it more simple.
- I was trying to do too much before. I think this is a better approach, and easier to add things.

10/19/2024
- I added a few more methods to the Clinic class, and added a few more methods to the Staff and Patient classes.
- The patient now has a list of visit records.
- You can assign records to a patient, and then see the records for that patient.

11/12/2024
- Changed the way the program runs.
- Moved everything to handle MVC better.
- Changed some test cases.
#### Milestone 3
11/12/2024
- Added new interfaces for the program to add more methods to the Milestone 3 requirements.

11/14/2024
- Added all the new command excepts making a map of the clinic.

11/15/2024
- Added the ability to make a map of the clinic.

#### Milestone 4
- Converted the program to use the MVC design pattern with the view being a GUI.
- User is able to load the clinicfile.txt file to see the whole clinic.
- Many different types of functionalities added to the GUI. 
Shown under the "List of Features" section.
- No new design changes were made. Only some methods were added to the controller
and of course the view was added with the GUI. The GUI came out the way I wanted it too.

### Assumptions

Swing is used for the GUI, I would have liked to try out JavaFX.

### Limitations
#### Milestone 1:
- The program is limited to the console, so there is no GUI.
- Error handling is limited to the console
- There needs to be at least one patient and one staff member in the clinic at all times, and one room at all times.
- Also figure out how not get an error when the Clinic doesn't have a name.
- ~~Error handling isn't perfect, so there are some edge cases that may not be handled. Like entering a string when a number is expected.
  This will log an error, but the program will continue to run. It's also in the log file.~~

#### Milestone 2:
- The program is limited to the console, so there is no GUI.
- ~~You must type the name of the patient or staff member exactly as it is in the system. To grab the patient or staff member, I am using the name as the key.~~ Most options should show the list of patients or staff members.
  Then just type the number of the patient or staff member you want to select.
- ~~Patient getting added to a room is not working correctly. The room does get set to the patient, but the room is not adding the patient to the list of patients in the room. Only when it's a waiting room.~~
#### Milestone 3:
- The program is limited to the console, so there is no GUI.
- Can't seem to find other limitations at the moment.
- Probably making the command take the System.in input for the requirements and use Appendable and Readable.
- Add more test cases.
#### Milestone 4:
- Some dialogs close when saving or applying changes.
- The GUI is not perfect, but it works.

### Citations

GeeksforGeeks. (2024, October 11). MVC design pattern. https://www.geeksforgeeks.org/mvc-design-pattern/

“Java Platform SE 8.” Oracle.com, 2024, https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
