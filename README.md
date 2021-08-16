# availity coding exercises
#### 1.Tell me about your proudest professional achievement.  It can also be a personal or school project. 
Back in 2015 when I was working on the ECARS application(developed in 2010) for Enterprise Holdings(Enterprise Rental car) company. There is an issue came up with User/Rental car Branch Personnel capturing the credit card numbers on a Notes Section in the ECARS application. The Notes section is a free flow text and primarly used for adding any important notes about the rental transaction but it is misutilized and there are millions of rows with credit card numbers and the PCI Compliance audited the Notes Table. 
* Task: Replace the credit card numbers with mask as we can't delete that data the notes as it contains some important information about rental transation and  prevent it from happening in future.

As an Engineeer working on the project firstly I have worked on gathering the different credit card patterns(for eg. American express always start with 34 or 35 and 15 digit long) from Payment Services team and later I worked on developing a sql script that just replaces credit card numbers with mask without requiring to delete the important notes. Also made the enhancement in the code to validate the notes before save to the DB.

#### 2. Tell me about something you have read recently that you would recommend and why. (Can be a Github Repo, Article, Blog, Book, etc) 
Postman Collection Runner - https://learning.postman.com/docs/running-collections/intro-to-collection-runs/ I started using this feature recently for my REST services testing and it does saves lot of time when doing the manual testing and if utilized with https://github.com/sivcan/ResponseToFile-Postman it automatically generates the report with all of the tests run and reposnses locally which can be used as an evidence for attaching to JIRA's or some.

#### 3. How would you explain to your grandmother what Availity does?
* Availity develops software that makes it easier for Hospitals or Providers connect with their payers or Inusrance Company to get the answers they need to focus on patient care.
#### 4. Coding exercise 1: 
You are tasked to write a checker that validates the parentheses of a LISP code.  Write a program (in Java or JavaScript) which takes in a string as an input and returns true if all the parentheses in the string are properly closed and nested.

* Solution: [Lisp Code Validator](https://github.com/sbandab87/availity-coding-excercises/blob/main/src/main/java/LispExpressionValidator.java)

#### 5. Coding exercise 2:  
Availity receives enrollment files from various benefits management and enrollment solutions (I.e. HR platforms, payroll platforms).  Most of these files are typically in EDI format.  However, there are some files in CSV format.  For the files in CSV format, write a program in a language that makes sense to you that will read the content of the file and separate enrollees by insurance company in its own file. Additionally, sort the contents of each file by last and first name (ascending).  Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included. The following data points are included in the file:
	•	User Id (string)
	•	First Name (string) 
	•	Last Name (string)
	•	Version (integer)
	•	Insurance Company (string)
* Solution: [Enrollees By Insurance Company](https://github.com/sbandab87/availity-coding-excercises/blob/main/src/main/java/EnrolleesByInsCompGenerator.java)
