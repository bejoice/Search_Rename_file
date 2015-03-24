package example;

import java.io.File;
import com.northconcepts.*;
import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.excel.ExcelDocument;
import com.northconcepts.datapipeline.excel.ExcelReader;

public class ExcelSheetRename {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelDocument document = new ExcelDocument().open(new File("C:/test/Test1.xls"));

		DataReader reader = new ExcelReader(document).setSheetName("Transaction List").setFieldNamesInFirstRow(true);
		//DataReader reader = new ExcelReader(document);

		reader.open();
		try {
		    Record record;
		    while ((record = reader.read()) != null) {
		        //log.info(record);
		    	System.out.println(record.toString());
		    	System.out.println(" Name=>"+((ExcelReader) reader).getSheetName() );
		    }
		} finally {
		    reader.close();
		}
	}

}
