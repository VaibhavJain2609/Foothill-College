/**
 * Calendar Lab (Template Class and Test Cases)
 * This is the template class and test cases for the Calendar Lab.
 * Written for the Woodstock School in Mussoorie, Uttarakhand, India.
 * 
 * @author Jeffrey Santos
 * @version 1.0
 */

public class Calendar {

	public static void main(String[] args) {
		//	Tests for calculateDayOfWeek:
		System.out.println(calculateDayOfWeek(9, 7, 1983));		// Output: 6
		System.out.println(calculateDayOfWeek(15, 4, 2016));	// Output: 5
		System.out.println(calculateDayOfWeek(30, 1, 2000));	// Output: 0
//	Tests for getDayOfWeek:
		System.out.println(getDayOfWeek(9, 7, 1983));	// Output: "Saturday"
		System.out.println(getDayOfWeek(15, 4, 2016));	// Output: "Friday"
		System.out.println(getDayOfWeek(30, 1, 3000));	// Output: "Sunday" 
		
		
		System.out.println(isLeapYear(1600));
		//	Tests for printCalendar:
		//	 Use the calendar feature of your computer to verify correct output.
	     	printCalendar(7, 2015);
		printCalendar(5, 2016);
		printCalendar(1, 2017);
	}

	/**
	 * Calculates the day of the week as a number between 0 and 6.
	 * (0=Sunday, 6=Saturday)
	 *
	 * @param day
	 *	Precondition: day is a valid day of the given month.
	 * @param month
	 *	Precondition:	month is a valid month of the year.
	 * @param year
	 *	Precondition: year is a valid year after the adoption of the Gregorian Calendar.
	 * @return	A number corresponding to the day of the week for the given date.
	 *				  (0=Sunday, 6=Saturday)
	 */
	//public static int calculateDayOfWeek(int d, int m, int y) {
		
	//}
	public static int calculateDayOfWeek(int day, int month, int year) {
		if (month == 1) {
			month = 11;
		year = year - 1;
		}
		else if (month == 2) {
			month = 12;
		year = year - 1;
		}
		else if (month == 3)
			month = 1;
		else if (month == 4)
			month = 2;
		else if (month == 5)
			month = 3;
		else if (month == 6)
			month = 4;
		else if (month == 7)
			month = 5;
		else if (month == 8)
			month = 6;
		else if (month == 9)
			month = 7;
		else if (month == 10)
			month = 8;
		else if (month == 11)
			month = 9;
		else if (month == 12)
			month = 10;
		int c = year / 100;
		int y = mod(year, 100);
		int week = mod((int) ((day + Math.floor((2.6*month)-0.2)+(y)+Math.floor(c/4)+((y)/4)-(2*c))), 7);
		return week;
	}

	/**
	 * Returns the day of the week as a string.
	 *
	 * @param day
	 *	Precondition: day is a valid day of the given month.
	 * @param month
	 *	Precondition: month is a valid month of the year.
	 * @param year
	 *	Precondition: year is a valid year after the adoption of the Gregorian Calendar.
	 */
	public static String getDayOfWeek(int day, int month, int year) {
	//	To be implemented in Activity #1, Exercise 2
	
	
		
	if (calculateDayOfWeek(day, month, year)==0)
		return "Sunday";
	if (calculateDayOfWeek(day, month, year)==1) 
		return "Monday";
	if (calculateDayOfWeek(day, month, year)==2) 
		return "Tuesday";
	if (calculateDayOfWeek(day, month, year)==3) 
		return "Wednesday";
	if (calculateDayOfWeek(day, month, year)==4) 
		return "Thursday";
	if (calculateDayOfWeek(day, month, year)==5) 
		return "Friday";
	if (calculateDayOfWeek(day, month, year)==6)
		return "Saturday";
		return null;
	}
	/**
	 * Determines whether or not a given year is a leap year.
	 *
	 * @param year
	 *	Precondition: year is a valid year after the adoption of the Gregorian Calendar.
	 * @return	true if the given year is a leap year, false otherwise.
	 */
	public static boolean isLeapYear(int year) {
		//	To be implemented in Activity #2, Exercise 1
			boolean Leap = false;
		   if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			   Leap = true;
		   }
		   return Leap;
	}

	/**
	 * Prints a visual calendar for the given month and year.
	 *
	 * @param month
	 *	Precondition: month is a valid month is the year.
	 * @param year
	 *	Precondition: year is a valid year after the adoption of the Gregorian calendar.
	 */
	public static void printCalendar(int month, int year) {
		String months[] = {"January", "February", "March", "April", "May", "June","July", "August", "September","October", "November", "December"};
		int dpm[] = {31, 28, 31, 30, 31, 30, 31, 31,30, 31, 30, 31};
		if (month==2 && isLeapYear(year)==true)
			dpm[1] = 29;
		int day1 = calculateDayOfWeek(1, month, year);
		System.out.println("      "+months[month-1] +", " + year);
		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");
		int i = 1;
		for (; i <= day1; i++) {
			System.out.print("    ");
		}
		for(int j = 1; j <= dpm[month-1]; j++, i++) {
			if ((i%7) == 0) {
				if (j<10) {
					System.out.println("0" + j + "  ");
				}
				else {
					System.out.println(j + "  ");
				}
			}
			else {
				if (j<10) {
					System.out.print("0" + j + "  ");
				}
				else {
					System.out.print(j + "  ");
				}
				}
		}
		System.out.println();
		}
	
	
			
	


	
	/**
	 * Modulus helper method to handle negative modulo
	 * operations that result from the given mathematical
	 * formula.
	 * 
	 * @return Returns n mod d.
	 */
	public static int mod(int n, int d) {
		return (n % d + d) % d;
	}


	}
// Saksham Helped Me