package com.IndianStateCensus;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	/**
	 * UC1_Load StateCSVData
	 * @param fileName
	 * @return
	 * @throws CensusAnalyserExecption
	 * @throws IOException
	 */
	public int loadStateCSVData(String fileName) throws CensusAnalyserExecption, IOException{
		try {
			Reader read = Files.newBufferedReader(Paths.get(fileName));
			Iterator<CSVStateCensus> stateCensusIterator = new OpenCSVBuilder().getCSVFileIterator(read, CSVStateCensus.class);
			int numOfRecord = this.getNumOfRecord(stateCensusIterator);
			return numOfRecord;
		} catch (RuntimeException e) {
			throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.NO_FILE);
		}
	}

	/**
	 * UC2_Load stateCodeCSV
	 * @param fileName
	 * @return
	 * @throws CensusAnalyserExecption
	 * @throws IOException
	 */
	public int loadStateCodeCSVData(String fileName) throws CensusAnalyserExecption, IOException{
		try {
			Reader read = Files.newBufferedReader(Paths.get(fileName));
			Iterator<CSVStates> stateCodeIterator = new OpenCSVBuilder().getCSVFileIterator(read, CSVStates.class);
			int numOfRecord = this.getNumOfRecord(stateCodeIterator);
			return numOfRecord;
		} catch (RuntimeException e) {
			throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.NO_FILE);
		}
	}
	private <E> int getNumOfRecord(Iterator<E> iterator) {
		int numOfRecord = 0;
		while (iterator.hasNext()) {
			numOfRecord++;
			E censusData = iterator.next();
		}
		return numOfRecord;
	}
}