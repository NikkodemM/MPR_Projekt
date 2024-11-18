package pl.edu.pjatk.MPR_Projekt.Controller;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;
import pl.edu.pjatk.MPR_Projekt.Services.CatService;
import pl.edu.pjatk.MPR_Projekt.exception.CatNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class MyRestController {
    private CatService catService;

    @Autowired
    public MyRestController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("cat/all")
    public ResponseEntity<List<Cat>> getAll() {
        return new ResponseEntity<>(this.catService.getCatList(), HttpStatus.OK);
    }

    @GetMapping("cat/{id}/identifier")
    public ResponseEntity<Long> getIdentifier(@PathVariable Long id) {
        Cat cat = this.catService.get(id)
                .orElseThrow(() -> new CatNotFoundException(id));
        return ResponseEntity.ok(cat.getIdentifier());
    }

    @GetMapping("cat/{id}/info-pdf")
    public ResponseEntity<byte[]> getCatInfoAsPdf(@PathVariable Long id) {
        Cat cat = this.catService.get(id)
                .orElseThrow(() -> new CatNotFoundException(id));

        try {
            byte[] pdfBytes = generatePdf(cat);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=cat_" + id + "_info.pdf")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private byte[] generatePdf(Cat cat) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(50, 750);

            contentStream.showText("Cat Information:");
            contentStream.newLine();
            contentStream.showText("ID: " + cat.getId());
            contentStream.newLine();
            contentStream.showText("Name: " + cat.getName());
            contentStream.newLine();
            contentStream.showText("Age: " + cat.getAge());
            contentStream.newLine();
            contentStream.showText("Identifier: " + cat.getIdentifier());
            contentStream.newLine();

            contentStream.endText();
            contentStream.close();

            document.save(outputStream);
            document.close();

            return outputStream.toByteArray();
        }
    }

    @PostMapping("cat/add")
    public ResponseEntity<Void> create(@RequestBody Cat cat) {
        this.catService.createCat(cat);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("cat/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("cat/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Cat cat) {
        this.catService.updateCat(id, cat);
        return ResponseEntity.noContent().build();
    }

}
