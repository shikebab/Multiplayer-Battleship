public class Player {
    public int num;
    public char icon;
    public int cool_down;
    public Main.Ship_type [] player_ships;
    public Player(int num, char icon){
        this.num = num;
        this.icon = icon;
        this.cool_down = 0;
        player_ships = new Main.Ship_type[] {Main.Ship_type.CARRIER, Main.Ship_type.BATTLESHIP, Main.Ship_type.CRUISER, Main.Ship_type.SUBMARINE, Main.Ship_type.DESTROYER };

    }
    public void update_player_ships(Main.Ship_type s){
        for(int i = 0; i < 5; i++){
            if(player_ships[i].equals(s)){
                player_ships[i] = Main.Ship_type.NOSHIP;
            }
        }
    }
}
