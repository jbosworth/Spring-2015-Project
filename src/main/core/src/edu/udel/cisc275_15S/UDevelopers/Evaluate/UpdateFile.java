package edu.udel.cisc275_15S.evaluate;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class UpdateFile {
	private Path file; // Path to file. Set to:  

	public UpdateFile(){
		this.file = FileSystems.getDefault().getPath("D:/Documents and Settings/"
				+ "Jabels/My Documents/Downloads/prototype/core/src", "UserFile.txt");
	}
	
	public Path getFile() {
		return file;
	}

	public void setFile(Path file) {
		this.file = file;
	}
	
	public void update(Record r){
		Charset charset = Charset.forName("US-ASCII");
		String s = r.toString();
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset, 
				StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
		    writer.write(s, 0, s.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
}
