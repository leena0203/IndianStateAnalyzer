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
import CSVReader.CSVBuilderFactory;
import CSVReader.ICSVBuilder;

@SuppressWarnings("unused")
public class StateCensusAnalyser {
	List<CSVStateCensus> stateCensusList = null;
	List<StateCodeCSV> stateCodeCSVList = null;

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
			stateCensusList = csv.getCSVFileList(read, CSVStateCensus.class);
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
	public int loadIndianStateCode(String csvFile) throws CensusAnalyserExecption {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<StateCodeCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			stateCodeCSVList =  csvBuilder.getCSVFileList(reader, StateCodeCSV.class);
			return stateCodeCSVList.size();
		}
		catch(CSVBuilderExecption exception) {
			throw new CensusAnalyserExecption(exception.getMessage(), CensusAnalyserExecption.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (IOException exception) {
			throw new CensusAnalyserExecption(exception.getMessage(), CensusAnalyserExecption.ExceptionType.INCORRECT_FILE);
		}
	}	

	/**
	 * UC3_Return sorted list using json
	 * @param fileName
	 * @return
	 * @throws CSVBuilderExecption
	 * @throws IOException
	 * @throws CensusAnalyserExecption
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getStateWiseSortedCensusData() throws CensusAnalyserExecption{
		if (stateCensusList.size()== 0 || stateCensusList == null) {
			throw new CensusAnalyserExecption("No Census Data", CensusAnalyserExecption.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(stateCensusList,censusComparator);
		String sortedStateCensusJson = new Gson().toJson(stateCensusList);
		System.out.println(sortedStateCensusJson);
		return sortedStateCensusJson;
	}

	private<E> void sort(List<E> list, Comparator<E> censusComparator) {
		for (int i =0; i<list.size()-1; i++) {
			for(int j =0; j<list.size()- i -1; j++) {
				E census1 = list.get(j);
				E census2 = list.get(j+1);
				if (censusComparator.compare(census1, census2) >0) {
					list.set(j, census2);
					list.set(j+1, census1);
				}
			}
		}
	}

	public String getStateCodeWiseSortedCensusData() throws CensusAnalyserExecption{
		if (stateCodeCSVList.size()== 0 || stateCodeCSVList == null) {
			throw new CensusAnalyserExecption("No Census Data", CensusAnalyserExecption.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<StateCodeCSV> censusComparator = Comparator.comparing(census -> census.stateCode);
		this.sort(stateCodeCSVList, censusComparator);
		String sortedStateCodeJson = new Gson().toJson(stateCodeCSVList);
		System.out.println("The state is: "+sortedStateCodeJson);
		return sortedStateCodeJson;
	}

}