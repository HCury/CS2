//Henrique Cury

import java.io.*;

public class Hw03 {

	public static void main(String[] args) throws IOException
	{
		int hash = 0;
		BufferedReader sc = new BufferedReader(new FileReader(args[0]));
		String inString = sc.readLine();

		while (inString != null)
		{
			int len = inString.length();
			hash = UCFxram(inString, len);
			System.out.format("%10x:%s\n", hash, inString);
			inString = sc.readLine();
		}
		System.out.println("Input file processed");
		sc.close();
	}

	public static int UCFxram(String inString, int len)
	{
		int arbitraryVal = 0xbcde98ef;
		int ArbitryaryVal = 0x7890face;
		int hash = 0xfa01bc96;
		int roundedEnd = len & 0xfffffffc;
		int x = 0;

		for (int i = 0; i < roundedEnd; i = i+4)
		{
			x = (inString.charAt(i) & 0xff) | ((inString.charAt(i + 1) & 0xff) << 8) | ((inString.charAt(i + 2) & 0xff) << 16) | (inString.charAt(i + 3) << 24);
			x = x * arbitraryVal;
			x = Integer.rotateLeft(x, 12);
			x = x * ArbitryaryVal;
			hash = hash ^ x;
			hash = Integer.rotateLeft(hash, 13);
			hash = hash * 5 + 0x46b6456e;
		}

		x = 0;
		
		if ((len & 0x03) == 3)
		{
			x = (inString.charAt(roundedEnd + 2) & 0xff) << 16;
			len = len - 1;
		}
		if ((len & 0x03) == 2)
		{
			x |= (inString.charAt(roundedEnd + 1) & 0xff) << 8;
			len = len - 1;
		}
		if ((len & 0x03) == 1)
		{
			x |= (inString.charAt(roundedEnd) & 0xff);
			x = x * arbitraryVal;
			x = Integer.rotateLeft(x, 14);
			x = x * ArbitryaryVal;
			hash = hash ^ x;
		}

		hash = hash ^ len;
		hash = hash & 0xb6acbe58;
		hash = hash ^ hash >>> 13;
		hash = hash * 0x53ea2b2c;
		hash = hash ^ hash >>> 16;
		return hash;
	}

	public void complexityIndicator()
	{
		System.err.println("he200230");
		System.err.println("Difficulty: 2");
		System.err.println("Hours: 10");
	}

}
