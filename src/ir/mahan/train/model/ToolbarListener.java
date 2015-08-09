package ir.mahan.train.model;

import java.sql.SQLException;

public interface ToolbarListener {

	void saveEventOccured();
	void refreshEventOccured() throws Exception;
}
