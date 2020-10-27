package Indian_State_Census;

import CSVReader.ICSVBuilder;
import CSVReader.OpenCSVBuilder;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}

}
