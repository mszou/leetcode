/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function may be called multiple times.
 */

public class Solution extends Reader4 {
	// idea: use buffer pointer (buffPtr) & buffer Counter (buffCnt) to store the data received in previous calls.
	// In the while loop, if buffPtr reaches current buffCnt, reset it as zero to be ready to read new data later.
	private int buffPtr = 0;
	private int buffCnt = 0;
	private char[] buff4 = new char[4];	// buffer for every read4

	public int read(char[] buf, int n) {	// read n chars from the file into buf
		int ptr = 0;	// pointer for read in chars, starting from 0
		while (ptr < n) {
			if (buffPtr == 0) {
				buffCnt = read4(buff4);	// read 4 into buff4
			}
			while (ptr < n && buffPtr < buffCnt) {
				buf[ptr++] = buff4[buffPtr++];
			}
			// all chars in buff4 used up, set pointer to 0
			if (buffPtr == buffCnt) {
				buffPtr = 0;
			}
			// read4 returns less than 4, end of file
			if (buffCnt < 4) {
				break;
			}
		}
		return ptr;
	}
}