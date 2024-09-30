# CS 5010 Semester Project

This repo represents the coursework for CS 5010!

**Name:** Rajiv Ragavan

**Email:** ragavan.r@northeastern.edu

**Preferred Name:** Rajiv



### About/Overview

Give a general overview of the problem and how your program solve the problem



### List of Features

The current features of my program include:

-Reading a text file into the model.
-Registering a new patient into the model. If the patients do not have a room yet they are placed in the waiting room.
-Registering a new clinical staff member

### How to Run

Describe how to run your program from the JAR file. Describe what arguments are needed (if any) and what they mean.



### How to Use the Program

Provide instructions on how to use the functionality in your program. If it is interactive, describe how to interact with your program. Pay particular attention to the parts that are not part of the example runs that you provide.



### Example Runs

List any example runs that you have in res/ directory and provide a description of what each example represents or does. Make sure that your example runs are provided as *plain text files*.
- I have an example run testing a clinic file where I inputted a text file which was then read into my driver class.
- Each requirement is subtitled and demonstrated with important and relevant attributes demonstrating how and why each method of mine succesffuly executed in accordance with the requirements.
- The final requirements show that the structure of my model is sound as it accurately displays the location of each member of the clinic in their respective rooms alongside their assigned clinical staff members.


### Design/Model Changes

Document what changes you have made from earlier designs. Why did you make those changes? Keep an on-going list using some form of versioning so it is clear when these changes occurred.
v1
- To start with I created an interface called Staff which is inherited by Non Clinical Staff and Clinical Staff along with enumerations for EducationLevel, CprLevel and RoomType.
- I made a classes for clinical staff, non clinical staff, patient, room and clinic. Along with these I have basic attributes and methods for each of these classes.
v1.1
-


### Assumptions

List any assumptions that you made during program development and implementations. Be sure that these do not conflict with the requirements of the project.
- I assumed that the lowest number a room can be assigned is 1 and that it is not posible for a room to have a negative number assigned to it.


### Limitations

What limitations exist in your program. This should include any requirements that were *not* implemented or were not working correctly (including something that might work some of the time).
- Currently I do not have functionality to add a new room to the clinic.
- I do not have any methods that might describe the beahvior of non clinical staff members.
- I do not have room number as an attribute of the Room object itself.


### Citations

Be sure to cite your sources. A good guideline is if you take more than three lines of code from some source, you must include the information on where it came from. Citations should use proper [IEEE citation guidelines](https://ieee-dataport.org/sites/default/files/analysis/27/IEEE Citation Guidelines.pdf) and should include references (websites, papers, books, or other) for ***any site that you used to research a solution***. For websites, this includes name of website, title of the article, the url, and the date of retrieval**.** Citations should also include a qualitative description of what you used, and what you changed/contributed.



