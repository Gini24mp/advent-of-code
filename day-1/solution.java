import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class solution {

    public static void processMatch(String MutableStr, String original, Map<String, List<Integer>> map, String regex, String key){
        List<Integer> list = new LinkedList<>();

        String RegArr[] = regex.split("\\|");

        MutableStr = updateMutableStr(list, MutableStr, original, RegArr[0], RegArr[1]);

        updateMap(map,key, list);

    }

    public static String updateMutableStr(List<Integer> list,String MutableStr,String original, String FirstReg, String SecondReg){
        if(MutableStr.matches(FirstReg)){
            String reg = FirstReg.substring(FirstReg.indexOf("(") + 1, FirstReg.indexOf(")"));
            updateList(list, MutableStr, original,reg);
            MutableStr = MutableStr.replaceFirst(reg, "");

        }
        
        if(MutableStr.matches(SecondReg)){
            String reg = SecondReg.substring(SecondReg.indexOf("(") + 1, SecondReg.indexOf(")"));
            updateList(list, MutableStr, original, reg);
            MutableStr = MutableStr.replaceFirst(reg, "");
        }

        return original;
    }

    public static void updateList(List<Integer> list,String MutableStr,String original, String reg){
        int first = original.indexOf(reg);
        int last = original.lastIndexOf(reg);

        if(list.size() == 2){
            if(first < list.get(0)){
                list.set(0, first);
            }

            if(last > list.get(1)){
                list.set(1, last);
            }

        }else{
            list.add(first);
            list.add(last);
        }

    }

    public static void updateMap(Map<String, List<Integer>> map, String key, List<Integer> list){
        if(map.containsKey(key)){
            List<Integer> temp = map.get(key);
            if(temp.get(0).compareTo(list.get(0))<0){
                list.set(0, temp.get(0));
            }
            if(temp.get(1).compareTo(list.get(1))>0){
                list.set(1, temp.get(1));
            }
            map.replace(key, list);
        }else{
            map.put(key, list);
        }

    }

    public static void processVisitation(Map<String, Integer> VisitorCounterMap, Boolean[] flags, String key){
        
        if(VisitorCounterMap.containsKey(key)){
            VisitorCounterMap.replace(key, VisitorCounterMap.get(key)+1);
        }else{
            VisitorCounterMap.put(key, 1);
        }

        if(VisitorCounterMap.get(key) == 2){
            flags[Integer.parseInt(key)-1] = true;
        }
    }

    public static int processString(String MutableStr, String original, Map<String, List<Integer>> map ,Boolean[] flags, Map<String, Integer> VisitorCounterMap ){

        if(MutableStr.matches(".*(one).*|.*(1).*")){

            processMatch(MutableStr, original, map, ".*(one).*|.*(1).*", "1");
            
        }

        processVisitation(VisitorCounterMap, flags, "1");
        
        if(MutableStr.matches(".*(two).*|.*(2).*")){

            processMatch(MutableStr, original, map, ".*(two).*|.*(2).*", "2");

        }

        processVisitation(VisitorCounterMap, flags, "2");
        
        if(MutableStr.matches(".*(three).*|.*(3).*")){

            processMatch(MutableStr, original, map, ".*(three).*|.*(3).*", "3");

        }

        processVisitation(VisitorCounterMap, flags, "3");
        
        if(MutableStr.matches(".*(four).*|.*(4).*")){

            processMatch(MutableStr, original, map, ".*(four).*|.*(4).*", "4");

        }

        processVisitation(VisitorCounterMap, flags, "4");
        
        if(MutableStr.matches(".*(five).*|.*(5).*")){

            processMatch(MutableStr, original, map, ".*(five).*|.*(5).*", "5");

        }

        processVisitation(VisitorCounterMap, flags, "5");

        if(MutableStr.matches(".*(six).*|.*(6).*")){

            processMatch(MutableStr, original, map, ".*(six).*|.*(6).*", "6");

        }

        processVisitation(VisitorCounterMap, flags, "6");

        if(MutableStr.matches(".*(seven).*|.*(7).*")){

            processMatch(MutableStr, original, map, ".*(seven).*|.*(7).*", "7");

        }

        processVisitation(VisitorCounterMap, flags, "7");
        
        if(MutableStr.matches(".*(eight).*|.*(8).*")){

            processMatch(MutableStr, original, map, ".*(eight).*|.*(8).*", "8");

        }

        processVisitation(VisitorCounterMap, flags, "8");
        
        if(MutableStr.matches(".*(nine).*|.*(9).*")){

            processMatch(MutableStr, original, map, ".*(nine).*|.*(9).*", "9");

        } 

        processVisitation(VisitorCounterMap, flags, "9");

        for(int i = 0; i<flags.length; i++){
            if(flags[i] == false){
                return processString(MutableStr, original, map, flags, VisitorCounterMap);
            }
        }
        
        if(map.size() == 0){

            return 0;
        }

        String MinKey = Collections.min(map.entrySet(), Comparator.comparingInt(e -> e.getValue().get(0))).getKey();
        
        String MaxKey = Collections.max(map.entrySet(), Comparator.comparingInt(e -> e.getValue().get(1))).getKey();
        
        String CombinedKeys = MinKey + MaxKey;

        return Integer.parseInt(CombinedKeys);
    }

    public static void main(String[] args) {

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int total = 0;
            while (myReader.hasNextLine()) {
                Map<String, List<Integer>> map = new HashMap<>();
                Map<String, Integer> VisitorCounterMap = new HashMap<>();
                Boolean[] flags = new Boolean[9];
                Arrays.fill(flags, Boolean.FALSE);
                String data = myReader.nextLine();   
                int subTotal = processString(data, data, map, flags, VisitorCounterMap);
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

