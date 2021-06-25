import java.io.*;
import java.util.Scanner;


public class FileManager {
	File file;
	String path;
	String fileName;
	String separator;
	
	public FileManager(String fileName){ //fileName: nome del file a cui accedere senza estensione
		this.fileName=fileName+".dati";
		this.path=getPath()+this.fileName;
		this.file=getFile(this.fileName);
	}
	public FileManager(){
		this.path=getPath();
		this.file=getFile("");
	}
	
	private String getPath(){
		separator=File.separator;
		return ".."+separator+"data"+separator;
	}
	
	private File getFile(String name){
		File f=new File(path);
		if(!f.exists()){
			System.out.print("File non trovato, creare "+name+" ? (s/n) ");
			Scanner sc=new Scanner(System.in);
			String ans=sc.nextLine().toLowerCase();
			while(!ans.equals("s")&&!ans.equals("n")){
				System.out.print("Input non valido, creare "+name+" ? (s/n) ");
				ans=sc.next().toLowerCase();
			}
			if(ans.equals("s")){
				f=newFile();
				System.out.print("File creato\n");
			}
			else if(ans.equals("n")){
				f=null;
				System.out.print("File non creato\n");
			}
		}
		return f;
	}
	
	public File newFile(){
		File f=new File(path);
		try{
			f.createNewFile();
		}
		catch(IOException e){
			System.out.print(e);
		}
		return f;
	}
	
	public String[] getFileList(){ //Restituisce un vettore con i nomi di tutti i file nella cartella
		String[] files=file.list();
		return files;
	}
	public void selectFile(){
		System.out.print("Quale file selezionare? ");
		Scanner sc=new Scanner(System.in);
		String ans=sc.nextLine();
		path=getPath()+ans;
		file=new File(path);
		if (file.exists()){
			deleteFile();
		}
		else{
			file=getFile(ans);
		}
	}
	public void deleteFile(){
		if(file.exists()){
			System.out.print("Eliminare "+file.getName()+" ? (s/n) ");
			Scanner sc=new Scanner(System.in);
			String ans=sc.nextLine().toLowerCase();
			while(!ans.equals("s")&&!ans.equals("n")){
				System.out.print("Input non valido, eliminare "+file.getName()+" ? (s/n) ");
				ans=sc.next().toLowerCase();
			}
			if(ans.equals("s")){
				file.delete();
				System.out.print("File eliminato\n");
			}
			else if(ans.equals("n")){
				System.out.print("File non eliminato\n");
			}
		}
	}
	
	public String[] read(int line){ //line: riga del file da leggere
		String row;
		try{
			String[] data=null;
			int i=0;
			BufferedReader br=new BufferedReader(new FileReader(file));
			while (( row = br.readLine()) != null) {
				if(i==line){
					data = row.split(",");
					break;
				}
				i++;
			}
			br.close();
			return data;
		}
		catch(IOException e1){
			System.out.print(e1);
		}
		catch(NullPointerException e1){
			System.out.print("File non trovato\n");
		}
		return null;
	}
	
	public void write(String[] input){ //input: dati da scrivere su file
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
			for(int i=0;i<input.length;i++){
				bw.write(input[i]+",");
			}
			bw.write("\n");
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
	
}
