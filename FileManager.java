package FileManager;

import java.io.*;

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
			f=newFile(name);
		}
		else{
			System.out.print("file "+name+" non trovato");
		}
		return f;
	}
	
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
	public void deleteFile(String name){
		File f=new File(getFilePath(name));
		if(f.exists()){
			f.delete();
		}
	}
	
	public String[] getFileList(){ //Restituisce un vettore con i nomi di tutti file nella cartella
		String[] files=file.list();
		return files;
	}
	public File selectFile(String name){
		File f=new File(getFilePath(name));
		if (!f.exists()){
			f=getFile(name);
		}
		return f;
	}
}
