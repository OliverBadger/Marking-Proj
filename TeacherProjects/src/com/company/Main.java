package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Initializes new student
        student s1 = new student();
        //Get year group
        s1.yearGroup();
        //Get Name
        System.out.println("\r\nWhat is the Name of the Student?");
        s1.name = in.nextLine();
        //Get Strengths of the Piece
        s1.strengths();
        //Get Grade
//        s1.grade();

        s1.print();
    }
}
