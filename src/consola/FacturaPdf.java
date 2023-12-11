package consola;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FacturaPdf {
	public static void generateInvoice(String filePath, String customerName, String vehicleDetails, double paymentAmount) throws IOException, DocumentException {
        Document document = new Document();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath,false)) {
            PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            // Configurar detalles de la factura
            String title = "Factura de Vehículo";
            String adminSignaturePath = "./facturas/firmaAdmin.png";  // Ruta de la firma del administrador

            // Encabezado
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titleParagraph = new Paragraph(title, fontTitle);
            titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titleParagraph);

            // Detalles del cliente y vehículo
            Font fontDetails = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph customerDetailsParagraph = new Paragraph("Cliente: " + customerName, fontDetails);
            Paragraph vehicleDetailsParagraph = new Paragraph("Detalles del vehículo: " + vehicleDetails, fontDetails);
            document.add(customerDetailsParagraph);
            document.add(vehicleDetailsParagraph);

            // Detalles del pago
            Paragraph paymentDetailsParagraph = new Paragraph("Monto a pagar: $" + paymentAmount, fontDetails);
            document.add(paymentDetailsParagraph);

            // Firma del administrador 
            try {
                Image adminSignatureImage = Image.getInstance(adminSignaturePath);
                adminSignatureImage.scaleAbsolute(100, 50); // Set the width and height as needed
                adminSignatureImage.setAbsolutePosition(50, 600); // Set the position as needed
                document.add(adminSignatureImage);
                //Paragraph TextoFirma = new Paragraph("Administrador Carlos G");
                //document.add(TextoFirma);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // For simplicity, we are skipping the image part in this example.
            // Step 5: Add logo to the right upper corner
            Image logoImage = Image.getInstance("./data/Imagenes/logo.png");
            logoImage.setAbsolutePosition(document.right() - 50, document.top() - 50);
            logoImage.scaleAbsolute(50, 50);
            document.add(logoImage);
            // Close the document
            document.close();
        }
    }	
}
