package Indian_State_Census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import CSVReader.CSVBuilderExecption;
import CSVReader.ICSVBuilder;

public class StateCensusAnalyser {

	/**
	 * UC1_Load StateCSVData
	 * @param fileName
	 * @return
	 * @throws CensusAnalyserExecption
	 * @throws IOException
	 * @throws CSVBuilderExecption 
	 */
	public int loadStateCSVData(String fileName) throws CensusAnalyserExecption, IOException, CSVBuilderExecption{
		
		try {
			Reader read = Files.newBufferedReader(Paths.get(fileName));
		    ICSVBuilder csv = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> stateCensusList = csv.getCSVFileList(read, CSVStateCensus.class);
			return stateCensusList.size();
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
	 * @throws CSVBuilderExecption 
	 */
	public int loadStateCodeCSVData(String fileName) throws CensusAnalyserExecption, IOException, CSVBuilderExecption{
		try {
			Reader read = Files.newBufferedReader(Paths.get(fileName));
			ICSVBuilder csv = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStates> stateCodeIterator = csv.getCSVFileIterator(read, CSVStates.class);
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