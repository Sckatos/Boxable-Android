# Boxable-Android
A port for Boxable PDFBox lib

How to use (Kotlin):

```
val document = PDDocument()
val page = PDPage()
val margin = 20f
val bottomMargin = 20f
val tableWidth = page.mediaBox.width - margin * 2
val yStartNewPage = page.mediaBox.height - margin * 2
val baseTable = BaseTable(yStartNewPage - 200, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, true)
val out = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/out.pdf")

document.addPage(page)
(1 .. 10).forEach { i ->
    val row = baseTable.createRow(15f)
    
    (0 until 3).forEach { val cell = row.createCell(15f, "Row $i Col ${it + 1}", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE) }
}

baseTable.draw()
document.save(out)
document.close()
```

Don't forget
```
implementation 'com.tom_roush:pdfbox-android:1.8.9.1'
```
