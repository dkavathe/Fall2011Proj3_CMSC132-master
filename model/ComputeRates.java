package model;

import javax.swing.JTextField;

/**
 * 
 * @author Dev Kavathekar
 *
 */

//performs arithmetic for InterestGUI
public class ComputeRates {
	
	//ensures all entries are valid
	public static boolean checkEmptyStrings(JTextField pr, JTextField rte, JTextField yrs){
		
		return (pr.getText().equals("") || rte.getText().equals("") || 
				yrs.getText().equals(""));
		
		
	}

	//calculates simple Interest based on given formula
	//returns result as double (no need to cast back JLabel's take doubles (contacted))
	public static double simpleInterest(double principal, double rate, double years){
		
	
		//simple interest amount = principal + (principal * (rate/100) * years)	
		return principal + (principal * (rate/100) * years);
		
		
	}
	
	//calculates compound interest based on formula
	public static double compoundInterst(double principal, double rate, double years){
		
		
		//Compound Interest Amount = principal * (1 + rate/100)^Years
		return principal * Math.pow((1 + rate/100), years);
		
		
	}
}
