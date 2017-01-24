import java.util.*;
//用这个压缩一个ip地址区间，Classless Inter-Domain Routing (CIDR) Chart 比如给 7.7.7.7, 3 表示ip range 7.7.7.7-7.7.7.9, 返回相应的cidr

public class CIDR {
	static int[] CIDRmask = new int[32];
	
	public List<String> range2cidrlist(String startIp, long count, String endIp) {
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < 32; i++) {
			for (int j = 31; j > 31 - i; j--) {
				CIDRmask[i] = CIDRmask[i] | (1 << j);
			}
		}
		long start = ipToLong(startIp);
		long end = 0l;
		if (endIp.length() == 0) {
			end = start + count - 1;
		} else {
			end = ipToLong(endIp);
		}
		int maxSize;
		while (start <= end) {
			maxSize = 32;
			while (maxSize > 0) {
				long mask = CIDRmask[maxSize - 1];
				long afterMask = mask & start;
				if (afterMask != start) {
					break;
				}
				maxSize--;
			}
			double diff = Math.log(end - start + 1) / Math.log(2);
			int maxLength = 32 - (int) Math.floor(diff);
			maxSize = Math.max(maxSize, maxLength);
			res.add(longToIp(start) + "/" + maxSize);
			start += Math.pow(2, 32 - maxSize);
		}
		return res;
	}
	
	public long ipToLong(String ip) {
		String[] tokens = ip.split("\\.");
		return (Long.parseLong(tokens[0]) << 24) + (Long.parseLong(tokens[1]) << 16) + (Long.parseLong(tokens[2]) << 8) + (Long.parseLong(tokens[3]));
	}
	
	public String longToIp(long longIP) {
		StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(longIP >>> 24));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x000000FF)));
        return sb.toString();
	}

	public static void main(String[] args) {
		CIDR c = new CIDR();
		List<String> res = c.range2cidrlist("123.0.0.2",3, "124.2.2.1"); //0111, 1000, 1001
        for (String ss : res) {
            System.out.println(ss);
        }
	}

}
