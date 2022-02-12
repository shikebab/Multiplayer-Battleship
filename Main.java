import java.util.Scanner;
public class Main {
    public static char [] [] setBoard(char [] [] arr){
        for(int i = 0; i <=9; i++){
            for(int j = 0; j <=9; j++){
                arr [i] [j] = '.';
            }
        }
        return arr;
    }
    public static void printBoard (char [] [] arr){
        System.out.print("    ");
        for(int m = 0; m <= 9; m++){
            System.out.print(m + "   ");
        }
        System.out.println();
        for(int i = 0; i <=9; i++){
            System.out.print(i + " ");
            System.out.print("|");
            for(int j = 0; j<=9; j++){
                System.out.print( " " + arr [i] [j] + " |");
            }
            System.out.println();
        }
    }


    enum Ship_type{
        NOSHIP, CARRIER, BATTLESHIP, CRUISER, SUBMARINE, DESTROYER
    }
    enum Directions{
        UP, DOWN, LEFT, RIGHT
    }

    static String Ship_type_print(Ship_type [] arr){
        String ret = "[";
        for(int i = 0; i < arr.length; i++){
            switch (arr[i]){
                case DESTROYER -> ret+="DESTROYER, ";
                case CRUISER -> ret+="CRUISER, ";
                case SUBMARINE -> ret+="SUBMARINE, ";
                case BATTLESHIP -> ret+="BATTLESHIP, ";
                case CARRIER -> ret+="CARRIER, ";
                default -> ret+="";
            }
        }
        ret += "]";
        return ret;
    }
    static boolean Is_placement_possible(char [] [] array, int Col, int Row, Directions dir, Ship_type s_t, Ship_type [] player_ships){
       boolean ret = false;
       for(int i = 0; i < player_ships.length; i++){
           ret = ret || (player_ships[i].equals(s_t));
       }
       if(!ret){return ret;}
       int min = 0;
       int max = 0;
       switch(dir){
           case RIGHT:
               switch(s_t){
                   case CARRIER:
                      max = Col + 5;
                      break;
                   case DESTROYER:
                        max = Col + 2;
                        break;
                   case CRUISER:
                   case SUBMARINE:
                       max = Col + 3;
                       break;
                   case BATTLESHIP:
                       max = Col + 4;
                       break;
                   }
               if(max > 10){
                   ret = false;
               }
               for(int i = Col; i < max; i++){
                   ret = ret && ((array [Row] [i]) == '.');
               }
               break;
           case LEFT:
               switch(s_t){
                   case CARRIER:
                       min = Col - 5;
                       break;
                   case DESTROYER:
                       min = Col - 2;
                       break;
                   case CRUISER:
                   case SUBMARINE:
                       min = Col - 3;
                       break;
                   case BATTLESHIP:
                       min = Col - 4;
                       break;
               }
               if(min < -1){
                   ret = false;
               }
               for(int i = Col; i > min; i--){
                   ret = ret && ((array [Row] [i]) == '.');
               }
               break;
           case DOWN:
               switch(s_t){
                   case CARRIER:
                       max = Row + 5;
                       break;
                   case DESTROYER:
                       max = Row + 2;
                       break;
                   case CRUISER:
                   case SUBMARINE:
                       max = Row + 3;
                       break;
                   case BATTLESHIP:
                       max = Row + 4;
                       break;
               }
               if(max > 10){
                   ret = false;
               }
               for(int i = Row; i < max; i++){
                   ret = ret && ((array [i] [Col]) == '.');
               }
               break;
           case UP:
               switch(s_t){
                   case CARRIER:
                       min = Row - 5;
                       break;
                   case DESTROYER:
                       min = Row - 2;
                       break;
                   case CRUISER:
                   case SUBMARINE:
                       min = Row - 3;
                       break;
                   case BATTLESHIP:
                       min = Row - 4;
                       break;
               }
               if(min < -1){
                   ret = false;
               }
               for(int i = Row; i > min; i--){
                   ret = ret && ((array [i] [Col]) == '.');
               }
               break;
        }
        return ret;
    }
    static char [] [] actual_placement(char [] [] array, int Col, int Row, char icon, Ship_type s_t, Directions dir, Ship_type [] player_ships){
        if(Is_placement_possible(array, Col, Row, dir, s_t, player_ships)) {
            int max = 0;
            int min = 0;
            switch (dir) {
                case RIGHT:
                    switch (s_t) {
                        case CARRIER:
                            max = Col + 5;
                            break;
                        case DESTROYER:
                            max = Col + 2;
                            break;
                        case CRUISER:
                        case SUBMARINE:
                            max = Col + 3;
                            break;
                        case BATTLESHIP:
                            max = Col + 4;
                            break;
                    }
                    for (int i = Col; i < max; i++) {
                        array[Row][i] = icon;
                    }
                    break;
                case LEFT:
                    switch (s_t) {
                        case CARRIER:
                            min = Col - 5;
                            break;
                        case DESTROYER:
                            min = Col - 2;
                            break;
                        case CRUISER:
                        case SUBMARINE:
                            min = Col - 3;
                            break;
                        case BATTLESHIP:
                            min = Col - 4;
                            break;
                    }
                    for (int i = Col; i > min; i--) {
                        array[Row][i] = icon;
                    }
                    break;
                case DOWN:
                    switch (s_t) {
                        case CARRIER:
                            max = Row + 5;
                            break;
                        case DESTROYER:
                            max = Row + 2;
                            break;
                        case CRUISER:
                        case SUBMARINE:
                            max = Row + 3;
                            break;
                        case BATTLESHIP:
                            max = Row + 4;
                            break;
                    }
                    for (int i = Row; i < max; i++) {
                        array[i][Col] = icon;
                    }
                    break;
                case UP:
                    switch (s_t) {
                        case CARRIER:
                            min = Row - 5;
                            break;
                        case DESTROYER:
                            min = Row - 2;
                            break;
                        case CRUISER:
                        case SUBMARINE:
                            min = Row - 3;
                            break;
                        case BATTLESHIP:
                            min = Row - 4;
                            break;
                    }
                    for (int i = Row; i > min; i--) {
                        array[i][Col] = icon;
                    }
                    break;
            }
        }
        else{
            System.out.println("Invalid position. Please enter the position carefully");
        }

        return array;
    }
    static Ship_type str_to_ship_type(String str){
        str = str.toUpperCase();
        if(str.equals("SUBMARINE")){
            return Ship_type.SUBMARINE;
        }
        else if(str.equals("DESTROYER")){
            return Ship_type.DESTROYER;
        }
        else if(str.equals("CRUISER")){
            return Ship_type.CRUISER;
        }
        else if(str.equals("BATTLESHIP")){
            return Ship_type.BATTLESHIP;
        }
        else {
            return Ship_type.CARRIER;
        }
    }

    static Directions str_to_dir(String str){
        str = str.toUpperCase();
        if(str.equals("UP")){
            return Directions.UP;
        }
        else if(str.equals("DOWN")){
            return Directions.DOWN;
        }
        else if(str.equals("RIGHT")){
            return Directions.RIGHT;
        }
        else {
            return Directions.LEFT;
        }
    }
    static boolean game_Won(char [] [] array, Player [] player_list){
        int num_of_zeroes = 0;
        int r = player_list.length;
        for (int i =0; i < r; i++){
            Player p = player_list[i];
            int count = 0;
            for(int k = 0; k < 9; k++){
                for (int m = 0; m < 9; m++){
                    if(array [k] [m] == p.icon){
                        count +=1;
                    }
                }
            }
            if(count == 0){num_of_zeroes+=1;}
        }
        return (num_of_zeroes == (r- 1));
    }

    static int num_seg_hit(char [] [] array, int Col, int Row, int Mag){
        int count = 0;
        switch(Mag){
            case 1:
                if((Row >= 0 && Row <= 9) && (Col >=0 && Col <=9)) {
                    if (array[Row][Col] != '.') {
                        array[Row][Col] = '.';
                        count += 1;
                    }
                }
                break;
            case 2:
                count += num_seg_hit(array, Col, Row, 1);
                count += num_seg_hit(array, Col, Row + 1, 1);
                count += num_seg_hit(array, Col, Row -1 , 1);
                count += num_seg_hit(array, Col + 1, Row + 1, 1);
                count += num_seg_hit(array, Col + 1, Row, 1);
                count += num_seg_hit(array, Col + 1, Row - 1, 1);
                count += num_seg_hit(array, Col - 1, Row + 1, 1);
                count += num_seg_hit(array, Col - 1, Row, 1);
                count += num_seg_hit(array, Col - 1, Row - 1, 1);
                break;
            case 3:
                count += num_seg_hit(array, Col, Row, 1);
                count += num_seg_hit(array, Col, Row + 1, 1);
                count += num_seg_hit(array, Col, Row -1 , 1);
                count += num_seg_hit(array, Col + 1, Row + 1, 1);
                count += num_seg_hit(array, Col + 1, Row, 1);
                count += num_seg_hit(array, Col + 1, Row - 1, 1);
                count += num_seg_hit(array, Col - 1, Row + 1, 1);
                count += num_seg_hit(array, Col - 1, Row, 1);
                count += num_seg_hit(array, Col - 1, Row - 1, 1);
                count += num_seg_hit(array, Col, Row - 2, 1);
                count += num_seg_hit(array, Col, Row + 2, 1);
                count += num_seg_hit(array, Col + 2, Row + 2, 1);
                count += num_seg_hit(array, Col + 2, Row, 1);
                count += num_seg_hit(array, Col + 2, Row - 2, 1);
                count += num_seg_hit(array, Col - 2, Row, 1);
                count += num_seg_hit(array, Col - 2, Row - 2, 1);
                count += num_seg_hit(array, Col - 2, Row + 2, 1);
                break;
            default:
                System.out.println("Sorry invalid magnitude.");
                break;
        }
        return count;
    }
    static void count_down_calc(Player pl, int Mag){
        pl.cool_down += (Mag -1);
    }
    static Player find_Winner(Player [] player_array, char [] [] array){
        char w_c = ' ';
        Player winner = player_array[0];
        for (int i = 0; i <= 9;i++ ){
            for(int j =0; j <=9; j++){
                if(array [i] [j] != '.'){
                    w_c = array [i] [j];
                }
            }
        }
        for(int o = 0; o < player_array.length; o++){
            if(player_array[o].icon == w_c){
                winner = player_array[o];
            }
        }
        return winner;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of players ");
        int num_of_Players = sc.nextInt();
        Player [] player_array = new Player[num_of_Players];
        for(int i = 1; i <= num_of_Players; i++){
            System.out.println("Which icon would Player " + i + " like to use? ");
            char ch = sc.next().charAt(0);
            Player a = new Player(i, ch);
            player_array[i-1] = a;
        }
        System.out.println("Players are: ");
        for (int j = 0; j < num_of_Players; j++){
            System.out.println("Player[num = "+ player_array[j].num + ", " + "icon = " + player_array[j].icon + ", cooldown = " + player_array[j].cool_down + "]");
        }
        System.out.println();
        System.out.println(" -= Let's place the ships! =-");
        System.out.println("The ship types are: [CARRIER, BATTLESHIP, CRUISER, SUBMARINE, DESTROYER]");
        System.out.println("Directions are: [UP, DOWN, LEFT, RIGHT]");
        System.out.println("Maximum shot magnitude is: 3");
        System.out.println("The map looks like this: ");
        char [] [] nullBoard=  new char [10] [10];
        char [] [] gameBoard = setBoard(nullBoard);
        printBoard(gameBoard);
        int m = num_of_Players * 5;
        for(int z = 1; z <= m; z+=num_of_Players) {
            for(int l = 0; l < player_array.length; l++){
                System.out.print("Player " + (l+1) + " please input a ship to place. You have these ships left: ");
                System.out.println(Ship_type_print(player_array[l].player_ships));
                System.out.print("Please input '<column> <row> <type> <direction>': ");
                int Col = sc.nextInt();
                int Row = sc.nextInt();
                String ship_t = sc.next();
                String dir = sc.next();
                Ship_type s_t = str_to_ship_type(ship_t);
                if(Is_placement_possible(gameBoard, Col, Row, str_to_dir(dir), s_t, player_array[l].player_ships)){
                    printBoard(actual_placement(gameBoard, Col, Row, player_array[l].icon, s_t, str_to_dir(dir), player_array[l].player_ships));
                    player_array[l].update_player_ships(s_t);}
                else{System.out.println("Please ensure that your input is not overlapping with other ships and you have not already entered this ship type.\n You get just one more chance to input correctly");
                    System.out.print("Please input '<column> <row> <type> <direction>': ");
                    Col = sc.nextInt();
                    Row = sc.nextInt();
                    ship_t = sc.next();
                    dir = sc.next();
                    s_t = str_to_ship_type(ship_t);
                    if(Is_placement_possible(gameBoard, Col, Row, str_to_dir(dir), s_t, player_array[l].player_ships)){
                        printBoard(actual_placement(gameBoard, Col, Row, player_array[l].icon, s_t, str_to_dir(dir), player_array[l].player_ships));
                        player_array[l].update_player_ships(s_t);}
                }
            }
        }
        System.out.println("All ships placed:");
        printBoard(gameBoard);
        System.out.println();
        System.out.println(" -= Let's play! =- ");
        System.out.println();
        System.out.println("The board:");
        printBoard(gameBoard);
        while(true){
            for(int d = 0; d < num_of_Players; d++) {
                if(player_array[d].cool_down == 0) {
                    System.out.println("Player " + (d + 1) + ", it is your turn.\nInput your shot.");
                    System.out.print("Please input <col> <row> <magnitude> ");
                    int c = sc.nextInt();
                    int r = sc.nextInt();
                    int q = sc.nextInt();
                    int n1 = num_seg_hit(gameBoard, c, r, q);
                    count_down_calc(player_array[d], q);
                    if (n1 > 1) {
                        System.out.println("Wow! You hit " + n1 + " ship parts!");
                    } else if (n1 == 1) {
                        System.out.println("Wow! You hit 1 ship part!");
                    } else {
                        System.out.println("Sorry, No ship parts were hit.");
                    }
                    printBoard(gameBoard);
                    if (game_Won(gameBoard, player_array)) {
                        Player winner = find_Winner(player_array, gameBoard);
                        System.out.println("Aaaaand... the winner is: ");
                        System.out.println("Player[num = " + winner.num + ", " + "icon = " + winner.icon + ", cooldown = " + winner.cool_down + "] !!!");
                        break;
                    }
                }
                else{
                    System.out.println("Player " + (d+1) + " is still on cool-down.");
                    player_array[d].cool_down -=1;
                }
            }
            if(game_Won(gameBoard, player_array)){
                break;
            }
        }
    }
}
/* What I still need to solve:
1) No Exceptions (Always trusting the user to enter the right data).
 */
/* What can/should be improved:
1) Using a while loop to keep asking the user to input the right way if he repeatedly inputs incorrectly.
2) In case of many players, making the game board bigger.
3) Avoiding repetition for a lot of methods. Using variables to prevent repeated calculations.
4) Writing more structured and readable code by not cramming it altogether in the Main method.
5) In case of 1 Player, the second player can be the computer and insert ships at random positions and take random shots using Java's Random class.

 */
