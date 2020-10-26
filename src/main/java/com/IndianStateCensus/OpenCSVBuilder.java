package com.IndianStateCensus;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder {

	public  Iterator<E> getCSVFileIterator(Reader read, Class csvClass) throws CSVBuilderExecption {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(read);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CSVBuilderExecption(e.getMessage(),
					CSVBuilderExecption.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	}
	


