package homeWork20;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static homeWork20.FileHelper.*;
import static homeWork20.TestConstants.*;
import static org.testng.Assert.assertTrue;

public class DownloadFileTest extends BaseUITest {

    @Test
    public void csvDownloadTest() {

        String fileName = props.getProperty("fileName");
        String downloadFolderPath = props.getProperty("downloadFolderPath");
        String fileExtension = props.getProperty("fileExt");
        String absoluteFilePath = new File(downloadFolderPath).getAbsolutePath();

        new DownloadPage(driver).downloadFile(fileName);

        // wait until file is downloaded
        assertTrue(waitUntilFileIsDownloaded(absoluteFilePath, fileName, 3),
                "File was not downloaded!");

        // Check file extension
        assertTrue(isExtensionCorrect(absoluteFilePath, fileName, fileExtension),
                "File extension is wrong!");

        // Check if file is not empty
        List<String> rows = readFileRows(absoluteFilePath, fileName);
        Assert.assertFalse(rows.isEmpty(), "File is empty");

        // Checking headers
        Assert.assertEquals(getFileHeaders(rows), EXPECTED_HEADERS,
                "CSV headers are not equal!");

        // checking file content
        Assert.assertTrue(rows.contains(EXPECTED_ROW_1),
                "Row 1 is not equal fo file content");
        Assert.assertTrue(rows.contains(EXPECTED_ROW_2),
                "Row 2 is not equal fo file content");
    }
}