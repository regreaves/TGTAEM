import java.util.*;

public class launcher {
    public static void main(){
      Scanner in = new Scanner(System.in);
      User user = new User("user", "user");
      Game game = new Game(user);
      
      System.out.println("Please input file locations.");
      System.out.println("Specify map location:");
      String map = in.nextLine();
      System.out.println("Specify items location:");
      String items = in.nextLine();
      System.out.println("Specify player location:");
      String p = in.nextLine();

    }
}
