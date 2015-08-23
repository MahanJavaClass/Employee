package ir.mahan.train.model;

public class Validation {

	public boolean stringValidate(String str) {

		return str.matches("[a-zA-Z]+");
	}

	public boolean numberValidate(String num) {

		return num.matches("[0-9]+\\.?[0-9]*");
	}

}
