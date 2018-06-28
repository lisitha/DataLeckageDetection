package service;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class AppUtil {

	public static void manipulatePdf(String src, String dest,String key) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(src);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		PdfContentByte under = stamper.getUnderContent(1);
		Font f = new Font(FontFamily.HELVETICA, 15);
		Phrase p = new Phrase(key, f);
		ColumnText.showTextAligned(under, Element.ALIGN_CENTER, p, 50,0, 0);
		PdfContentByte over = stamper.getOverContent(1);
		p = new Phrase("", f);
		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 297, 500, 0);
		p = new Phrase("", f);
		over.saveState();
		PdfGState gs1 = new PdfGState();
		gs1.setFillOpacity(0.5f);
		over.setGState(gs1);
		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, 297, 450, 0);
		over.restoreState();
		stamper.close();
		reader.close();
		
		System.out.println("completed");
	}
}
