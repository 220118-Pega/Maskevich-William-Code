package com.revature.projectreimbursement.ui;

import java.util.Scanner;

public class InputClass {
	
	public static final int getInputInt(String myString) {
		boolean loopCounter = true;
		Scanner aNum = new Scanner(System.in);
		int myNum = -1;
		do {
			if (aNum.hasNextInt()) {
				myNum = aNum.nextInt();
				loopCounter = false;
				//anInt.nextLine();
			} else {
				System.out.println(myString);
				aNum.nextLine();
			}
		} while (loopCounter);
		return myNum;
	}
	public static final double getInputDouble(String myString) {
		boolean loopCounter = true;
		Scanner aNum = new Scanner(System.in);
		// only need to change dataType and ....
		double myNum = -1d;
		do {
			if (aNum.hasNextDouble()) {
				myNum = aNum.nextDouble();
				loopCounter = false;
			} else {
				System.out.println(myString);
				aNum.nextLine();
			}
		} while (loopCounter);
		// ... the return type when copying this for other numeric dataType ...
		return myNum;
		// .. well, of course, and the return type in method signature. and all the other stuff that needs to be changes as well!!!
	}

}
