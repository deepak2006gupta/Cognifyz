import java.util.Scanner;

class TicTacToe {
   public TicTacToe() {
   }
    
    public static void displayBoard(char[][] var0) {
        System.out.println();
        System.out.println(" " + var0[0][0] + " | " + var0[0][1] + " | " + var0[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + var0[1][0] + " | " + var0[1][1] + " | " + var0[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + var0[2][0] + " | " + var0[2][1] + " | " + var0[2][2]);
        System.out.println();
    }

   public static boolean hasWon(char[][] var0, char var1) {
      for(int var2 = 0; var2 < 3; var2++) {
         if (var0[var2][0] == var1 && var0[var2][1] == var1 && var0[var2][2] == var1) {
            return true;
         }

         if (var0[0][var2] == var1 && var0[1][var2] == var1 && var0[2][var2] == var1) {
            return true;
         }
      }

      if (var0[0][0] == var1 && var0[1][1] == var1 && var0[2][2] == var1) {
          return true;
      } else {
          return (var0[0][2] == var1 && var0[1][1] == var1 && var0[2][0] == var1);
      }
   }

   public static void main(String[] var0) {
      Scanner scanner = new Scanner(System.in);
      boolean var2 = true;
      System.out.println("-----Tic-Tac-Toe-----");

      while(var2) {
         char[][] var3 = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
         char var4 = 'X';
         boolean var5 = false;
         int var6 = 0;

         while(!var5) {
            displayBoard(var3);
            System.out.println("Player " + var4 + "'s turn");

            while(true) {
               System.out.print("Enter row (1-3): ");
               int var7 = scanner.nextInt() - 1;
               System.out.print("Enter column (1-3): ");
               int var8 = scanner.nextInt() - 1;
               if (var7 >= 0 && var7 <= 2 && var8 >= 0 && var8 <= 2) {
                  if (var3[var7][var8] == ' ') {
                     var3[var7][var8] = (char)var4;
                     ++var6;
                     if (hasWon(var3, (char)var4)) {
                        displayBoard(var3);
                        System.out.println("Player " + var4 + " wins!");
                        var5 = true;
                     } else if (var6 == 9) {
                        displayBoard(var3);
                        System.out.println("It's a draw!");
                        var5 = true;
                     } else {
                        var4 = var4 == 'X' ? 'O' : 'X';
                     }
                     break;
                  }

                  System.out.println("That position is already taken. Try again.");
               } else {
                  System.out.println("Invalid position. Please enter numbers from 1 to 3.");
               }
            }
         }

         System.out.print("Do you want to play again? (yes/no): ");
         String var9 = scanner.next();
         var2 = var9.equalsIgnoreCase("yes") || var9.equalsIgnoreCase("y");
      }

      System.out.println("Thanks for playing!\n");
      scanner.close();
   }

}
