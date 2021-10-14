package writeReadFile;

import model.Book;
import model.CustomerInformation;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteCSVFile {


    public List<String> parseCsvLine(String csvLine) {
        List<String> result = new ArrayList<>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(";");
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }
    public void PrintBook(List<String> book, int index) {
        System.out.println(
                index + ". Tên sách:'" + book.get(0) + '\'' +
                        ", tác giả:'" + book.get(1) + '\'' +
                        ", giá sách:" + book.get(2));
    }

    public void PrintCustomerAndBook(Book book, CustomerInformation customer, int index) {
        System.out.println(
                index + ". Tên khách hàng:'" + customer.getName() + '\'' +
                        ", địa chỉ:'" + customer.getAddress() + '\'' +
                        ", số điện thoại:'" + customer.getNumber()+ '\'' +
                        ", tên sách:'" + book.getName()+ '\'' +
                        ", tác giả:'" + book.getAuthor()+ '\'' +
                        ", giá tiền:" + book.getPrice()+ ", ngày mua: "+customer.getDate()
                );
    }



}
