package SSHA;

import java.security.MessageDigest;

public class SHA1 {
	public static String getHashM(String m) {
		String msg= "";
		
		try {
			MessageDigest digest=  MessageDigest.getInstance("SHA-1");
			byte[] b= digest.digest(m.getBytes("UTF-8"));
			StringBuffer sb =  new StringBuffer();
			
			for(int i=0; i<b.length; i++) {
				String h= Integer.toHexString(0xff & b[i]);
				
				if(h.length()== 1) {
					sb.append('0');
				}
				
				sb.append(h);
			}
			
			msg= sb.toString();
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		return msg;
	}
}
