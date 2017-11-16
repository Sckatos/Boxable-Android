package com.penta.boxable_android;

import com.penta.boxable_android.page.DefaultPageProvider;
import com.penta.boxable_android.page.PageProvider;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;

import java.io.IOException;

/**
 * Created by dgautier on 3/18/2015.
 */
public class BaseTable extends Table<PDPage> {

    public BaseTable(float yStart, float yStartNewPage, float bottomMargin, float width, float margin, PDDocument document, PDPage currentPage, boolean drawLines, boolean drawContent) throws IOException {
        super(yStart, yStartNewPage, 0, bottomMargin, width, margin, document, currentPage, drawLines, drawContent, new DefaultPageProvider(document, currentPage.getMediaBox()));
    }
    
    public BaseTable(float yStart, float yStartNewPage, float pageTopMargin, float bottomMargin, float width, float margin, PDDocument document, PDPage currentPage, boolean drawLines, boolean drawContent) throws IOException {
        super(yStart, yStartNewPage, pageTopMargin, bottomMargin, width, margin, document, currentPage, drawLines, drawContent, new DefaultPageProvider(document, currentPage.getMediaBox()));
    }
    
    public BaseTable(float yStart, float yStartNewPage, float pageTopMargin, float bottomMargin, float width, float margin, PDDocument document, PDPage currentPage, boolean drawLines, boolean drawContent, final PageProvider<PDPage> pageProvider) throws IOException {
        super(yStart, yStartNewPage, pageTopMargin, bottomMargin, width, margin, document, currentPage, drawLines, drawContent, pageProvider);
    }

    @Override
    protected void loadFonts() {
        // Do nothing as we don't have any fonts to load
    }

}
