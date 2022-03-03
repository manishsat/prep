/*
There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:

Eat one orange.
If the number of remaining oranges n is divisible by 2 then you can eat n / 2 oranges.
If the number of remaining oranges n is divisible by 3 then you can eat 2 * (n / 3) oranges.
You can only choose one of the actions per day.

Given the integer n, return the minimum number of days to eat n oranges.

Leet code 1553 - HARD
*/

public class EatOranges {
    public static void main(String[] args) {
        EatOranges e = new EatOranges();
        System.out.println(e.minDays(10));
        System.out.println(e.dp(10));
    }
    /*
    conditions
    1) You can eat 1 orange a day
    2) if no of oranges /2 then you can eat half oranges
    3) if no of oranges / 3 than you can eat 2/3 of oranges
    */
    public int minDays(int oranges) {
        
        Map<Integer, Integer> orangeToDays = new HashMap<Integer, Integer>();
        orangeToDays.put(0,0);
        orangeToDays.put(1,1);
        
        return dfs(oranges, orangeToDays);
        
    }
    //at every step we are dividing by /2 or by /3 so O(log N) base 3
    public int dfs (int oranges, Map<Integer, Integer> orangeToDays) {
        if(orangeToDays.containsKey(oranges)) {
            return orangeToDays.get(oranges);
        }
        
        //choices
    
        int first = 1 + (oranges % 2) + dfs(oranges/2, orangeToDays); // if odd how to make even so 1 -> to eat half and %2 will be 1 and rest half passed down
        int two = 1 + (oranges % 3) + dfs(oranges/3, orangeToDays); // 1 is to eat 2/3 and pass down 1/3 to dfs
        int min = Math.min(first, two);
        orangeToDays.put(oranges, min);
        
        return min;
        
    }
    
    //O(N) because we are following top down approach
    public int dp(int oranges) {
        
        int[] dpTable = new int [oranges + 1];
        Arrays.fill(dpTable, oranges);
        dpTable[0] = 0;
        dpTable[1] = 1;
        
        //we have trying to calculate best time take to eat all n oranges it is a linear approach
        //Top down approach
        for(int i = 2; i <= oranges; i++) {
            //We have 2 choices first we can make n oranges divisible by 2 and second divisible by 3
            //Take example n = 10 1) 10/2-> 2) 10/3 so at every step we have 2 choices
            int firstChoice = 1 + dpTable[i%2] + dpTable[i/2];
            int secondChoice = 1 + dpTable[i%3] + dpTable[i/3];
            dpTable[i] = Math.min(firstChoice, secondChoice);
        }

        return dpTable[oranges];
    }
}
