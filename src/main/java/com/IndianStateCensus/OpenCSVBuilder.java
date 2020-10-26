package com.IndianStateCensus;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {

	public <E> Iterator<E> getCSVFileIterator(Reader read, Class<E> csvClass) throws CensusAnalyserExecption {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(read);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserExecption(e.getMessage(),
					CensusAnalyserExecption.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	}
	


