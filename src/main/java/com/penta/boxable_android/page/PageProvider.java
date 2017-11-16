package com.penta.boxable_android.page;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;

public interface PageProvider<T extends PDPage> {

	T createPage();

	T nextPage();

	T previousPage();

	PDDocument getDocument();
}
