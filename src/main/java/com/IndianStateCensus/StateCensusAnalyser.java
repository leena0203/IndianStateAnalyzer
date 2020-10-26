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
		Iterator<CSVStateCensus> stateCensusIterator = this.getCSVFileIterator(read, CSVStateCensus.class);
		Iterator<CSVStateCensus> censusIterator = stateCensusIterator;
		int numOfRecord = 0;
		while (censusIterator.hasNext()) {
			numOfRecord++;
			@SuppressWarnings("unused")
			CSVStateCensus censusData = censusIterator.next();
		}
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
		Iterator<CSVStates> stateCodeIterator = this.getCSVFileIterator(read, CSVStates.class);
		Iterator<CSVStates> censusIterator = stateCodeIterator;
		int numOfRecord = 0;
		while (censusIterator.hasNext()) {
			numOfRecord++;
			@SuppressWarnings("unused")
			CSVStates censusData = censusIterator.next();
		}
		return numOfRecord;
	} catch (RuntimeException e) {
		throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.INCORRECT_FILE);
	} catch (NoSuchFileException e) {
		throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.NO_FILE);
	}
}

private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserExecption {
	try {
		CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
		csvToBeanBuilder.withType(csvClass);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<E> csvToBean = csvToBeanBuilder.build();
		return csvToBean.iterator();
	} catch (IllegalStateException e) {
		throw new CensusAnalyserExecption(e.getMessage(),CensusAnalyserExecption.ExceptionType.UNABLE_TO_PARSE);
	}
}
}