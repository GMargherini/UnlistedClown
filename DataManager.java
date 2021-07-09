package FileManager;

import java.io.*;

public class DataManager {
	File file;
	public DataManager(FileManager fm){
		file=fm.file;
	}
	
	private String[] split(String str){
		int i,index,num=0;
		for(i=0;i<str.length();i++){
			if(str.charAt(i)==(',')){
				num++;
			}
		}
		String[] data=new String[num+1];
			for(i=0;i<num;i++){
				index=str.indexOf(',');
				if(index>0){
					data[i]=str.substring(0,index);
					str=str.substring(index+1,str.length());
				}
			}
			data[num]=str;
		return data;
	}
	
	public String[] read(int line){ //line: riga del file da leggere
		String row;
		try{
			String[] data;
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
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("Fine file superata");
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
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("File non trovato");
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
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("File non trovato");
		}
		return i;
	}
	
	public String lastID(){
		return read(getLineCount()-1)[0];
	}
	
	public int getLine(String id){
		int i=0,j;
		String row;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((row=br.readLine()) != null){
				j=row.indexOf(',');
				if(row.substring(0,j).equals(id)){
					return i;
				}
				i++;
			}
			return -1;
		}
		catch(IOException e){
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("File non trovato");
		}
		catch(IndexOutOfBoundsException e2){
			System.out.println("Errore nella lettura del file");
		}
		return -1;
	}
	
	public String[] firstColumn(){
		int i=0,j;
		String row;
		String[] data= new String[getLineCount()];
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((row=br.readLine()) != null){
				j=row.indexOf(',');
				data[i]=row.substring(0,j);
				i++;
			}
			return data;
		}
		catch(IOException e){
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("File non trovato");
		}
		catch(IndexOutOfBoundsException e2){
			System.out.println("Errore nella lettura del file");
		}
		return data;
	}
}
