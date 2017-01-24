import java.util.*;

public class PageSplit {
// idea: transform the String[] to a List<String> and use iterator to traverse the data,
// use a Set to check the uniqueness of hostId. Every time we print a line of data, remove
// it from the list. when we got 12 lines for this page or the iterator reaches the end,
// turn to next page and let the iterator traverse from the beginning.
	public void splitPage(String[] input, int k) {
//		List<List<String>> res = new ArrayList<List<String>>();
		if (input == null || input.length == 0) {
			return;
		}
		List<String> data = new ArrayList<>(Arrays.asList(input));
		Set<String> visited = new HashSet<>();
		Iterator<String> itr = data.iterator();
		int pageNum = 1;
		System.out.println("Page " + pageNum);
		while (itr.hasNext()) {
			String curr = itr.next();
			String hostId = curr.split(",")[0];
			if (!visited.contains(hostId)) {
				System.out.println(curr);
				visited.add(hostId);
				itr.remove();
			}
			// new page
			if (visited.size() == k || !itr.hasNext()) {
				visited.clear();
				itr = data.iterator();
				if (!data.isEmpty()) {
					pageNum++;
					System.out.println();
					System.out.println("Page " + pageNum);
				}
			}
		}
	}

	
	public static void main(String[] args) {
		PageSplit p = new PageSplit();
        String[] data = new String[]{
                "1,28,300.1,SanFrancisco",
                "4,5,209.1,SanFrancisco",
                "20,7,208.1,SanFrancisco",
                "23,8,207.1,SanFrancisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,SanFrancisco",
                "6,29,204.1,SanFrancisco",
                "7,20,203.1,SanFrancisco",
                "8,21,202.1,SanFrancisco",
                "2,18,201.1,SanFrancisco",
                "2,30,200.1,SanFrancisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,SanJose",
                "6,25,10.1,Oakland",
                "19,15,9.1,SanJose",
                "3,19,8.1,SanJose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,SanJose",
                "5,6,3.1,SanJose",
                "29,22,2.1,SanJose",
        };
//        List<String> input = new ArrayList<>(Arrays.asList(data));
        p.splitPage(data, 12);
//        List<List<String>> result = p.splitPage(data, 10);
//        for(int i = 0; i < result.size(); i++) {
//            System.out.println(i);
//            List<String> tem = result.get(i);
//            for(String s: tem) {
//                System.out.println(s);
//            }
//        }
        
	}
}
