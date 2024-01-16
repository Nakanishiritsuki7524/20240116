package DummyFile;

public class InputCheck {

	private String str;				
	//ここで入る整数をif文で書いているがいらないような気がしたから消した
	public InputCheck(String str) {					
	this.str = str;	//(str = str)これを（this.str = str）にした
	}				
					
protected boolean isNumeric() {				
	//ここのthis.str==""を変えた
	if (this.str == null||this.str.isEmpty()) {				
		return false;			
	}				
					
	for (int i = 0; i < this.str.length(); i++)	{				
		char c = this.str.charAt(i);			
		if (c < '0' || c > '9') {			
			return false;		
		}			
	}				
	return true;				
}					
					
protected boolean checkSize() {					
	if(this.str.length() <= 10) {				
		if(Math.abs(Integer.parseInt(this.str)) <= 1024*1024*100) {			
			return true;		
		}else//ここにelseが必要と思った（ifなのにelseが無かったため）		
		return false;			
	}				
	else {				
		return false;			
	}				
}					
}					
					

