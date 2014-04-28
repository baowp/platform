package com.abbcc.util;

public class luncen {
	public static char getInitial(String str)
	 {
	  byte[] bs;
	  try
	  {
	   bs=str.getBytes("utf-8");
	  }
	  catch(java.io.UnsupportedEncodingException e)
	  {
	   return '*';
	  }
	  
	  if(bs.length==0)
	   return '*';
	   
	  switch(bs[0])
	  {
	   case (byte)'a': case (byte)'A': return 'a';
	   case (byte)'b': case (byte)'B': return 'b';
	   case (byte)'c': case (byte)'C': return 'c';
	   case (byte)'d': case (byte)'D': return 'd';
	   case (byte)'e': case (byte)'E': return 'e';
	   case (byte)'f': case (byte)'F': return 'f';
	   case (byte)'g': case (byte)'G': return 'g';
	   case (byte)'h': case (byte)'H': return 'h';
	   case (byte)'i': case (byte)'I': return 'i';
	   case (byte)'j': case (byte)'J': return 'j';
	   case (byte)'k': case (byte)'K': return 'k';
	   case (byte)'l': case (byte)'L': return 'l';
	   case (byte)'m': case (byte)'M': return 'm';
	   case (byte)'n': case (byte)'N': return 'n';
	   case (byte)'o': case (byte)'O': return 'o';
	   case (byte)'p': case (byte)'P': return 'p';
	   case (byte)'q': case (byte)'Q': return 'q';
	   case (byte)'r': case (byte)'R': return 'r';
	   case (byte)'s': case (byte)'S': return 's';
	   case (byte)'t': case (byte)'T': return 't';
	   case (byte)'u': case (byte)'U': return 'u';
	   case (byte)'v': case (byte)'V': return 'v';
	   case (byte)'w': case (byte)'W': return 'w';
	   case (byte)'x': case (byte)'X': return 'x';
	   case (byte)'y': case (byte)'Y': return 'y';
	   case (byte)'z': case (byte)'Z': return 'z';
	   default:
	   if(bs.length>=2) //长度>=2就有可能是中文
	   {
	    int b1=bs[0]&0xff;
	    int b2=bs[1]&0xff;
	    int value=(b1<<8)|b2;
	    System.out.println(value);
	    if( value>=0xb0a1 && value<=0xb0c4) return 'a';
	    if( value>=0xb0c5 && value<=0xb2c0) return 'b';
	    if( value>=0xb2c1 && value<=0xb4ed) return 'c';
	    if( value>=0xb4ee && value<=0xb6e9) return 'd';
	    if( value>=0xb6ea && value<=0xb7a1) return 'e';
	    if( value>=0xb7a2 && value<=0xb8c0) return 'f';
	    if( value>=0xb8c1 && value<=0xb9fd) return 'g';
	    if( value>=0xb9fe && value<=0xbbf6) return 'h';
	    if( value>=0xbbf7 && value<=0xbfa5) return 'j';
	    if( value>=0xbfa6 && value<=0xc0ab) return 'k';
	    if( value>=0xc0ac && value<=0xc2e7) return 'l';
	    if( value>=0xc2e8 && value<=0xc4c2) return 'm';
	    if( value>=0xc4c3 && value<=0xc5b5) return 'n';
	    if( value>=0xc5b6 && value<=0xc5bd) return 'o';
	    if( value>=0xc5be && value<=0xc6d9) return 'p';
	    if( value>=0xc6da && value<=0xc8ba) return 'q';
	    if( value>=0xc8bb && value<=0xc8f5) return 'r';
	    if( value>=0xc8f6 && value<=0xcbf9) return 's';
	    if( value>=0xcbfa && value<=0xcdd9) return 't';
	    if( value>=0xcdda && value<=0xcef3) return 'w';
	    if( value>=0xcef4 && value<=0xd188) return 'x';
	    if( value>=0xd1b9 && value<=0xd4d0) return 'y';
	    if( value>=0xd4d1 && value<=0xd7f9) return 'z';
	   }
	  }
	  
	  return '*';
	 } 
	public static void main(String[] args){
		String val="";
		for(int i=0;i<100;i++){
			val+="\""+i+"等奖\",";
		}
		System.out.println("["+val+"]");
	}
}
