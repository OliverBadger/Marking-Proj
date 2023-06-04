package com.company;

import java.util.Scanner;

public class subject {

    //TAKE BSM OUT AND CHANGE TO MANUALLY READ FROM A TEXT FILE

    private String[] L4subjects = {"Web Design", "Business Systems Modelling"};
    private String[] webDesign = {"Design a website based on the clients requirements. You need to supply a requirements specification, wire frames of the website and a navigation diagram.",
            "Justify your design by demonstrating an understanding of website design concepts and discuss the theory behind your design decisions. (750 words)",
            "Implement the website you have designed on different platforms paying attention to site structure and layout. Demonstrate the use of HTML5 and CSS3 to control web pages.",
            "Test the website for functionality and security in different browsers and on different platforms as well as producing supportive documentation such as a test plan and user documentation.",
            "Critically review your website against the requirements specification (750 words)"};

    private String[] BSM = {"Apply system modelling techniques to a range of scenarios.",
            "Use system models to suggest improvements.",
            "Critically evaluate the tools and techniques of business modelling."};

    public String[] degreeSelector(String yearGroup, String[] yearGroupArray){
        Scanner in = new Scanner(System.in);
        String subject[] = null;

        if (yearGroup == yearGroupArray[4]) {
            System.out.println("\r\nWhich subject are you marking?");
            for (int i = 0; i < L4subjects.length; i++) {
                int x = i + 1;
                System.out.println(x + ": " + L4subjects[i]);
            }
        }
        int input = in.nextInt();
        switch (input) {
            case 1: subject = webDesign; break;
            case 2: subject = BSM; break;
            case 3: System.out.println("Wednesday"); break;
            case 4: System.out.println("Thursday"); break;
            case 5: System.out.println("Friday"); break;
            case 6: System.out.println("Saturday"); break;
            case 7: System.out.println("Sunday"); break;
        }
        return subject;
    }
}