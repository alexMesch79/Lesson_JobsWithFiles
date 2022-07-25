import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class TestZipFile {

    ClassLoader classLoader = TestZipFile.class.getClassLoader();

    @DisplayName("Проверяем файл User5.csv в zip-архиве")
    @Test
    void csvTest() throws Exception {

        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("SampleZip.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("User5.csv");
        List<String[]> list;
        InputStream inputStream = zipFile.getInputStream(entry);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        list = csvReader.readAll();
        assertThat(list).contains(
                new String[] {"Username; Identifier;First name;Last name"},
                new String[] {"Agent007;007;James;Bond"});

    }

    @DisplayName("Проверяем файл sample1.xlsx в zip-архиве")
    @Test
    void checkXLSTest() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("SampleZip.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("sample1.xlsx");
        XLS xlsx;
        InputStream inputStream = zipFile.getInputStream(entry);
        xlsx = new XLS(inputStream);
        assertThat(xlsx.excel.getSheetAt(0)
                .getRow(3)
                .getCell(4)
                .getStringCellValue())
                .contains("This is description");
    }

    @DisplayName("Проверяем файл sample4.pdf в zip-архиве")
    @Test
    void pdfTest() throws Exception {

        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("SampleZip.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("sample4.pdf");
        PDF pdf;
        InputStream inputStream = zipFile.getInputStream(entry);
        pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("A Simple PDF File");

    }

}
