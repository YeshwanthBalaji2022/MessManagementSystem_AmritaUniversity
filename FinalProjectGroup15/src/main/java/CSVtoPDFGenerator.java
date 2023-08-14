import UserAuthentication.UserDataBase;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CSVtoPDFGenerator {
    public void generatePDFFromCSV(String csvFilePath, String outputFilePath) {
        try {
            // Load the CSV file
            File csvFile = new File(csvFilePath);
            byte[] csvData = Files.readAllBytes(Paths.get(csvFile.getAbsolutePath()));
            String csvContent = new String(csvData);
            
            // Create a new PDF document
            PDDocument document = new PDDocument();
            
            // Create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            
            // Create a content stream for writing to the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            // Set font and font size
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            
            // Set table parameters
            float margin = 10;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;
            int rowsPerPage = 30;
            int numberOfRows = 0;
            
            // Split CSV content into lines
            String[] lines = csvContent.split("\n");
            
            // Calculate the number of rows in the table
            numberOfRows = lines.length;
            
            // Calculate the number of pages needed to display all the rows
            int numberOfPages = numberOfRows / rowsPerPage;
            if (numberOfRows % rowsPerPage > 0) {
                numberOfPages++;
            }
            
            // Iterate over each page and write the table
            int currentPage = 1;
            int currentRow = 0;
            for (int i = 0; i < numberOfPages; i++) {
                // Create a new page if necessary
                if (currentPage != 1) {
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream.close();
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    yStart = page.getMediaBox().getHeight() - margin;
                    yPosition = yStart;
                }
                
                // Write table headers
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Name");
                contentStream.newLineAtOffset(tableWidth * 0.25f, 0);
                contentStream.showText("Roll Number");
                contentStream.newLineAtOffset(tableWidth * 0.25f, 0);
                contentStream.showText("Column 3");
                contentStream.newLineAtOffset(tableWidth * 0.25f, 0);
                contentStream.showText("Column 4");
                contentStream.endText();
                
                // Adjust y position for table content
                yPosition -= 20;
                
                // Draw table borders
                float tableHeight = 20 * (rowsPerPage + 1);
                float cellWidth = tableWidth / 4;
                float tableYStart = yPosition;
                float tableYEnd = tableYStart - tableHeight;
                float rowHeight = 20;
                
                // Draw row borders
                contentStream.setLineWidth(1f);
                for (int row = 0; row <= rowsPerPage; row++) {
                    contentStream.moveTo(margin, tableYStart - (row * rowHeight));
                    contentStream.lineTo(margin + tableWidth, tableYStart - (row * rowHeight));
                }
                
                // Draw column borders
                contentStream.moveTo(margin + cellWidth, tableYStart);
                contentStream.lineTo(margin + cellWidth, tableYEnd);
                contentStream.moveTo(margin + 2 * cellWidth, tableYStart);
                contentStream.lineTo(margin + 2 * cellWidth, tableYEnd);
                contentStream.moveTo(margin + 3 * cellWidth, tableYStart);
                contentStream.lineTo(margin + 3 * cellWidth, tableYEnd);
                
                // Write table content
                int rowsToWrite = Math.min(rowsPerPage, numberOfRows - currentRow);
                for (int j = currentRow; j < currentRow + rowsToWrite; j++) {
                    String line = lines[j];
                    String[] data = line.split(",");
                    
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(margin, yPosition - 5); // Add a gap between the text
                    contentStream.showText(data[0]);
                    contentStream.newLineAtOffset(cellWidth, 0);
                    contentStream.showText(data[1]);
                    contentStream.newLineAtOffset(cellWidth, 0);
                    contentStream.showText(data[2]);
                   
                    contentStream.endText();
                    
                    yPosition -= rowHeight;
                }
                
                // Update current page and current row
                currentPage++;
                currentRow += rowsPerPage;
                
                // Close the content stream
                contentStream.close();
            }
            
            // Save the PDF document
            document.save(outputFilePath);
            
            // Close the PDF document
            document.close();
            
            System.out.println("PDF generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    
    public static void main(String[] args) {
        CSVtoPDFGenerator generator = new CSVtoPDFGenerator();
        String csvFilePath = "C:\\Users\\yaswa\\OneDrive\\Documents\\NetBeansProjects\\Database\\EntryData\\Data.csv";
        String outputFilePath = "C:\\Users\\yaswa\\OneDrive\\Documents\\NetBeansProjects\\Database\\EntryData\\Output.pdf";
        generator.generatePDFFromCSV(csvFilePath, outputFilePath);
    }
}
