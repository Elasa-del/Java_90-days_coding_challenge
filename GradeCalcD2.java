import java.util.Scanner;

public class GradeCalcD2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double mark = -1.0;
        
        System.out.println("=========================================");
        System.out.println("   HARAMAYA CS: GRADE CALCULATOR ENGINE  ");
        System.out.println("=========================================");
        
        do {
            System.out.println("Enter exam mark [0.0 - 100.0]:");
            if(sc.hasNextDouble()){
                mark = sc.nextDouble();
                if (mark < 0.0 || mark > 100.0){
                    System.out.println("❌ Invalid score, please enter mark between (0.0-100.0).\n");
                }
            } else {
        
                System.out.println("❌ Invalid Input! Please enter a valid numeric value.\n");
                sc.next(); 
            }
        } while(mark < 0.0 || mark > 100.0);

        String letterGrade;
        if(mark >= 90.0){
            letterGrade = "A+";
        } else if(mark >= 85.0){
            letterGrade = "A";
        } else if(mark >= 80.0){
            letterGrade = "A-";
        } else if(mark >= 75.0){
            letterGrade = "B+";
        } else if(mark >= 70.0){
            letterGrade = "B";
        } else if(mark >= 65){
            letterGrade = "B-";
        } else if(mark >= 60){
            letterGrade = "C+";
        } else if(mark >= 50){
            letterGrade = "C";
        } else if(mark >= 40){
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }

        String remarks = switch(letterGrade){
            case "A+" -> "Excellent!";
            case "A"  -> "Very Good";
            case "A-" -> "Good";
            case "B+" -> "Above Average";
            case "B"  -> "Average";
            case "B-" -> "Satisfactory";
            case "C+" -> "Fair";
            case "C"  -> "Pass";
            case "D"  -> "Marginal Pass";
            case "F"  -> "Fail";
            default   -> "Unknown Grade State.";
        };

        boolean isPassed = !letterGrade.equals("F"); 

        System.out.println("\n-----------------------------------------");
        System.out.println("           FINAL EVALUATION RESULT       ");
        System.out.println("-----------------------------------------");
        System.out.printf(" Exam Mark Score  : %.2f / 100.00%n", mark);
        System.out.println(" Letter Grade     : " + letterGrade);
        System.out.println(" Academic Status  : " + (isPassed ? "PASSED ✅" : "FAILED ❌"));
        System.out.println(" Instructor Remark: " + remarks);
        System.out.println("=========================================");
        
        sc.close();
    }
}
