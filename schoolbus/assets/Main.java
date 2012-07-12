import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

enum States{
	NORMAL,
	DES,
}

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		try{
		File inputFile = new File("E://resources//develops//online//sql//数据.txt");
		File outputFileMainSummer = new File("E://resources//develops//online//sql//main_summer.sql");
		File outputFileSearch = new File("E://resources//develops//online//sql//search.sql");
		File outputFileMainWinter = new File("E://resources//develops//online//sql//main_winter.sql");

		outputFileMainSummer.delete();
		outputFileSearch.delete();
		outputFileMainWinter.delete();
		
		outputFileMainSummer.createNewFile();
		outputFileSearch.createNewFile();
		outputFileMainWinter.createNewFile();
		
		FileInputStream inputFileI = new FileInputStream(inputFile);
		InputStreamReader reader = new InputStreamReader(inputFileI);
		BufferedReader br = new BufferedReader(reader);
		
		FileOutputStream ops1 = new FileOutputStream(outputFileMainSummer);
		FileOutputStream ops2 = new FileOutputStream(outputFileSearch);
		FileOutputStream ops3 = new FileOutputStream(outputFileMainWinter);
		OutputStreamWriter w1 = new OutputStreamWriter(ops1);
		OutputStreamWriter w2 = new OutputStreamWriter(ops2);
		OutputStreamWriter w3 = new OutputStreamWriter(ops3);
		BufferedWriter bw1 = new BufferedWriter(w1);
		BufferedWriter bw2 = new BufferedWriter(w2);
		BufferedWriter bw3 = new BufferedWriter(w3);
		
		String inLine;
		String[] arr,tmp,tmp2;
		States state = States.NORMAL;
		StringBuilder sb ,sb2,sb3,tb;
		String time[];
		int id =1,wordDex;
//		inLine = br.readLine();
		while((inLine=br.readLine()) != null){
			//排除所有注释，空行以及只包含制表符的空行
			if(inLine.startsWith(";") || inLine.isEmpty()||inLine.replace("\t", "").trim().isEmpty()) continue;
			wordDex = -1;
			time = new String[2];
			sb = new StringBuilder();
			sb2 = new StringBuilder();
			tb = new StringBuilder();
			sb.append("insert into summer_time values(");
			sb.append(id);
			sb.append(",");
			arr = inLine.split(" |\t");
			for(int j=0;j<arr.length;j++){
				String word = arr[j];
				wordDex ++;
				switch(wordDex){
				case 0:
				case 2:
					if(word.startsWith("n")){ sb.append("null,"); continue;}
					tmp = word.split("d");
					sb.append("'");
					sb.append(tmp[0]);
					sb.append("',");
					if(tmp.length <2) sb.append("null,");
					else {
						sb.append("'");
						sb.append(tmp[1]);
						sb.append("',");
					}
					tb.append(word);
					if(wordDex==0)	tb.append(",");
					break;
				case 1:
					if(word.startsWith("n")){ sb.append("null,"); continue;}
					sb.append("'");
					sb.append(word.replaceAll("d",""));
					sb.append("',");
					tb.append(word);
					tb.append(",");
					break;
				case 3:
					//
					if(word.startsWith("n")){ sb.append("null,"); continue;}
					sb.append("'");
					sb.append(word);
					sb.append("',");
					time[0] = word;
					String times[] = word.split(":");
					int hour =Integer.parseInt(times[0]);
					int minute =Integer.parseInt(times[1]); 
					if( hour>12 || hour==12 &&  minute>30){
						System.out.println("=-==-=-=======================");
						minute -= 30;
						if(minute < 0) {
							hour --;
							minute +=60;
						}
//						System.out.println(time[1]);
						time[1] = hour +":"+ (minute<10 ? ("0"+minute) :minute);
					}
					break;
				case 4:
					if(word.startsWith("n")){ sb.append("null,"); continue;}
					sb.append("'");
					sb.append(word);
					sb.append("',");
					break;
				case 5:
					if(word.startsWith("n")){ sb.append("null,"); continue;}
					sb.append(word);
					sb.append(",");
					break;
				case 6:
					if(word.startsWith("n")){ sb.append("null);\n"); continue;}
					sb.append("'");
					sb.append(word);
					sb.append("');");
					break;
				}
			}
			
			tmp = tb.toString().split(",");
			for(int a =0;a<tmp.length;a++){
				for(int b=a+1;b<tmp.length;b++){
					if(tmp[a].split("d")[0].equals(tmp[b].split("d")[0])) continue;
					sb2.append("insert into search_table values(");
					sb2.append(id);
					sb2.append(",");
					sb2.append("'");
					sb2.append(tmp[a].split("d")[0]);
					sb2.append("',");
					sb2.append("'");
					sb2.append(tmp[b].split("d")[0]);
					sb2.append("');\n");
				}
			}
			
			id++;
			
			String s3 = sb.toString();
			s3 = s3.replace("summer", "winter");
			if(time[1] != null)	s3 = s3.replace(time[0], time[1]);
//			System.out.println(time[0]+" "+time[1]);
			System.out.println(inLine);
			System.out.println(sb);
			System.out.println(sb2);
			System.out.println(s3);
			
			bw1.append(sb);
			bw2.append(sb2);
			bw3.append(s3);
			bw1.flush();
			bw2.flush();
			bw3.flush();
		}

		bw1.close();
		bw2.close();
		bw3.close();
		w1.close();
		w2.close();
		w3.close();
		ops1.close();
		ops2.close();
		br.close();
		reader.close();
		inputFileI.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
