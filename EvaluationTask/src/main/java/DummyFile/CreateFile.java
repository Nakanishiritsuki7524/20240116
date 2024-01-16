package DummyFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;						
						
class CreateFile {						
	//ここはエラーがなしかな？
	protected static boolean createDummyFile(int byteSize) {					
		File file = new File("./DummyFile");				
		file.delete();				
		try(RandomAccessFile rFile = new RandomAccessFile(file.getPath(), "rw")){				
			rFile.setLength(byteSize);		
			rFile.close();
		} catch (IOException e) {				
			e.printStackTrace();			
			return false;			
		}									
	return true;				
	}					
}						
						