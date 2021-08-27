package FileManager;

import java.io.*;

/**
 * <p>Permette di accedere a dei file</p>
 * @author Giorgio Margherini
 */

public class FileManager {
	File file;
	String path;
	String fileName;
	String separator;
	
	/**
	 * <p>Inizzializza un <code>FileManager</code> relativo al file specificato
	 * @param fileName nome del file a cui accedere (senza estensione)
	 */
	public FileManager(String fileName){
		this.fileName=fileName;
		this.path=getFilePath(this.fileName);
		this.file=getFile(this.fileName);
	}
	
	/**
	 *<p>Inizzializza un <code>FileManager</code> relativo alla cartella "data"</p>
	 */
	public FileManager(){
		this.path=getDirectoryPath();
		this.file=getFile("");
	}
	
	/**
	 * <p>Restituisce il path della cartella "data"</p>
	 * @return Il percorso della cartella
	 */
	private String getDirectoryPath(){
		separator=File.separator;
		return ".."+separator+"data"+separator;
	}
	
	/**
	 * <p>Restituisce il percorso del file selezionato</p>
	 * @param name Il nome del file di cui si vuole ottenere il percorso
	 * @return Il percorso del file
	 */
	private String getFilePath(String name){
		separator=File.separator;
		return ".."+separator+"data"+separator+name+".dati";
	}
	
	/**
	 * <p>Restituisce il file selezionato</p>
	 * @param name Il nome del file a cui accedere
	 * @return Il file selezionato
	 */
	private File getFile(String name){
		File f=newFile(name);
		return f;
	}
	
	/**
	 * <p>Se non esiste, crea un nuovo file chiamato <code>name</code>.dati</p>
	 * @param name Il nome del file da creare (senza estensione)
	 * @return Il file appena creato
	 */
	public File newFile(String name){
		File f=new File(getFilePath(name));
		if(!f.exists()){
			try{
				f.createNewFile();
			}
			catch(IOException e){
				System.out.print("Errore nella creazione del file "+name);
			}
		}
		return f;
	}
	
	/**
	 * <p>Se esiste, elimina il file <code>name</code>.dati</p>
	 * @param name il nome del file da eliminare (senza estensione)
	 */
	public void deleteFile(String name){
		File f=new File(getFilePath(name));
		if(f.exists()){
			f.delete();
		}
	}
	/*Se si elimina un file [NomeCentroVaccinale].dati, ricordare di eliminare la corrispondente linea da "centri vaccinali" con
		
		DataManager dm=new DataManager(new FileManager("centri vaccinali"));
		dm.deleteLine(dm.getLine(name));
	*/
	
	/**
	 * <p>Restituisce il contenuto della cartella "data" come array di <code>String</code></p>
	 * @return Un vettore con i nomi di tutti file nella cartella "data"
	 */
	public String[] getFileList(){ 
		File f=new File(getDirectoryPath());
		String[] files=f.list();
		return files;
	}
	
	//restituisce il file "name" se esiste
	/**
	 * <p>Se esiste, accede al file specificato</p>
	 * @param name Il nome del file a cui accedere
	 */
	public void selectFile(String name){
		File f=new File(getFilePath(name));
		if (f.exists()){
			f=getFile(name);
			this.file=f;
			this.fileName=name;
			this.path=getFilePath(name);
		}
	}
	
	/**
	 * <p>Controlla se il file selezionato esiste</p>
	 * @param name File da controllare
	 * @return <code>true</code> se il file esiste, <code>false</code> altrimenti
	 */
	public Boolean esiste(String name){
		File f=new File(getFilePath(name));
		return f.exists();
	}
}