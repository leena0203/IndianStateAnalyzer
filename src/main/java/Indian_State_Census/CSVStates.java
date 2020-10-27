package Indian_State_Census;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	@CsvBindByName(column = "State Name",required = true)
	public String state;
	
	@CsvBindByName(column = "StateCode",required = true)
	public String stateCode;

	@Override
	public String toString() {
		return "StateCodeCSV [state=" + state + ", stateCode=" + stateCode + "]";
	}
}

