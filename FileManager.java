package FileManager;

import java.io.*;

public class FileManager {
	File file;
	String path;
	String fileName;
	String separator;
	
	//accede al file "filename"
	public FileManager(String fileName){
		this.fileName=fileName;
		this.path=getFilePath(this.fileName);
		this.file=getFile(this.fileName);
	}
	
	//accede alla cartella "data"
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
		return f;
	}
	
	//crea un nuovo file chiamato "name.dati"
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
	
	//elimina il file "name"
	public void deleteFile(String name){
		File f=new File(getFilePath(name));
		if(f.exists()){
			f.delete();
		}
	}
	
	//restituisce un vettore con i nomi di tutti file nella cartella
	public String[] getFileList(){ 
		File f=new File(getDirectoryPath());
		String[] files=f.list();
		return files;
	}
	
	//restituisce il file "name" se esiste
	public File selectFile(String name){
		File f=new File(getFilePath(name));
		if (!f.exists()){
			f=getFile(name);
		}
		return f;
	}
}
