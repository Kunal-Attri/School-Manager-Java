package utilpkg;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Util {
	
	public static String formatAsTable(List<List<String>> rows)
	{
	    int[] maxLengths = new int[rows.get(0).size()];
	    for (List<String> row : rows)
	    {
	        for (int i = 0; i < row.size(); i++)
	        {
	            maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
	        }
	    }

	    StringBuilder formatBuilder = new StringBuilder();
	    for (int maxLength : maxLengths)
	    {
	        formatBuilder.append("%-").append(maxLength + 2).append("s");
	    }
	    String format = formatBuilder.toString();

	    StringBuilder result = new StringBuilder();
	    for (List<String> row : rows)
	    {
	        result.append(String.format(format, row.toArray())).append("\n");
	    }
	    return result.toString();
	}
	
	public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            byte[] messageDigest = md.digest(input.getBytes());
  
            BigInteger no = new BigInteger(1, messageDigest);
  
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
  
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
