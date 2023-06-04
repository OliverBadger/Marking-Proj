package com.company;

import java.util.Locale;
import java.util.Scanner;
import java.io.*;

public class student {
    //CHANGE THIS TO READING FROM A TEXT FILE WHEN GET CHANCE
    private String pathName = "C:\\Users\\Oliver\\Desktop\\Projects\\TeacherProjects\\src\\com\\company\\studentCriteria";
    private String[] ygOptions = {"Level 2","Level 3-Year 1", "Level 3-Year 2","Access", "Level 4"};
    private String[] goDegree = {"Fail","Marginal Fail", "3rd","2:2", "2:1", "1st"};
    private String[] gradeOptions = {"Fail","Pass", "Merit","Distinction"};
    private String[] strengthQuestions = {"Spelling and Grammar","Organisation, Structure and Standard of Presentation (includes references)",
            "Range and Professionalism of Sources","Communication Skills with Accuracy and Understanding", "Exploration and insight",
            "Inspirational, Creativity and Innovation", "Contextual evidence, Concepts, Theories and other relevant information applied",
            "Critical Analysis", "Innovation, Decision-making and Self Reflection"};
    public int[] strengthAnswers = new int[9];
    public String[] strengthWords = {"not submitted", "very unsatisfactory", "unsatisfactory", "adequate but weak",
    "satisfactory", "good to very good", "excellent", "outstanding", "exemplary"};
    private int overall, maxScore, percentage;

    public String yearGroup;
    public String name;
    public String grade;
    public String feedback;

    Scanner in = new Scanner(System.in);

    public void yearGroup(){
        System.out.println("\r\nPlease Select a Year Group");

        for (int i=0;i<ygOptions.length;i++) {
            int x=i+1;
            System.out.println(x+": "+ ygOptions[i]);
        }
        switch (in.nextInt()) {
            case 1: yearGroup = ygOptions[0];break;
            case 2: yearGroup = ygOptions[1];break;
            case 3: yearGroup = ygOptions[2];break;
            case 4: yearGroup = ygOptions[3];break;
            case 5: yearGroup = ygOptions[4];break;
            default:System.out.println("Unexpected Number Pressed!");
            yearGroup();
        }
    }

    public void grade(){
        //If statement to see if it is a degree marking
        if (yearGroup==ygOptions[4]) {
            if (percentage < 30) {
                grade = goDegree[0];
            } else if (percentage < 40) {
                grade = goDegree[1];
            } else if (percentage < 50) {
                grade = goDegree[2];
            } else if (percentage < 60) {
                grade = goDegree[3];
            } else if (percentage < 70) {
                grade = goDegree[4];
            } else {
                grade = goDegree[5];
            }
        }
        else {
            for (int i=0;i<gradeOptions.length;i++) {
                int x=i+1;
                System.out.println(x+": "+ gradeOptions[i]);
            }
            switch (in.nextInt()) {
                case 1: grade = gradeOptions[0];break;
                case 2: grade = gradeOptions[1];break;
                case 3: grade = gradeOptions[2];break;
                case 4: grade = gradeOptions[3];break;
                default: System.out.println("Unexpected Number Pressed!");
            }
        }
    }

    public void strengths(){
        subject sub = new subject();
        int totalForTask = 0;
        String taskStrings[] = sub.degreeSelector(yearGroup, ygOptions);

        //if it is degree level then it marks this criteria
        if (yearGroup == "Level 4"){
            System.out.println("\r\n\r\nSelect a Number From 1 - 10 to grade their work for the following criteria");
            for (int o = 0; o < taskStrings.length; o++) {
                System.out.println("Task " + (o+1) + ": "+ taskStrings[o]);
                for (int i = 0; i < strengthQuestions.length; i++) {
                    int x = i + 1;
                    System.out.println(x + ": " + strengthQuestions[i]);
                    int input = in.nextInt();
                    if (input > 10 || input < 0) {
                        System.out.println("Answer with a number within the current range");
                        i--;
                    } else {
                        strengthAnswers[i] = (input);
                    }
                }
                for(int i = 0; i < strengthAnswers.length; i++){
                    totalForTask = totalForTask + strengthAnswers[i];
                }
                for (int i = 0; i < strengthQuestions.length; i++) {
                    int x = i + 1;
                    System.out.println("The " + strengthQuestions[i].toLowerCase(Locale.ROOT) + " is " + strengthWords[(strengthAnswers[i]-1)]);
                }
                overall = overall + totalForTask;
                maxScore = maxScore + 90;
                System.out.println("\r\nThe total for task " + (o+1) + " is: " + totalForTask + "/90");

                totalForTask = (totalForTask * 100) / 90;
                System.out.println("The percentage for task " + (o+1) + " is: " + totalForTask + "%");

                totalForTask = 0;
            }
            percentage = (overall * 100) / maxScore;
            System.out.println("\r\nOverall the score is: " + overall + "/" + maxScore);
            System.out.println("The percentage is: " + percentage + "%\r\n");
        }
        else {
            //Listing all module choices
            File folder = new File(pathName + "\\" + yearGroup);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
                }
            }
            //Decision to write to text file or just read it
            System.out.println("Do you see your option choices 1 = Yes; 2 = No");
            int x = in.nextInt();
            if(x==1){
                System.out.println("Please select your option choice...");
                //read the file and select if passed or not
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        int z = i+1;
                        System.out.println(z +": " + listOfFiles[i].getName());
                    }
                }
                int z = (in.nextInt())-1;
                BufferedReader br = null;
                try {
                    File file = new File(String.valueOf(listOfFiles[z])); // java.io.File
                    FileReader fr = new FileReader(file); // java.io.FileReader
                    br = new BufferedReader(fr); // java.io.BufferedReader
                    String line;
                    int f = 0;
                    while ((line = br.readLine()) != null && f!=2) {
                        System.out.println(line);
                        System.out.println("Has this been awarded?");
                        f=in.nextInt();
                        if(f==2){
                            if(line.startsWith("P"))grade = gradeOptions[0];
                            if(line.startsWith("M"))grade = gradeOptions[1];
                            if(line.startsWith("D"))grade = gradeOptions[2];
                            setFeedback(br.readLine());
                        }
                    }
                    if(grade==null){
                        grade = gradeOptions[3];
                    }
                }
                catch(IOException e) { e.printStackTrace();}
                finally
                {
                    try { if (br != null) br.close(); }
                    catch(IOException e) { e.printStackTrace(); }
                }
            }
            else if(x==2){
                System.out.println("What is the new assignment?");
                Scanner input = new Scanner(System.in);
                String y = input.nextLine();
                File newFile = new File(pathName + "\\" + yearGroup + "\\" + y + ".txt");
                //File newFile = new File(input + ".txt");
                try {
                    //Writing to the text file
                    newFile.createNewFile();
                    FileWriter myWriter = new FileWriter(pathName + "\\" + yearGroup + "\\" + y + ".txt");

                    //Create new array for the amount of pass merit distinction criteria of the total.
                    int[] PMD = new int[3];
                    System.out.println("How many pass criteria");
                    PMD[0] = input.nextInt();
                    System.out.println("How many merit criteria");
                    PMD[1] = input.nextInt();
                    System.out.println("How many distinction criteria");
                    PMD[2] = input.nextInt();
                    String criteria = "";
                    input.nextLine();

                    //nested for for pass merit and distinction
                    for (int f=0;f<PMD.length;f++) {
                        switch (f) {
                            case 0: criteria = "Pass";break;
                            case 1: criteria = "Merit";break;
                            case 2: criteria = "Distinction";break;
                            default:System.out.println("Unexpected Value");
                        }
                        for (int i = 0; i < PMD[f]; i++) {
                            int z = i + 1;
                            System.out.println(criteria + ": " +z + ": ");
                            myWriter.write(criteria + " - "+ input.nextLine() + "\r\n");
                        }
                    }
                    myWriter.close();
                    strengths();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Unexpected value entered");
                strengths();
            }
        }
    }

    public void print(){
        System.out.println("Year Group: " + yearGroup);
        System.out.println("Name: " + name);
        grade();
        System.out.println("Grade: " + grade);
        if(yearGroup == "Level 4") {
            System.out.println("\r\nThe overall score was calculated at: " + overall + "/" + maxScore);
            System.out.println("The percentage of the mark is : " + percentage + "%");
        }
        else {
            if(feedback!=null && grade != gradeOptions[0]){
                System.out.println("Congratulations "+name+"! You have achieved a "+ grade +", to improve you need to " + feedback);
            }
            else if(grade == gradeOptions[0]){
                System.out.println("You got a fail");
                System.out.println(grade);
            }
            else{
                System.out.println("You got a distinction");
                System.out.println(grade);
            }
        }
        System.out.println();
        System.out.println();
    }


    //Getter and Setter for feedback to allow to set the next line for feedback
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        //Creates substring to remove the pass merit and distinction from the start of the feedback
        if(feedback.startsWith("P")){feedback=feedback.substring(7);this.feedback = feedback;}
        else if(feedback.startsWith("M")){feedback=feedback.substring(8);this.feedback = feedback;}
        else if(feedback.startsWith("D")){feedback=feedback.substring(14);this.feedback = feedback;}
    }
}
