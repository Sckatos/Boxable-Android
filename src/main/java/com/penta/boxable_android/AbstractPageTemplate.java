package com.penta.boxable_android;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;


/**
 * Created by dgautier on 3/18/2015.
 */
public abstract class AbstractPageTemplate extends PDPage {

    protected abstract PDDocument getDocument();

    protected abstract float yStart();

    protected void addPicture(PDImageXObject ximage, float cursorX, float cursorY, int width, int height) throws IOException {

        PDPageContentStream contentStream = new PDPageContentStream(getDocument(), this, true, false);
        contentStream.drawImage(ximage, cursorX, cursorY, width, height);
        contentStream.close();
    }
/*
    protected PDImage loadPicture(String nameJPGFile) throws IOException {
        return PDImageXObject.createFromFile(nameJPGFile, getDocument());
    }*/

}
