package DummyFile;

import java.util.Scanner;

public class DummyFile {
							
		public static void main(String[] args) {								
		//ここのScannerはインポートすると治る
		try(Scanner scan = new Scanner(System.in)){								
			System.out.println("作成するファイルの100MB以下のバイト数(104857600)を入力してください。");							
			String str = scan.nextLine();							
										
			 InputCheck ic = new InputCheck(str);
	            if (ic.isNumeric() && ic.checkSize()) {
	                int fileSize = Math.abs(Integer.parseInt(str));
	                if (CreateFile.createDummyFile(fileSize)) {
	                    String outPutFileSize = "";
	                    if (fileSize >= (1024 * 1024)) {
	                        outPutFileSize = fileSize / (1024 * 1024) + "MB";
	                    } else if (fileSize >= 1024) {
	                        outPutFileSize = fileSize / 1024 + "KB";
	                    } else {
	                        outPutFileSize = fileSize + "B";
	                    }
	                    System.out.println(outPutFileSize + "のファイルを出力しました。");
	                } else {
	                    System.out.println("エラー：ファイル作成に失敗しました。");
	                }
	            } else {
	                System.out.println("エラー：数値のみ入力してください。");
				}
			}
		}
	}


													
										
