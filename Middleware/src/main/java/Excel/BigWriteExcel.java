package Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.util.logging.Logger;

public class BigWriteExcel {
    private static Logger logger = Logger.getLogger(BigWriteExcel.class.getName());

    public static void main(String args[]) throws Exception {
        long start=System.currentTimeMillis();
        SXSSFWorkbook wb = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 100000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 20; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(GeneratedCarID.generateCarID());
            }
            // manually control how rows are flushed to disk
            if(rownum % 2000 == 0) {
                ((SXSSFSheet)sh).flushRows(2000); // retain 100 last rows and flush all others
                // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
                // this method flushes all rows
            }
        }
        FileOutputStream out = new FileOutputStream("E:\\sxssf.xlsx");
        wb.write(out);
        out.close();
        System.out.println("cost :"+(System.currentTimeMillis()-start));
        // dispose of temporary files backing this workbook on disk
//        wb.dispose();
    }
}
