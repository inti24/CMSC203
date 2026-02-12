package gpa;
import java.io.*; //import io package for file input and output
import java.util.Scanner; // used for file data input
public class GradeCalculator {

	/*
	Project 1
	Class: CMSC203
	Instructor: Ahmed Tarek
	Due: 02/11/2026
	platform/compiler Eclipse java
	I pledge that i have completed the programming assignment independently. I have not copied the code from a student or any source. 
	I have not given my code to any student.
	Jovani Aguirre-Ramos
	*/
	
	public static void main(String[] args) throws java.io.IOException { //throws clause for File I/O checked exceptions
		
		int cat = 3;//default # categories 
		String catName1 = "Projects";//default name for category 1
		String catName2 = "Quizzes";//default name for category 2
		String catName3 = "Exams";//default name for category 3
		String catName4 = "NA";//default name for category 4
		String catName5 = "NA";//default name for category 5
		
		int totalWeight = 100;
		int woc1 = 40;//default weight for category 1
		int woc2 = 30;//default weight for category 2
		int woc3 = 30;//default weight for category 3
		int woc4 = 0;//default weight for category 4
		int woc5 = 0;//default weight for category 5
		
		double avgCatGrade1 =0;//calculated average  category grade
		double avgCatGrade2 =0;//calculated average  category grade
		double avgCatGrade3 =0;//calculated average  category grade
		double avgCatGrade4 =0;//calculated average  category grade
		double avgCatGrade5 =0;//calculated average  category grade
		
		String stuCatName1 ="N/A";//category from student text file 
		String stuCatName2="N/A";//category from student text file
		String stuCatName3="N/A";//category from student text file
		String stuCatName4="N/A";//category from student text file
		String stuCatName5="N/A";//category from student text file
		
		int numberAssignment;
		
		double assignmentGrade;
		
		int stuLineCount=0;
		int stuCatCount=0;
		boolean weightedGrade= false;
		double overAllScore = 0;
		String courseName= "default";
		String firstName= "default";
		String lastName ="default";
		String baseLetterGrade ="N/A";
		String finalLetterGrade ="N/A";
		boolean defaultConFig=false;
		
		
		
		try { //checking to see if the config file works and making categories and if it adds up to 100 total weighted
		File gradeConfig = new File("gradeconfig.txt"); // file is saved in C::\\Users\\gooey\\eclipse-workspace\\Project1
		Scanner gConfig = new Scanner(gradeConfig);
		
		
		while(gConfig.hasNextLine()) { //read all line in the config			
			
			courseName = gConfig.nextLine();//gets the course name 
			if(courseName.isEmpty())	
			throw new RuntimeException();// no course name go straight to catch section
				
			cat = gConfig.nextInt(); //update the amount of categories by reading the text file
			gConfig.nextLine();
			
			
		for(int i =0; i<cat;i++) {// when getting to categories we take the amount of cat from the text file and that is how many lines it will read after
			String line = gConfig.nextLine();
			
			Scanner lineChecker = new Scanner(line);
			
			
			switch(i) {//this will update the category name and the weight respectively 
			case 0:
				catName1=lineChecker.next();
				woc1=lineChecker.nextInt();
				break;
			case 1:
				catName2=lineChecker.next();
				woc2=lineChecker.nextInt();
				break;
			case 2:
				catName3=lineChecker.next();
				woc3=lineChecker.nextInt();
				break;
			case 3:
				catName4=lineChecker.next();
				woc4=lineChecker.nextInt();
				break;
			case 4:
				catName5=lineChecker.next();
				woc5=lineChecker.nextInt();
				break;
			}
			
		
		}
		
		}
		if(totalWeight != woc1+woc2+woc3+woc4+woc5) {//check to see if the total weight from each cat is equal to 100
			throw new RuntimeException();
		}
		
		gConfig.close();
		}catch(Exception fileNotWorking){//if the file has an error use default config
			System.out.println("invalid Configuration using default values");
		//fileNotWorking.printStackTrace();// help debug
		defaultConFig=true;
			cat = 3;//default # categories 
			catName1 = "Projects";//default name for category 1
			catName2 = "Quizzes";//default name for category 2
			catName3 = "Exams";//default name for category 3
			
			
			woc1 = 40;//default weight for category 1
			woc2 = 30;//default weight for category 2
			woc3 = 30;//default weight for category 3
			woc4 = 0;//default weight for category 4
			woc5 = 0;//default weight for category 5
			
		}
		
		
		try{//Reading Student information
		File studentGrade = new File("grades_input.txt"); //reading student information
		Scanner stuGrade = new Scanner(studentGrade);
				
		//count how many lines in student file 
		
			while(stuGrade.hasNextLine()) {
				stuGrade.nextLine();
				stuLineCount++;
			}
		
		stuCatCount = (stuLineCount-2)/3;
		//System.out.println(stuCatCount);
		Scanner stuGrade2 = new Scanner(studentGrade);
		
		while(stuGrade2.hasNextLine()) {//read all the lines 
			firstName = stuGrade2.nextLine();// gets the students first name
			lastName = stuGrade2.nextLine();// gets the students last name 
			//System.out.print("Student name: " + firstName +" "+ lastName);	
		
			for(int i =0; i<stuCatCount;i++) {// when getting to categories we take the amount of cat from the student text file and that is how many lines it will read after
				
				
				switch(i) {//this will update the category name and the weight respectively 
				case 0:
					stuCatName1 =stuGrade2.nextLine();
					if(stuCatName1.equals(catName1)||stuCatName1.equals(catName2)||stuCatName1.equals(catName3)||stuCatName1.equals(catName4)||stuCatName1.equals(catName5)) {
					numberAssignment = Integer.parseInt(stuGrade2.nextLine()); //update the amount of categories by reading the text file
					
					
					for(int j=0;j<numberAssignment;j++) {
						assignmentGrade =stuGrade2.nextDouble();
						avgCatGrade1 +=  assignmentGrade ; 
					}
					avgCatGrade1 = avgCatGrade1 /numberAssignment;
					//System.out.println(stuCatName1);
					//System.out.println(avgCatGrade1);
					stuGrade2.nextLine();
					assignmentGrade =0;
				
					break;
					}else {
						System.out.println("error category does not match");
						avgCatGrade1 = 0;
						break;
					}
				case 1:
					stuCatName2 =stuGrade2.nextLine();
					if(stuCatName2.equals(catName1)||stuCatName2.equals(catName2)||stuCatName2.equals(catName3)||stuCatName2.equals(catName4)||stuCatName2.equals(catName5)) {
					numberAssignment = Integer.parseInt(stuGrade2.nextLine()); //update the amount of categories by reading the text file
					
					
					for(int j=0;j<numberAssignment;j++) {
						assignmentGrade =stuGrade2.nextDouble();
						avgCatGrade2 +=  assignmentGrade ; 
					}
					avgCatGrade2 /= numberAssignment;
					//System.out.println(stuCatName2);
					//System.out.println(avgCatGrade2);
					stuGrade2.nextLine();
					assignmentGrade =0;
				
					break;
					}else {
						System.out.println("error category does not match");
						avgCatGrade2 = 0;
						break;
					}
				case 2:
					stuCatName3 =stuGrade2.nextLine();
					if(stuCatName3.equals(catName1)||stuCatName3.equals(catName2)||stuCatName3.equals(catName3)||stuCatName3.equals(catName4)||stuCatName3.equals(catName5)) {
					numberAssignment = Integer.parseInt(stuGrade2.nextLine()); //update the amount of categories by reading the text file
					
					
					for(int j=0;j<numberAssignment;j++) {
						assignmentGrade =stuGrade2.nextDouble();
						avgCatGrade3 +=  assignmentGrade ; 
					}
					avgCatGrade3 /= numberAssignment;
					//System.out.println(stuCatName3);
					//System.out.println(avgCatGrade3);
					stuGrade2.nextLine();
					assignmentGrade =0;
					break;
					}else {
						System.out.println("error category does not match");
						avgCatGrade3 = 0;
						break;
					}
				case 3:
					stuCatName4 =stuGrade2.nextLine();
					if(stuCatName4.equals(catName1)||stuCatName4.equals(catName2)||stuCatName4.equals(catName3)||stuCatName4.equals(catName4)||stuCatName4.equals(catName5)) {
					numberAssignment = Integer.parseInt(stuGrade2.nextLine()); //update the amount of categories by reading the text file
					
					for(int j=0;j<numberAssignment;j++) {
						assignmentGrade =stuGrade2.nextDouble();
						avgCatGrade4 +=  assignmentGrade ; 
					}
					avgCatGrade4 /= numberAssignment;
					//System.out.println(stuCatName4);
					//System.out.println(avgCatGrade4);
					stuGrade2.nextLine();
					assignmentGrade =0;
					break;
					}else {
						System.out.println("error category does not match");
						avgCatGrade4 = 0;
						break;
					}
				case 4:
					stuCatName5 =stuGrade2.nextLine();
					if(stuCatName5.equals(catName1)||stuCatName5.equals(catName2)||stuCatName5.equals(catName3)||stuCatName5.equals(catName4)||stuCatName5.equals(catName5)) {
					numberAssignment = Integer.parseInt(stuGrade2.nextLine()); //update the amount of categories by reading the text file
					
					
					for(int j=0;j<numberAssignment;j++) {
						assignmentGrade =stuGrade2.nextDouble();
						avgCatGrade5 +=  assignmentGrade ; 
					}
					avgCatGrade5 /= numberAssignment;
					//System.out.println(stuCatName5);
					//System.out.println(avgCatGrade5);
					stuGrade2.nextLine();
					assignmentGrade =0;
					break;
					}else {
						System.out.println("error category does not match");
						avgCatGrade5 = 0;
						break;
					}
					
					
					
				}
				
			
			}	
			
			}
		
			
			
			
			}catch(Exception invalidFile){
				//System.out.println("Missing or unreadable input file");
			}
		
		
		//Overall score
				overAllScore = (avgCatGrade1 *woc1 /100)+(avgCatGrade2 *woc2 /100)+(avgCatGrade3 *woc3 /100)+(avgCatGrade4 *woc4 /100)+(avgCatGrade5 *woc5 /100);
					//System.out.println(overAllScore);
		
		//Asking if +/- grading 
		
		boolean inputChecker =true;
		while(inputChecker) {
			System.out.println("Apply +/- grading? (Y/N):");
			Scanner specialGrade = new Scanner(System.in);
			String checker =specialGrade.next();
		switch(checker) {
		case "y":
			weightedGrade =true;
			inputChecker =false;
			break;
		case "Y":
			weightedGrade =true;
			inputChecker =false;
			break;
		case "n":
			weightedGrade =false;
			inputChecker =false;
			break;
		case "N":
			weightedGrade =false;
			inputChecker =false;
			break;
		default:
			System.out.println("Invalid input please try again.");
			inputChecker =true;
			
			break;
				
		}
		}
		
		//assigning letter grade 
		if(overAllScore>=90) {
			if(overAllScore>=97.0 && weightedGrade == true) {
				//System.out.println("A+");
				finalLetterGrade ="A+";
				baseLetterGrade ="A";
			}else if(overAllScore<=93 && weightedGrade == true) {
				//System.out.println("A-");
				finalLetterGrade ="A-";
				baseLetterGrade ="A";
			}if(weightedGrade == true){
				baseLetterGrade ="A";
				finalLetterGrade ="A";
			}else{
				baseLetterGrade ="A";
				//System.out.println("A");
			}
		}else if(overAllScore>=80) {
			if(overAllScore>=87.0 && weightedGrade == true) {
				//System.out.println("B+");
				finalLetterGrade ="B+";
				baseLetterGrade ="B";
			}else if(overAllScore<=83 && weightedGrade == true) {
				//System.out.println("B-");
				finalLetterGrade ="B-";
				baseLetterGrade ="B";
			}if(weightedGrade == true){
				baseLetterGrade ="B";
				finalLetterGrade ="B";
			}else{ 
				baseLetterGrade ="B";
				//System.out.println("B");
			}
		}else if(overAllScore>=70) {
			if(overAllScore>=77.0 && weightedGrade == true) {
				//System.out.println("C+");
				finalLetterGrade ="C+";
				baseLetterGrade ="C";
			}else if(overAllScore<=73 && weightedGrade == true) {
				//System.out.println("C-");
				finalLetterGrade ="C-";
				baseLetterGrade ="C";
			}if(weightedGrade == true){
				finalLetterGrade ="C";
				baseLetterGrade ="C";
			}else{
				//System.out.println("C");
				baseLetterGrade ="C";
			}
		}else if(overAllScore>=60) {
			if(overAllScore>=67.0 && weightedGrade == true) {
				//System.out.println("D+");
				finalLetterGrade ="D+";
				baseLetterGrade ="D";
			}else if(overAllScore<=63 && weightedGrade == true) {
				//System.out.println("D-");
				finalLetterGrade ="D-";
				baseLetterGrade ="D";
			}if(weightedGrade ==true){
				//System.out.println("D");
				finalLetterGrade ="D";
				baseLetterGrade ="D";
			}else {
				baseLetterGrade ="D";
			}
		}else if(overAllScore<60) {
			//System.out.println("F");
			baseLetterGrade ="F";
		}
		
		
		//Provide Summary in console
		System.out.println("Course: "+courseName);
		System.out.println("Student Name: " +firstName +" "+ lastName);
		//categories 
		System.out.println("Category Results: ");
		if(stuCatName1.equals(catName1)||stuCatName1.equals(catName2)||stuCatName1.equals(catName3)||stuCatName1.equals(catName4)||stuCatName1.equals(catName5)) {
			if(stuCatName1.equals(catName1)) {
				System.out.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName1.equals(catName2)) {
				System.out.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName1.equals(catName3)) {
				System.out.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName1.equals(catName4)) {
				System.out.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName1.equals(catName5)) {
				System.out.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName2.equals(catName1)||stuCatName2.equals(catName2)||stuCatName2.equals(catName3)||stuCatName2.equals(catName4)||stuCatName2.equals(catName5)) {
			if(stuCatName2.equals(catName1)) {
				System.out.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName2.equals(catName2)) {
				System.out.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName2.equals(catName3)) {
				System.out.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName2.equals(catName4)) {
				System.out.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName2.equals(catName5)) {
				System.out.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName3.equals(catName1)||stuCatName3.equals(catName2)||stuCatName3.equals(catName3)||stuCatName3.equals(catName4)||stuCatName3.equals(catName5)) {
			if(stuCatName3.equals(catName1)) {
				System.out.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName3.equals(catName2)) {
				System.out.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName3.equals(catName3)) {
				System.out.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName3.equals(catName4)) {
				System.out.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName3.equals(catName5)) {
				System.out.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName4.equals(catName1)||stuCatName4.equals(catName2)||stuCatName4.equals(catName3)||stuCatName4.equals(catName4)||stuCatName4.equals(catName5)) {
			if(stuCatName4.equals(catName1)) {
				System.out.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName4.equals(catName2)) {
				System.out.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName4.equals(catName3)) {
				System.out.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName4.equals(catName4)) {
				System.out.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName4.equals(catName5)) {
				System.out.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName5.equals(catName1)||stuCatName5.equals(catName2)||stuCatName5.equals(catName3)||stuCatName5.equals(catName4)||stuCatName5.equals(catName5)) {
			if(stuCatName1.equals(catName1)) {
				System.out.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName5.equals(catName2)) {
				System.out.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName5.equals(catName3)) {
				System.out.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName5.equals(catName4)) {
				System.out.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName5.equals(catName5)) {
				System.out.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		
		
		System.out.printf("Overall Numeric Average: %.2f\n",overAllScore);
		System.out.println("Base Letter Grade: "+baseLetterGrade);
		System.out.println("Final Letter Grade: "+finalLetterGrade);
		System.out.println("Summary written to grades_report.txt");
		System.out.println("Program complete. Goodbye!");
		if(defaultConFig==true) {
			System.out.println("Default Configuration was used");
			}
		
		
		
		//make a file
		PrintWriter summary = new PrintWriter("grades_report.txt");
		summary.println("Course: "+courseName);
		summary.println("Student Name: " +firstName +" "+ lastName);
		//categories 
		summary.println("Category Results: ");
		if(stuCatName1.equals(catName1)||stuCatName1.equals(catName2)||stuCatName1.equals(catName3)||stuCatName1.equals(catName4)||stuCatName1.equals(catName5)) {
			if(stuCatName1.equals(catName1)) {
				summary.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName1.equals(catName2)) {
				summary.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName1.equals(catName3)) {
				summary.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName1.equals(catName4)) {
				summary.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName1.equals(catName5)) {
				summary.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName2.equals(catName1)||stuCatName2.equals(catName2)||stuCatName2.equals(catName3)||stuCatName2.equals(catName4)||stuCatName2.equals(catName5)) {
			if(stuCatName2.equals(catName1)) {
				summary.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName2.equals(catName2)) {
				summary.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName2.equals(catName3)) {
				summary.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName2.equals(catName4)) {
				summary.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName2.equals(catName5)) {
				summary.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName3.equals(catName1)||stuCatName3.equals(catName2)||stuCatName3.equals(catName3)||stuCatName3.equals(catName4)||stuCatName3.equals(catName5)) {
			if(stuCatName3.equals(catName1)) {
				summary.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName3.equals(catName2)) {
				summary.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName3.equals(catName3)) {
				summary.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName3.equals(catName4)) {
				summary.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName3.equals(catName5)) {
				summary.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName4.equals(catName1)||stuCatName4.equals(catName2)||stuCatName4.equals(catName3)||stuCatName4.equals(catName4)||stuCatName4.equals(catName5)) {
			if(stuCatName4.equals(catName1)) {
				summary.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName4.equals(catName2)) {
				summary.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName4.equals(catName3)) {
				summary.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName4.equals(catName4)) {
				summary.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName4.equals(catName5)) {
				summary.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		if(stuCatName5.equals(catName1)||stuCatName5.equals(catName2)||stuCatName5.equals(catName3)||stuCatName5.equals(catName4)||stuCatName5.equals(catName5)) {
			if(stuCatName1.equals(catName1)) {
				summary.println(catName1 +"("+woc1+"%):average = " +avgCatGrade1);
			}else if(stuCatName5.equals(catName2)) {
				summary.println(catName2 +"("+woc2+"%):average = " +avgCatGrade2);
			}else if(stuCatName5.equals(catName3)) {
				summary.println(catName3 +"("+woc3+"%):average = " +avgCatGrade3);
			}else if(stuCatName5.equals(catName4)) {
				summary.println(catName4 +"("+woc4+"%):average = " +avgCatGrade4);
			}else if(stuCatName5.equals(catName5)) {
				summary.println(catName5 +"("+woc5+"%):average = " +avgCatGrade5);
			}
		}
		
		
		summary.printf("Overall Numeric Average: %.2f\n",overAllScore);
		summary.println("Base Letter Grade: "+baseLetterGrade);
		summary.println("Final Letter Grade: "+finalLetterGrade);
		summary.println("Summary written to grades_report.txt");
		summary.println("Program complete. Goodbye!");
		if(defaultConFig==true) {
		summary.println("Default Configuration was used");
		}
		summary.close();
		
		
		
		System.exit(0);

	}

}
