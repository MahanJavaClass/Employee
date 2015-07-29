package ir.mahan.train.model;

import ir.mahan.train.view.FormEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Ifile {
	public void writeToFile(FormEvent user) throws IOException;
	public  FormEvent  readFromFile() throws Exception;
}
