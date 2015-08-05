package ir.mahan.train.model.test;

import static org.junit.Assert.*;

import ir.mahan.train.model.DataBase;

import org.junit.Before;
import org.junit.Test;

public class DataBaseTest {
	DataBase db;

	@Before
	public void setup() {
		db = new DataBase();
	}

	@Test
	public void initializationListMustBeEmpty() {
		assertEquals(0, db.getPeopleList().size());

	}

}
