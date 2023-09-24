import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int myNumber = (int)(Math.random()*100);
        int userNumber=0;

        do{
            System.out.println("Guess my number: ");
            userNumber = sc.nextInt();

            if(userNumber==myNumber){
                System.out.println("woohoo .. Correct Number!");
                break;
                
            }
            else if(userNumber>myNumber){
                System.out.println("your number is to large");
            }
            else{
                System.out.println("your number is to small");
            }
        }while(userNumber>=0);
        System.out.println("My number was: ");
}







}