import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.Cell;
import java.util.List;

public class ScanTable{

   public static void main(String args[]) throws IOException{

      // Instantiating Configuration class
      Configuration config = HBaseConfiguration.create();

      // Instantiating HTable class
      HTable table = new HTable(config, "emp");

      // Instantiating the Scan class
      Scan scan = new Scan();

      // Scanning the required columns
      scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("name"));
      scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("city"));

      // Getting the scan result
      ResultScanner scanner = table.getScanner(scan);

      // Reading values from scan result
      for (Result result = scanner.next(); result != null; result=scanner.next()){
		  List<Cell> cells = result.listCells();
		      for (Cell cell : cells) {
			   String empid = Bytes.toString(CellUtil.cloneRow(cell));
			   String value = Bytes.toString(CellUtil.cloneValue(cell));
			   System.out.println("key=" + empid +" value=" + value);     
		    }
	   }
      
      //closing the scanner
      scanner.close();
   }
}
