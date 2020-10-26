package com.IndianStateCensus;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class IndianStateCensusTest {
	String IndiaStateCensus = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\IndiaStateCensusData.csv";
	String	Incorrect_CSVFile = "C:\\Users\\leena\\eclipse-workspace\\Indian StateCensus\\IndiaStateCensusData.csv";
	String	Incorrect_CSVFile_Path = "C:\\Users\\leena\\eclipse-workspace\\IndiaStateCensusData.txt";
	String  Incorrect_Delimiter = "C:\\Users\\leena\\eclipse-workspace\\indiastatecensusdata.csv";
	String Incorrect_header = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\USCensusData.csv";
	String IndiaStateCode = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\StateCode.csv";
	String Incorrect_Delimiter_StateCode = "C:\\Users\\leena\\eclipse-workspace\\statecode.csv";
	/**
	 * UC1_TC 1.1_Returns number of records in datacsv file
	 */
	@Test
	void givenRightPath_ReturnsNumOfRecord() {
		StateCensusAnalyser test = new StateCensusAnalyser();
		try {
			assertEquals(29, test.loadStateCSVData(IndiaStateCensus));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * UC1_TC 1.2_Returns custom exception when incorrect path is input
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */

	@Test
	void givenWrongTypeOfFile_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC1_TC 1.3_Returns custom exception when incorrect extension given
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	@Test
	void givenWrongPath_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile_Path);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC1_TC 1.4_when CSV file is passed properly but wrong delimiter is used
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	@Test
	void givenWrongDelimiter_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_Delimiter);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC1_TC 1.5_when Wrong header csv file is passed throws custom exception
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	@Test
	void givenWrongHeaderFile_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_header);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 *UC2_TC 1.1_Returns number of records in Codecsv file
	 */
	@Test
	void givenRightPath_ForStateCode_ReturnsNumOfRecord() {
		StateCensusAnalyser test = new StateCensusAnalyser();
		try {
			assertEquals(37, test.loadStateCodeCSVData(IndiaStateCode));
		}catch(Exception e) {
			//e.printStackTrace();
		}
	}

	/**
	 * UC2_TC 1.2_Returns custom exception when incorrect path is input
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */

	@Test
	void givenWrongTypeOfFile_ForState_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC2_TC 1.3_Returns custom exception when incorrect extension given
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	@Test
	void givenWrongPath_ForStateCode_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_CSVFile_Path);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC2_TC 1.4_when CSV file is passed properly but wrong delimiter is used
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	@Test
	void givenWrongDelimiter_ForStateCode_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_Delimiter_StateCode);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC1_TC 1.5_when Wrong header csv file is passed throws custom exception
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	@Test
	void givenWrongHeaderFile_ForStateCode_ReturnsCustomException() throws IOException, CSVBuilderExecption {
		StateCensusAnalyser test = new StateCensusAnalyser();
		int count = 0;
		try {
			count = test.loadStateCSVData(Incorrect_header);
		} catch (CensusAnalyserExecption e) {
			//e.printStackTrace();
			assertEquals(CensusAnalyserExecption.ExceptionType.INCORRECT_FILE, e.type);
		}
	}
}


