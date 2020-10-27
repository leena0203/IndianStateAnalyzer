package Indian_State_Census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
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

	public String getStateWiseSortedCensusData(String fileName) throws CSVBuilderExecption, IOException, CensusAnalyserExecption {
		try {
			Reader read = Files.newBufferedReader(Paths.get(fileName));
		    ICSVBuilder csv = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> stateCensusList = csv.getCSVFileList(read, CSVStateCensus.class);
			Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
			this.sort(stateCensusList, censusComparator);
			String sortedStateCensusJson = new Gson().toJson(stateCensusList);
			System.out.println(sortedStateCensusJson);
			return sortedStateCensusJson;
		} catch (RuntimeException e) {
			throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserExecption(e.getMessage(), CensusAnalyserExecption.ExceptionType.NO_FILE);
		}
	}

	private void sort(List<CSVStateCensus> stateCensusList, Comparator<CSVStateCensus> censusComparator) {
		for (int i =0; i<stateCensusList.size()-1; i++) {
			for(int j =0; j<stateCensusList.size()- i -1; j++) {
				CSVStateCensus census1 = stateCensusList.get(j);
				CSVStateCensus census2 = stateCensusList.get(j+1);
				if (censusComparator.compare(census1, census2) >0) {
					stateCensusList.set(j, census2);
					stateCensusList.set(j+1, census1);
				}
			}
		}
	}
	
}