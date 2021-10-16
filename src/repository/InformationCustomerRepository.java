package repository;

import model.SaleInformation;
import utils.CSVFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InformationCustomerRepository {

    public final String filePath = "src/data/SaleInformation.csv";

    public List<SaleInformation> getInformation() throws IOException {
        List<SaleInformation> saleList = new ArrayList<>();
        List<String> records = CSVFile.readFile(filePath);
        for (String record: records) {
            saleList.add(new SaleInformation(record));
        }
        return saleList;
    }

    public void saveSaleInformation(List<SaleInformation> saleInformations) throws IOException {
        List<String> records = new ArrayList<>();
        for (SaleInformation saleInfo: saleInformations) {
            records.add(saleInfo.toString());
        }
        CSVFile.writeFile(filePath, records);
    }

    public void insert(SaleInformation saleInfo) throws IOException {
        List<SaleInformation> saleInfoList = getInformation();
       saleInfoList.add(saleInfo);
       saveSaleInformation(saleInfoList);
    }
}
