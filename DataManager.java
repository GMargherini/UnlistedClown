package FileManager;

import java.io.*;

public class DataManager {
	File file;
	
	public DataManager(FileManager fm){
		try{
			file=fm.file;
			if(!file.exists())
				throw new FileNotFoundException();
		}
		catch(FileNotFoundException e){
			System.out.println("File non trovato");
		}
	}
	
	private static String[] split(String str){
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
	
	//restituisce la riga numero "line"
	public String[] read(int line){ 
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
	
	//scrive il contenuto di "input" alla fine del file
	public void write(String[] input){ 
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
	
	//se "append" è true aggiunge il contenuto di "input" alla riga "line", altrimenti la riscrive
	public void writeInLine(String[] input, int line, Boolean append){
		int len=input.length-1,i=0;
		String row;
		try{
			BufferedReader br=new BufferedReader(new FileReader(file));
			StringBuilder sb=new StringBuilder();
			while (( row=br.readLine()) != null ) {
				if(append || i!=line){
					sb.append(row);
				}
				if(i==line){
					if(append)
						sb.append(",");
					for( int j=0;j<len;j++){
						sb.append(input[j]);
						sb.append(",");
					}
					sb.append(input[len]);
				}
				sb.append("\n");
				i++;
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(file,false));
			bw.write(sb.toString());
			bw.close();
		}
		catch(IOException e){
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("File non trovato");
		}
	}
	
	//cancella la riga numero "line"
	public void deleteLine(int line){
		int i=0;
		String row;
		try{
			BufferedReader br=new BufferedReader(new FileReader(file));
			StringBuilder sb=new StringBuilder();
			while (( row=br.readLine()) != null ) {
				if(i!=line){
					sb.append(row);
					sb.append("\n");
				}
				i++;
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(file,false));
			bw.write(sb.toString());
			bw.close();
		}
		catch(IOException e){
			System.out.println("Errore di Input/Output");
		}
		catch(NullPointerException e1){
			System.out.println("File non trovato");
		}
	}
	
	//restituisce il numero di righe nel file
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
	
	//restituisce il primo elemento dell'ultima riga
	public String lastID(){
		return read(getLineCount()-1)[0];
	}
	
	//restituisce il numero della riga in cui si trova "id"
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
	
	//restituisce la prima colonna del file
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
