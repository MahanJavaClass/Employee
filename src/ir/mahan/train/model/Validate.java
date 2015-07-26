package ir.mahan.train.model;

public class Validate {
	
	public boolean stringValidate(String str){
		
		 return str.matches( "[a-zA-Z]+" );
	}
	public boolean numberValidate(String num){
		
		return num.matches("[0-9]+\\.?[0-9]*");
	}

}
