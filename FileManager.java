package gruppo.progetto;
import java.io.*;


public class FileManager {
	File file;
	String path;
	String fileName;
	String separator;
	
	public FileManager(String fileName){ //fileName: nome del file a cui accedere senza estensione
		this.fileName=fileName;
		this.path=getPath();
	}
	
	private String getPath(){
		separator=File.separator;
		return ".."+separator+"data"+separator+fileName+".dati";
	}
	
	private File getFile(){
		file=new File(path);
		if(!file.exists()){
			try{
				file.createNewFile();
			}
			catch(IOException e){
				System.out.print(e);
			}
		}
		return file;
	}
	
	public String[] read(int line){ //line: riga del file da leggere
		String row;
		try{
			String[] data=null;
			int i=0;
			BufferedReader br=new BufferedReader(new FileReader(getFile()));
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
		return null;
	}
	
	public void write(String[] input){ //input: dati da scrivere su file
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(getFile(),true));
			for(int i=0;i<input.length;i++){
				bw.write(input[i]+",");
			}
			bw.close();
		}
		catch(IOException e){
			System.out.print(e);
		}
	}
	
	public int getLineCount(){
		int i=0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(getFile()));
			while ((br.readLine()) != null){
				i++;
			}
		}
		catch(IOException e){
			System.out.print(e);
		}
		return i;
	}
}