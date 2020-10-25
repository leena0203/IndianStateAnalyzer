package com.IndianStateCensus;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class IndianStateCensusTest {
	String IndiaStateCensus = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\IndiaStateCensusData.csv";
	String	Incorrect_CSVFile = "C:\\Users\\leena\\eclipse-workspace\\Indian StateCensus\\IndiaStateCensusData.csv";
	String	Incorrect_CSVFile_Path = "C:\\Users\\leena\\eclipse-workspace\\IndiaStateCensusData.txt";
	String  Incorrect_Delimiter = "C:\\Users\\leena\\eclipse-workspace\\indiastatecensusdata.csv";
	String Incorrect_header = "C:\\Users\\leena\\eclipse-workspace\\IndianStateCensus\\Indian_State_Censun.csv";

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
	 */

	@Test
	void givenWrongTypeOfFile_ReturnsCustomException() throws IOException {
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
	 */
	@Test
	void givenWrongPath_ReturnsCustomException() throws IOException {
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
	 */
	@Test
	void givenWrongDelimiter_ReturnsCustomException() throws IOException {
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
	 */
	@Test
	void givenWrongHeaderFile_ReturnsCustomException() throws IOException {
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

