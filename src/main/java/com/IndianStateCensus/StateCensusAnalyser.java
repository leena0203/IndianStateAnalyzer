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
public int loadStateCSVData(String fileName) throws CensusAnalyserExecption, IOException{
	try {
		Reader read = Files.newBufferedReader(Paths.get(fileName));
		CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder(read);
		csvToBeanBuilder.withType(CSVStateCensus.class);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
		Iterator<CSVStateCensus> censusIterator = csvToBean.iterator();
		int numOfRecord = 0;
		while (censusIterator.hasNext()) {
			numOfRecord++;
			CSVStateCensus censusData = censusIterator.next();
		}
		return numOfRecord;
	} catch (RuntimeException e) {
		throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.INCORRECT_FILE);
	} catch (NoSuchFileException e) {
		throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.NO_FILE);
	}
}
}