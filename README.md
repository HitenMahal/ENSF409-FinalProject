# ENSF409-FinalProject

Group Members: 
Hiten Mahalwar  
Toshi Biswas  
Karim Mansour  
Alex Zhao  

# IMPORTANT NOTES
Our main file is HamperGUI.java, running that file will start the program
All .jar files should be in a "lib" folder in the working directory
Any Hamper OrderForm reciepts will be printed to a .txt file in the working directory
The program connects to database 'food_inventory' with username 'student' and password 'ensf'

# FOR RUNNING THE PROGRAM
To compile the code:
    javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/*.java
To run the program:
    java -cp .;lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.HamperGUI.java

# FOR TESTING
Please make sure that the program files are also compiled.
To compile the tests:
    javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar tests/edu/ucalgary/ensf409/*.java
To run the tests:
    java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.edu.ucalgary.ensf409.[TESTING_FILE_NAME]

