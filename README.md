# Clinic Management System

**Name:** Rajiv Ragavan

### About/Overview

- The goal is to create a model of a Clinic with different rooms, patients, and staff members and implement the functionality that involves registering and maintaining info on patients and employees while ensuring that there are not conflicts with rooms or patient to staff assignments.
- My program solves this problem by conducting all the functions of the clinic through the main Clinic class itself while calling on Room, Patient and Staff classes to access unique attributes and functions of these classes.
- The user interacts with the model through the view which sends information to the controller to perform actions on the model. Once the actions are completed in the model, the controller relays the visual representation of the changes made back to the user.

### List of Features

The current features of my program include:

- Reading a text file into the model.
- Registering a new patient into the model. If the patients do not have a room yet they are placed in the waiting room
- Registering a new clinical staff member
- Sending a patient home while retaining their information within the Clinic system. Ensuring that their departure is confirmed by a ClinicalStaff member
- The ability to deactiate a clinical staff member
- Assigning a patient to a room which removes them from the room they were previously assigned to. This also allows for waiting rooms to accomodate more than one person per room.
- Assign as many clinical staff members to any patient as needed.
- Return a String based visual representation of a room, its residents and the clinical staff members assigned to those residents.
- Display a seating  chart which concisely displays each room and each person in their respective rooms.
- The ability to find a room based on a given room number.
- Adding a patient visit record to the model
- Finding a patient within the model by name.
- Viewing different rooms and areas in the model which reflect patient information including their most recent visit.
- Displaying patient info.
- Listing ClinicalStaff members with Patients that are assigned to them.
- Generating a map of the clinic which is stored in 'res' once called with its respective command.
- Deactivating a selected staff member.
- Display Patients Who Haven't Visited the Clinic in More Than a year.
- Display patients who visited teh clinic at least twice in the past year.
- Unassign a ClinicalStaff member from a patient
- List clinicalstaff members and the number of patients that they have ever been assigned.
- A controller which can perform the tasks listed above in an easy to access menu.
- The ability to clear all records from the model.
- A view which gives the user a visual representation of the clinic and allows them to perform the actions of the controller through the view.
- Clickable buttons for visual and practical ease for patient, room and staff member selection.

### Changes made to refactor model for text based controller
I didn't have to change my model much to refactor it to the text-based controller as it was already decoupled from the controller.
The controller's command classes manage the work of calling the methods on the clinic object from the model methods. The Controller
just prints the retrieved values after this is done. In order to test the text-based controller I refactored the model. to do this, I inherited
from the same clinic interface and then made the mock's appendable record the controller's ability to reach each command in the model.

### How to Run

Describe how to run your program from the JAR file. Describe what arguments are needed (if any) and what they mean.
- To run my program from a .jar file first make sure the .jar file is downloaded alongside clinicfile.txt (if you choose to use the text based controller).
- Open a command prompt
- Change diretory to where the repository is located.
- Use the command "java -jar res/Milestone4.jar" for the view based controller or "java -jar res/Milestone4.jar res/clinicfile.txt" for the text based controller where clinicfile.txt is the filepath to wherever clinicfile is located on your computer.
- The only argument needed to accomplish this is clinicfile.txt.


### How to Use the Program

Provide instructions on how to use the functionality in your program. If it is interactive, describe how to interact with your program. Pay particular attention to the parts that are not part of the example runs that you provide.
- If the user passes in a clinic text file they will be able to enter user input interactively to work with the clinic model. Either through text-based commands or by mouse interactions with the view-based design.
- For the text-based controller, the console will prompt the user with possible commands and request necessary input from the user in order to accomplish the numbered tasks on the menu.
- The user may quit to the menu after having selected a command with "q" at any time if they wish to do so.
- You may quit out of the proram by pressing "q" when prompted for a menu option.
- For the view based controller, a window will open displaying a welcome screen with an intreactable menus in the top left to perform actions such as loading the clinic, clearing its records, or quitting the program.
- Once the clinic is loaded, a menu of features will become available for the user to then interact with the clinic model directly through the view.
- The design is intuitive and will prompt for user input when necessary.


### Example Runs

List any example runs that you have in res/ directory and provide a description of what each example represents or does. Make sure that your example runs are provided as *plain text files*.
- I have an example run testing a clinic file where I inputted a text file which was then read into my driver class. The goal of this class was to pass in a clinic file.
- My test run goes through each command that the controller is capable of operating to demonstrate its function. 
- My test run shows that my example runs correctly when passed in information and exists the program once completed through a quit command.
- My test run now shows this functionality with newly implemented commands to the controller.
- My test run for the view based design is a pdf document located within res titled Example Run.pdf which shows all the features of the view based design and how they are reflected in the view.


### Design/Model Changes

Document what changes you have made from earlier designs. Why did you make those changes? Keep an on-going list using some form of versioning so it is clear when these changes occurred.
v1
- To start with I created an interface called Staff which is inherited by Non Clinical Staff and Clinical Staff along with enumerations for EducationLevel, CprLevel and RoomType.
- I made a classes for clinical staff, non clinical staff, patient, room and clinic. Along with these, I have basic attributes and basic methods for each of these classes.
v1.1
- Added more methods to the classes. The structure of my model seems to operate mainly from Clinic and calls methods stored within the other classes to carry out the major clinic functions of the model.
- Started adding functions like addANewPatient() to Clinic as well as send home. With this I need to add isActive flags on Patient and Staff entities so that their information can be retained within the model.
v1.3
- Added the readFile() method to the Clinic class. This is a large method that uses 3 helper methods each with functions for reading each patient, room and staff file into the model as well as adding to the newly created ArrayList to store these objects in a location where its easy for Clinic to carry out its other duties such as manipulating the activity of patients and staff members within the clinic.
v1.4
- Added ArrayList of ClinicalStaff objects to patient and ArrayList of Patient objects to room. Room needs to maintain a list of everyone who stays within the room and leaves the room when they are moved to accomodate for when a room can evenutally hold more than 1 person at a time. In addition WaitingRooms must store mutliple Patient objects as they are capable of keeping multiple patients.
- I kept a list of allocated staff members for a Patient because they can have multiple clinical staff members assigned to them. Deactivation of a Staff member would simply mean removing them from that list and deactivation of a Patient would mean emptying the list entirely.
v2.0
- Added a VisitRecord class and interface which kept track of each of any particular patient's visit to the clinic.
v2.1
- Added a method to ClinicalStaff which displayed an appropriate title to correspond with  the staff member's respective job title.
- Added toString method for patient so that their information as well as visit history could be displayed.
v2.2
- Added a findPatient method to the main clinic class which found a patient within the clinic's system by name.
v2.3
- Added a controller to the clinic so that the main driver could call commands with ease without having to directly access the file with commands and passed control to the controller.
- Added a series of commands to the controller which encapsulate many of the major functions of the clinic model.
v3.0
- Added a new controller which inherits from the old controller to display an additional series of commands to interact with the clinic.
v3.1
- Added a new ClinicClass with a new interface to implement new features to the clinic to facilitate the new controller commands that are being implemented.
- Implemented deactivation of any kind of staff member.
- Lets the user unassign a selected staff member from a patient.
v3.2
- Added commands to list staff Clinical staff members who have any patients assigned to them along with information about the patients that are assigned to them.
- Implemented a command to show patients whose last visit was more than a year ago.
- Implemented a command to show patients who visited the clinic two times or more times in the past year.
v3.3
- Added a command to generate a visual representation of clinic map as a buffered image.
v3.4
- Added a command to list clinical staff members along with the number of patients that they have ever been assigned.
v4.0
- Added necessary reset() command to the model which allows for clearing of all of the records stored in the model.
- Added a new controller which inherits from controller v3.0 to implement a command design pattern for the functions of the view specifically.
v4.1
- Created the a MasterView which served as a JFrame which housed a space for JPanels that would represent the AboutPage as well as the view of the clinic.
- The Master view has function to switch JPanel when showing a different parts of the view. Added rollback design to demonstrate transfer of control to controller once user input is detected.
v4.2
- Created the ClinicLayoutPage which is the visual representatioon of the view provided as a JPanel that is stored within the MasterView JFrame and accessed by MasterView via the switchPanel method within the masterview.
v4.3
- Added command design pattern for the controller so that the handling of the controllers functions is isolated in seperate single purpose classes.
v4.4
- Implemented the duties of each command class.


### Assumptions

List any assumptions that you made during program development and implementations. Be sure that these do not conflict with the requirements of the project.
- I assumed that the lowest number a room can be assigned is 1 and that it is not posible for a room to have a negative room number assigned to it.
- If a staff member is deactivated I assume that none of their attributes change with the deactivation such as jobTitle or NPI level/CprLevel.
- As of right now I assume that there is no maximum capacity for the clinic or a limit to how many clinical staff members can be assigned to a patient.
- I assume that a patient's temperature can be anything except negative.
- I assume that the patient can have any possible chief complaint and still be admitted to the hospital.
- I assume that there are no restrictions on what constitutes a patient or staff member's name other than that they are Strings.
- I assume that a waiting room can have an unlimited number of people at any given time.
- I assume that alphabetical order is not a concern when presenting patients within a room.


### Limitations

What limitations exist in your program. This should include any requirements that were *not* implemented or were not working correctly (including something that might work some of the time).
- Currently I do not have functionality to add a new room to the clinic.
- I do not have any methods that might describe the beahvior of non clinical staff members.
- I do not have room number as an attribute of the Room object itself.
- If there are too many patients in a respective waiting room my map will not cleanly reflect this do to the limitations provided by the size of my output and the font size of the text.
- Similar to the last point, my view will not cleanly reflect too many patients in a given waiting room if an excess are to be added. 


### Citations

Be sure to cite your sources. A good guideline is if you take more than three lines of code from some source, you must include the information on where it came from. Citations should use proper [IEEE citation guidelines](https://ieee-dataport.org/sites/default/files/analysis/27/IEEE Citation Guidelines.pdf) and should include references (websites, papers, books, or other) for ***any site that you used to research a solution***. For websites, this includes name of website, title of the article, the url, and the date of retrieval**.** Citations should also include a qualitative description of what you used, and what you changed/contributed.

Geeks for Geeks, “java.time.LocalDateTime Class in Java,” geeksforgeeks.org, para. May 26, 2021. [Online]
Available: https://www.geeksforgeeks.org/java-time-localdatetime-class-in-java/. [Accessed: Oct. 19, 2024]

baeldung, “Guide to DateTimeFormatter,” baeldung.com, para. Jan. 8,
2024. [Online]. Available: https://www.baeldung.com/java-datetimeformatter. [Accessed Oct. 19, 2024]
