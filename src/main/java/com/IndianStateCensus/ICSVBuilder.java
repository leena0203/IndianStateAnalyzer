package com.IndianStateCensus;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
	public Iterator<E> getCSVFileIterator(Reader read, Class<E> csvClass) throws CensusAnalyserExecption; 

}
