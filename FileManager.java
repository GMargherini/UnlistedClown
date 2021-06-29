package FileManager;
import java.io.*;
import java.util.*;


public class FileManager {
	File file;
	String path;
	String fileName;
	String separator;
	
	public FileManager(String fileName){ //fileName: nome del file a cui accedere senza estensione
		this.fileName=fileName;
		this.path=getFilePath(this.fileName);
		this.file=getFile(this.fileName);
	}
	public FileManager(){
		this.path=getDirectoryPath();
		this.file=getFile("");
	}
	
	private String getDirectoryPath(){
		separator=File.separator;
		return ".."+separator+"data"+separator;
	}
	private String getFilePath(String name){
		separator=File.separator;
		return ".."+separator+"data"+separator+name+".dati";
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
				f=newFile(name);
				System.out.print("File creato\n");
			}
			else if(ans.equals("n")){
				f=null;
				System.out.print("File non creato\n");
			}
		}
		return f;
	}
	
//	private File newFile(){
//		File f=new File(path);
//		try{
//			f.createNewFile();
//		}
//		catch(IOException e){
//			System.out.print(e);
//		}
//		return f;
//	}
	public File newFile(String name){
		File f=new File(getFilePath(name));
		if(!f.exists()){
			try{
				f.createNewFile();
			}
			catch(IOException e){
				System.out.print(e);
			}
		}
		return f;
	}
	public void deleteFile(){
		if(file.exists()){
			System.out.print("Eliminare "+fileName+" ? (s/n) ");
			Scanner sc=new Scanner(System.in);
			String ans=sc.nextLine().toLowerCase();
			while(!ans.equals("s")&&!ans.equals("n")){
				System.out.print("Input non valido, eliminare "+fileName+" ? (s/n) ");
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
	
	public String[] getFileList(){ //Restituisce un vettore con i nomi di tutti file nella cartella
		String[] files=file.list();
		return files;
	}
	public void selectFile(){
		System.out.print("Quale file selezionare? ");
		Scanner sc=new Scanner(System.in);
		String ans=sc.nextLine();
		path=getFilePath(ans);
		file=new File(path);
		if (file.exists()){
			deleteFile();
		}
		else{
			file=getFile(ans);
		}
	}
	
	
	
}