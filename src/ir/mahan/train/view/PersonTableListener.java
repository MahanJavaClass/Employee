package ir.mahan.train.view;

public interface PersonTableListener {
	public void deleteRow(int row);
	public void saveRow(int[] rows);
	public void refreshRow();
}
