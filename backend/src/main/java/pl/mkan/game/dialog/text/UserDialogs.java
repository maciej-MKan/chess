package pl.mkan.game.dialog.text;

import pl.mkan.game.engine.Move;

import java.util.Locale;
import java.util.Scanner;

public class UserDialogs {
    public static Move getNextMove() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter your move (Q - quit)");
            String s = scanner.nextLine();
            if ("Q".equalsIgnoreCase(s)){
                System.exit(0);
            }
            try{
                int colBeginning = Integer.parseInt(s.substring(0, 1));
                int rowBeginning = Integer.parseInt(s.substring(1, 2));
                int colEnd = Integer.parseInt(s.substring(2, 3));
                int rowEnd = Integer.parseInt(s.substring(3, 4));
                if (colBeginning > 7 || rowBeginning > 7 || colEnd > 7 || rowEnd > 7){
                    throw new Exception();
                }
                return new Move(colBeginning, rowBeginning, colEnd, rowEnd);
            } catch (Exception e){
                System.out.println("Wrong move, try again");
            }
        }
    }
}
