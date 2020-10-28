package Indian_State_Census;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import CSVReader.CSVBuilderExecption;

class IndianStateCensusTest {
	String IndiaStateCensus = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\IndiaStateCensusData.csv";
	String	Incorrect_CSVFile = "C:\\Users\\leena\\eclipse-workspace\\Indian StateCensus\\IndiaStateCensusData.csv";
	String	Incorrect_CSVFile_Path = "C:\\Users\\leena\\eclipse-workspace\\IndiaStateCensusData.txt";
	String  Incorrect_Delimiter = "C:\\Users\\leena\\eclipse-workspace\\indiastatecensusdata.csv";
	String Incorrect_header = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\USCensusData.csv";
	String IndiaStateCode = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\IndiaStateCode.csv";
	String Incorrect_Delimiter_StateCode = "C:\\Users\\leena\\eclipse-workspace\\statecode.csv";

	@Test
	void givenRightPath_ReturnsNumOfRecord() {
		StateCensusAnalyser test = new StateCensusAnalyser();
		try {
			assertEquals(29, test.loadStateCSVData(IndiaStateCensus));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void givenStateCodeCSVFile_ifMatchesTotalNumberOfRecords_ShouldReturnTrue() throws IOException{
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadIndianStateCode(IndiaStateCode);
		} catch (CensusAnalyserExecption exception) {
			exception.printStackTrace();
		}
		assertEquals(37, count);
	}
	@Test
	void givenWrongTypeOfFile_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		@SuppressWarnings("unused")
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.NO_FILE, e.type);
		}
	}

	@Test
	void givenWrongPath_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		@SuppressWarnings("unused")
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile_Path);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	@Test
	void givenWrongDelimiter_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		@SuppressWarnings("unused")
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_Delimiter);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	@Test
	void givenWrongHeaderFile_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		@SuppressWarnings("unused")
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_header);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}




//	@Test
//	void givenWrongTypeOfFile_ForState_ReturnsCustomException() throws IOException, CSVBuilderExecption {
//		StateCensusAnalyser test = new StateCensusAnalyser();
//		@SuppressWarnings("unused")
//		int count = 0;
//		try {
//			count = test.loadIndianStateCode(Incorrect_CSVFile);
//		} catch (CensusAnalyserExecption e) {
//			e.printStackTrace();
//			assertEquals(CensusAnalyserExecption.ExceptionType.NO_FILE, e.type);
//		}
//	}

	@Test
	void givenWrongPath_ForStateCode_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		@SuppressWarnings("unused")
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile_Path);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	@Test
	void givenWrongDelimiter_ForStateCode_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		@SuppressWarnings("unused")
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_Delimiter_StateCode);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}


	@Test
	void givenIndianCensusData_whenSortedOnState_ReturnStartState() 
			throws IOException, CSVBuilderExecption, CensusAnalyserExecption {

		StateCensusAnalyser test = new StateCensusAnalyser();
		test.loadStateCSVData(IndiaStateCensus);
		String sortedCensusData = test.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV =new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].state);	
	}

	@Test
	void givenIndianCensusData_whenSortedOnState_ReturnEndState() 
			throws IOException, CSVBuilderExecption, CensusAnalyserExecption {

		StateCensusAnalyser test = new StateCensusAnalyser();
		test.loadStateCSVData(IndiaStateCensus);
		String sortedCensusData = test.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV =new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);	
	}
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnStartState()
			throws IOException, CensusAnalyserExecption, CSVBuilderExecption {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadIndianStateCode(IndiaStateCode);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		StateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, StateCodeCSV[].class);
		assertEquals("Andhra Pradesh New", censusCSV[0].state);
	}
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnEndState()
			throws IOException, CensusAnalyserExecption, CSVBuilderExecption {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadIndianStateCode(IndiaStateCode);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		StateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, StateCodeCSV[].class);
		assertEquals("WB", censusCSV[censusCSV.length - 1].stateCode);
	}
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnMostlyPopulated()
			throws IOException, CensusAnalyserExecption, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		test.loadStateCSVData(IndiaStateCensus);
		String sortedPopulation = test.getPopulationWiseSortedData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedPopulation, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[0].state);
	}
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnLeastPopulated()
			throws IOException, CensusAnalyserExecption, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		test.loadStateCSVData(IndiaStateCensus);
		String sortedPopulation = test.getPopulationWiseSortedData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedPopulation, CSVStateCensus[].class);
		assertEquals("Uttarakhand", censusCSV[censusCSV.length - 1].state);
	}
}


