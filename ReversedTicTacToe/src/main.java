import java.util.Scanner;
import java.util.Random;
public class main {

    public static void main(String[] args) {
        char[][] Board = new char[5][5];
        int row = 0;
        int col = 0;
        int countAItry =0;
        int movei=-1;
        int movej=-1;
        Random rand = new Random();
        int Round = rand.nextInt(2);
        if(Round==0){
            System.out.print("AI start the game\n" );

        }else{
            System.out.print("Player start the game\n" );
        }
        int playerGetColor=rand.nextInt(2);
        char Player=' ';
        char AI=' ';
        if (playerGetColor==0)
        {
            Player='B';
            AI='R';
            System.out.print("Player color is Blue(B)\n" );
            System.out.print("AI color is Red(R)\n" );
        }else{
            Player='R';
            AI='B';
            System.out.print("Player color is Red(R)\n" );
            System.out.print("AI color is Blue(B)\n" );
        }
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < Board.length; i++) {
                for (int j = 0; j < Board.length; j++) {
                    Board[i][j] = ' ';
                }
            }
            boolean noOneWinTheGameYet = true;
            while (noOneWinTheGameYet) {
                System.out.println("------------");
                for (int i = 0; i < Board.length; i++) {
                    System.out.printf("| %c %c %c %c %c |\n", Board[i][0], Board[i][1], Board[i][2],Board[i][3],Board[i][4]);
                }
                System.out.println("------------");
                noOneWinTheGameYet = gamestate(Board);
                while (noOneWinTheGameYet) {
                    if (Round % 2 != 0){
                        System.out.print("Player Round\n");
                        System.out.print("Enter row and column values: ");


                        String[] rowAndCol = scanner.nextLine().split(" ");
                        try {
                            row = Integer.parseInt(rowAndCol[0]) - 1;
                            col = Integer.parseInt(rowAndCol[1]) - 1;
                        } catch (Exception e) {
                            System.out.println("Row and column values can only be numbers!");
                            continue;
                        }
                        if (row + 1 > 5 || row + 1 < 1 || col + 1 > 5 || col + 1 < 1) {
                            System.out.println("Coordinates can only be entered from 1 to 5!");
                            continue;
                        }
                        if (Board[row][col] == ' ') {
                            Board[row][col] = Player;
                            Round++;
                            break;
                        } else {
                            System.out.println("This cell is not empty!");
                            continue;
                        }
                    }else{
                        int i =rand.nextInt(5);
                        int j =rand.nextInt(5);
                        if( Board[i][j]==' '){
                            Board[i][j] = AI;
                            if(gamestate(Board)==false){
                                Board[i][j] = ' ';
                                countAItry++;
                                if(countAItry>=1000) {
                                    System.out.print("AI Round\n");
                                    for (int x=0; x<5;x++){
                                        for (int y=0; y < 5;y++) {
                                            if (Board[x][y] == ' ') {
                                                movei=x;
                                                movej=y;
                                            }
                                        }
                                    }
                                    Board[movei][movej] = AI;
                                    Round++;
                                    break;
                                }
                            }else{
                                System.out.print("AI Round\n");
                                Round++;
                                break;
                            }
                        }
                    }

                }
            }
        }
        if(gamestate(Board)==false)
        {
            if (Round%2!=0) {
                System.out.println("-----------");
                System.out.println("| \\ - - - / |");
                System.out.println("| -\\ - - /- |");
                System.out.println("|P l a y e r|");
                System.out.println("| -/W I N\\- |");
                System.out.println("| / - - - \\ |");
                System.out.println("-----------");
            }else {
                System.out.println("-----------");
                System.out.println("|\\ - - - /|");
                System.out.println("|-\\ - - /-|");
                System.out.println("|   A I   |");
                System.out.println("|-/W I N\\-|");
                System.out.println("|/ - - - \\|");
                System.out.println("-----------");
            }
        }
    }

    private static boolean gamestate(char[][] Board) {
        int win = 0;
        char winnerChar=' ';
        int rowCountRed=0;
        int rowCountBlue=0;
        int colCountRed=0;
        int colCountBlue=0;
        int diagCountRed=0;
        int diagCountBlue=0;
        //row
        for (int i=0; i<5;i++){
            for (int j=0; j < 5;j++) {
                if (Board[i][j] == 'R') {
                    rowCountRed++;
                    if (rowCountRed>=3){
                        win++;
                        winnerChar='B';
                    }
                }else if (Board[i][j] == 'B') {
                    rowCountBlue++;
                    if (rowCountBlue>=3){
                        win++;
                        winnerChar='R';
                    }
                }
            }
            rowCountRed=0;
            rowCountBlue=0;
        }
        //col
        for (int j=0; j < 5;j++) {
            for (int i=0; i<5;i++){
                if (Board[i][j] == 'R') {
                    colCountRed++;
                    if (colCountRed>=3){
                        win++;
                        winnerChar='B';
                    }
                }else if (Board[i][j] == 'B') {
                    colCountBlue++;
                    if (colCountBlue>=3){
                        win++;
                        winnerChar='R';
                    }
                }
            }
            colCountBlue=0;
            colCountRed=0;
        }
        //diag 3 long
        for(int n = 0; n < 4; n++) {
            if(n == 0) {
                for(int i = 2, j = 0; i >= 0 && j <= 2; i--, j++) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
            else if (n == 1) {
                for(int i = 2, j = 0; i <= 4 && j <= 2; i++, j++) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
            else if (n == 2) {
                for(int i = 2, j = 4; i >= 0 && j >= 2; i--, j--) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
            else {
                for(int i = 2, j = 4; i <= 4 && j >= 2; i++, j--) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
        }
        //diag 4 long
        for(int n = 0; n < 4; n++) {
            if(n == 0) {
                for(int i = 3, j = 0; i >= 0 && j <= 3; i--, j++) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
            else if (n == 1) {
                for(int i = 1, j = 0; i <= 4 && j <= 3; i++, j++) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
            else if (n == 2) {
                for(int i = 3, j = 4; i >= 0 && j >= 1; i--, j--) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                           winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
            else {
                for(int i = 1, j = 4; i <= 4 && j >= 1; i++, j--) {
                    if (Board[i][j] == 'R') {
                        diagCountRed++;
                        if (diagCountRed>=3){
                            win++;
                            winnerChar='B';
                        }
                    }else if (Board[i][j] == 'B') {
                        diagCountBlue++;
                        if (diagCountBlue>=3){
                            win++;
                            winnerChar='R';
                        }
                    }
                }
                diagCountBlue=0;
                diagCountRed=0;
            }
        }
        //diag 5 long
        for(int i = 0, j = 0; i <= 4 && j <= 4; i++, j++) {
            if (Board[i][j] == 'R') {
                diagCountRed++;
                if (diagCountRed>=3){
                    win++;
                    winnerChar='B';
                }
            }else if (Board[i][j] == 'B') {
                diagCountBlue++;
                if (diagCountBlue>=3){
                    win++;
                    winnerChar='R';
                }
            }
        }
        diagCountBlue=0;
        diagCountRed=0;
        for(int i = 0, j = 4; i <= 4 && j >= 0; i++, j--) {
            if (Board[i][j] == 'R') {
                diagCountRed++;
                if (diagCountRed>=3){
                    win++;
                    winnerChar='B';
                }
            }else if (Board[i][j] == 'B') {
                diagCountBlue++;
                if (diagCountBlue>=3){
                    win++;
                    winnerChar='R';
                }
            }
        }

        if (win >= 1) {
            /*if (winnerChar=='B') {
                System.out.println("-----------");
                System.out.println("|\\ - - - /|");
                System.out.println("|-\\ - - /-|");
                System.out.println("| B L U E |");
                System.out.println("|-/W I N\\-|");
                System.out.println("|/ - - - \\|");
                System.out.println("-----------");
            }else if (winnerChar=='R') {
                System.out.println("-----------");
                System.out.println("|\\ - - - /|");
                System.out.println("|-\\ - - /-|");
                System.out.println("|  R E D  |");
                System.out.println("|-/W I N\\-|");
                System.out.println("|/ - - - \\|");
                System.out.println("-----------");
            }*/
            return false;
        }
        return true;
    }
}