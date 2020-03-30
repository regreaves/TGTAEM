package sqlDB;


public class DatabaseProvider {
	private static DerbyDatabase theInstance;
	
	public static void setInstance(DerbyDatabase db) {
		theInstance = db;
	}
	
	public static DerbyDatabase getInstance() {
		if (theInstance == null) {
			throw new IllegalStateException("DerbyDatabase instance has not been set!");
		}
		return theInstance;
	}
}
