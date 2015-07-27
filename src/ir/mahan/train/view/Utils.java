package ir.mahan.train.view;

public class Utils {

	public static String getExtension(String name) {
		// TODO Auto-generated method stub
		int pointIndex = name.lastIndexOf(".");
		if (pointIndex == -1){
			return null;
		}
		if(pointIndex == name.length() - 1){
			return null;
		}
		return name.substring(pointIndex+1 , name.length());
	}
}
