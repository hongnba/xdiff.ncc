package me.lumpchen.xdiff.pdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import me.lumpchen.xdiff.document.AnnotContent;
import me.lumpchen.xdiff.document.GraphicsContent;
import me.lumpchen.xdiff.document.ImageContent;
import me.lumpchen.xdiff.document.PageContent;
import me.lumpchen.xdiff.document.TextContent;

public class PageContentExtractorTest {

	public static void main(String[] args) {
		extract(new File(args[0]));
	}

	public static void extract(File pdf) {
		PDDocument pdfDoc = null;
		try {
			pdfDoc = PDDocument.load(pdf);
			
			PDFContentRenderer render = new PDFContentRenderer(pdfDoc);
			int n = pdfDoc.getNumberOfPages();
			PDFReStructureDocument reDocument = new PDFReStructureDocument();
			
			for (int i = 0; i < n; i++) {
				render.renderImage(i);
				List<PageContent> pageContents = render.getPageContentList();
				
				PDFReStructurePage rePage = new PDFReStructurePage(i, pageContents);
				reDocument.addPage(rePage);
				
//				showPageContents(pageContents);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pdfDoc != null) {
				try {
					pdfDoc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static List<PageContent> extractPage(PDPage page) throws IOException {
		PDFPageContentDrawer extractor = new PDFPageContentDrawer(page);
		return extractor.getPageContentList();
	}
	
	static void showPageContents(List<PageContent> pageContents) {
		if (pageContents == null || pageContents.isEmpty()) {
			return;
		}
		
		System.out.println(pageContents.size());
		for (PageContent content : pageContents) {
			showContent(content);
		}
	}
	
	static void showContent(PageContent content) {
		if (content.getType() == PageContent.Type.Text) {
			TextContent text = (TextContent) content;
			System.out.println(text.getTypeString() + ": " + text.showString());
			
			System.out.println("    FontName: " + text.getGraphicsStateDesc().textState.getFontName()
					+ " " + text.getGraphicsStateDesc().textState.fontSize);
			System.out.println("    Outline: " + text.getOutlineRect());
		} else if (content.getType() == PageContent.Type.Path) {
			GraphicsContent text = (GraphicsContent) content;
			System.out.println(text.getTypeString() + ": " + text.showString());
			
			System.out.println("    ColorSpace: " + text.getGraphicsStateDesc().strokingColor.colorSpace);
			System.out.println("    Outline: " + text.getOutlineRect());
		} else if (content.getType() == PageContent.Type.Image) {
			ImageContent image = (ImageContent) content;
			System.out.println(image.getTypeString() + ": " + image.showString());
			
			System.out.println("    ColorSpace: " + image.colorSpace);
			System.out.println("    Decode: " + image.decode);
			System.out.println("    Height: " + image.height);
			System.out.println("    Width: " + image.width);
			System.out.println("    RasterSize: " + image.byteCount);
			System.out.println("    Suffix: " + image.suffix);
		} else if (content.getType() == PageContent.Type.Annot) {
			AnnotContent annot = (AnnotContent) content;
			System.out.println(annot.getTypeString() + ": " + annot.showString());
			
//			System.out.println("    SubType: " + annot.getSubType());
//			System.out.println("    FieldType: " + annot.getFieldType());
//			System.out.println("    Name: " + annot.getFieldName());
//			System.out.println("    Contents: " + annot.getAlternateFieldName());
		} 
	}
	

	static class PDFReStructureDocument {
		public List<PDFReStructurePage> pageList = new ArrayList<PDFReStructurePage>();
		
		public PDFReStructureDocument() {
		}

		public void addPage(PDFReStructurePage page) {
			this.pageList.add(page);
		}
		
		public void reStructure() {
			// header/footer
			
		}
	}

	static class PDFReStructurePage {
		public int pageIndex;
		public List<PageContent> contentList;
		
		public PDFReStructurePage(int pageIndex, List<PageContent> contentList) {
			this.pageIndex = pageIndex;
			this.contentList.addAll(contentList);
		}
	}
}

