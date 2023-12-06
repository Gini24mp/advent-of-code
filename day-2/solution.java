import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class solution {

    public static boolean possible(HashMap<String, Integer> GameMap){
        int blue = GameMap.get("blue");
        int red = GameMap.get("red");
        int green = GameMap.get("green");

        int redMax = 12;
        int greenMax = 13;
        int blueMax = 14;

        return !(blue > blueMax || red > redMax || green > greenMax);
    }

    public static void processGameTurn(String turn, HashMap<String, Integer> GameMap){
        String[] colorsArr = turn.split(",");

        for(int i = 0; i < colorsArr.length; i++){
            String color = colorsArr[i].replaceAll("[^a-zA-Z]", "");
            int num = Integer.parseInt(colorsArr[i].replaceAll("[^0-9]", ""));
            GameMap.put(color, GameMap.get(color) + num);
        }

    }

    public static int StartGameStrProcessing(String test){
        String TestArr[] = test.split(":");
        int GameNum = Integer.parseInt(TestArr[0].replaceAll("[^0-9]", ""));

        String GameTurns[] = TestArr[1].split(";");

        for(int i = 0; i < GameTurns.length; i++){
            HashMap<String, Integer> GameMap = new HashMap<String, Integer>();
            GameMap.put("blue", 0);
            GameMap.put("red", 0);
            GameMap.put("green", 0);            
            processGameTurn(GameTurns[i], GameMap);
            if(!possible(GameMap)){
                return 0;
            }
        }

        return GameNum;
    }


    public static void main(String[] args){
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int total = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();   
                int subTotal = StartGameStrProcessing(data);
                total += subTotal;
            }
            myReader.close();
            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println("File Not found.");
            e.printStackTrace();
        }

    }
    
}
