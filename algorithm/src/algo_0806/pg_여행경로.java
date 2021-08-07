package algo_0806;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_여행경로 {
	static String[] answer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
//		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] tickets = {{"ICN", "BBB"},{"ICN", "CCC"},{"BBB", "CCC"},{"CCC", "BBB"},{"CCC", "ICN"}};
		System.out.println(Arrays.toString(solution(tickets)));
		
	}
	static public String[] solution(String[][] tickets) {
        Queue<String>[] list = new PriorityQueue[10000];
        int size = 0;
        Map<String, Integer> airport = new HashMap<String, Integer>();
        
        for(int i = 0; i < tickets.length; i++){
            if(!airport.containsKey(tickets[i][0])){
                airport.put(tickets[i][0], size);                
                list[size] = new PriorityQueue<String>();
                size++;
            }
            list[airport.get(tickets[i][0])].offer(tickets[i][1]);
        }
  
        answer = new String[tickets.length + 1];
        answer[0] = "ICN";
        int index = 0;
        
        for(int i = 0; i < tickets.length; i++){
            answer[i+1] = list[index].poll();
            //System.out.println(airport.get(answer[i+1]));
            if(i < tickets.length-1) {
	            if(airport.containsKey(answer[i+1])) {
		            if(list[airport.get(answer[i+1])].size() != 0){
		                index = airport.get(answer[i+1]);
		            }else{
	                    String tmp = list[index].poll();
	                    list[index].offer(answer[i+1]);
	                    answer[i+1] = tmp;
	                    index = airport.get(tmp);
	                }
	            }
            }
            System.out.println(answer[i+1]);
        }
        
        return answer;
    }
}
