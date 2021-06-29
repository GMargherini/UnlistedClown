package FileManager;

import java.io.*;
import java.util.*;

public class DataManager {
	File file;
	public DataManager(FileManager fm){
		file=fm.file;
	}
	
	private String[] split(String str){
		String[] data=new String[6];
		String[] split;
		int i;
		StringTokenizer st=new StringTokenizer(str,",");
		for(i=0;st.hasMoreTokens();i++){
			data[i]=st.nextToken();
		}
		split=new String[i];
		System.arraycopy(data, 0, split, 0, i);
		return split;
	}
	
	public String[] read(int line){ //line: riga del file da leggere
		String row;
		try{
			String[] data=null;
			int i=0;
			BufferedReader br=new BufferedReader(new FileReader(file));
			if(line==0){
				row=br.readLine();
				data=split(row);
				br.close();
				return data;
			}
			while (( br.readLine()) != null && i<line-1) {
				i++;
			}
			row=br.readLine();
			data=split(row);
			br.close();
			return data;
		}
		catch(IOException e1){
			System.out.print(e1);
		}
		catch(NullPointerException e1){
			System.out.print("Fine file superata\n");
		}
		return null;
	}

	public void write(String[] input){ //input: dati da scrivere su file
		int len=input.length;
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
			for(int i=0;i<len-1;i++){
				bw.write(input[i]+",");
			}
			bw.write(input[len-1]+"\n");
			bw.close();
		}
		catch(IOException e){
			System.out.print(e);
		}
		catch(NullPointerException e1){
			System.out.print("File non trovato\n");
		}
	}
	
	public int getLineCount(){
		int i=0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((br.readLine()) != null){
				i++;
			}
		}
		catch(IOException e){
			System.out.print(e);
		}
		catch(NullPointerException e1){
			System.out.print("File non trovato\n");
		}
		return i;
	}
	
	public String lastID(){
		return read(getLineCount()-1)[0];
	}
	
	public int getLine(String id){ //restituisce la linea in cui compare id
		int i=0,j;
		String row;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((row=br.readLine()) != null){
				i++;
				j=row.indexOf(',');
				if(row.substring(0,j).equals(id)){
					return i;
				}
			}
		}
		catch(IOException e){
			System.out.print(e);
		}
		catch(NullPointerException e1){
			System.out.print("File non trovato\n");
		}
		catch(IndexOutOfBoundsException e2){
			System.out.print("Errore nella lettura del file");
		}
		return i;
	}
}
