package cn.it.sales.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HexUtil {
	//md5加密
//	
//	public static void main(String[] args) {
//		String srt = getMd5("mhl");
//		System.out.println(srt);
//	}
	
	 public static String getMd5(String inStr)  
	    {  
	        String outStr=null;//输出项 为null
	        if(inStr ==null)  
	        {  
	            outStr = null;  
	        }  
	        else if("".equals(inStr))  
	        {  
	            outStr = "";  //空字符串
	        }  
	        else  
	        {  
	            try  
	            {  
	                MessageDigest md = MessageDigest.getInstance("MD5");  //随机生成md5进行加密
	                md.update(inStr.getBytes());  
	                byte b[] = md.digest();  
	                StringBuffer buf = new StringBuffer();  
	                for(int i=1;i<b.length;i++)  
	                {  
	                    int c = b[i]>>>4 & 0xf;  
	                    buf.append(Integer.toHexString(c));  
	                    c = b[i]& 0xf;  
	                    buf.append(Integer.toHexString(c));  
	                }  
	                outStr = buf.toString();  
	            }  
	            catch (NoSuchAlgorithmException e)  
	            {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        }  
	        return outStr;  
	    }  
}
