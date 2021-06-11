import java.io.*;


public class FileManager {
	File file;
	String path;
	String fileName;
	public FileManager(String fileName){
		this.fileName=fileName;
		this.path=getPath();
	}
	private String getPath(){
		return ".."+File.separator+"data"+File.separator+fileName+".dati";
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
	public String[] read(int l){
		String row;
		String[][] data=null;
		int i=0;
		try{
			data=new String[getLineCount()][10];
			i=0;
			BufferedReader csvReader=new BufferedReader(new FileReader(getFile()));
			while (( row = csvReader.readLine()) != null) {
    			data[i] = row.split(",");
				i++;
			}
			csvReader.close();
			return data[l];
		}
		catch(IOException e1){
			System.out.print(e1);
		}
		catch(ArrayIndexOutOfBoundsException e2){
			System.out.print("Linea non valida\n");
		}
		return null;
	}
	public void write(String str){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(getFile(),true));
			bw.write(str);
			bw.close();
		}
		catch(IOException e){
			System.out.print(e);
		}
	}
	public int getLineCount(){
		int i=0;
		try{
		BufferedReader csvReader = new BufferedReader(new FileReader(getFile()));
			while ((csvReader.readLine()) != null){
				i++;
			}
		}
		catch(IOException e){
			System.out.print(e);
		}
		return i;
	}
}
