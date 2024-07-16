import com.example.constatazione_amichevole.data.Constatazione
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm
import java.io.File


fun generatePdf(data: Constatazione, outputPath: String) {
    println("Rreetest")
    val templatePath = "C:\\Users\\walid\\Downloads\\Documents\\CID_compilabile.pdf"
    val file = File(templatePath)
    PDDocument.load(file).use { document ->
        val acroForm: PDAcroForm? = document.documentCatalog.acroForm
        if (acroForm != null) {
            acroForm.getField("Nome")?.setValue(data.name)
            acroForm.getField("stampatello")?.setValue(data.surname)
            acroForm.getField("N Tel o Email")?.setValue(data.phone_number)
            acroForm.getField("N di Polizza")?.setValue(data.assurance)
            acroForm.getField("Testo1")?.setValue(data.accident_description)
            acroForm.getField("Marca Tipo")?.setValue(data.macchinaNome)
            acroForm.getField("Testo3")?.setValue(data.nameB)
            acroForm.getField("Testo2")?.setValue(data.surnameB)
            acroForm.getField("Testo7")?.setValue(data.phone_numberB)
            acroForm.getField("Testo9")?.setValue(data.assuranceB)
            document.save(outputPath)
        } else {
            println("Il PDF non contiene campi modulo.")
        }
    }}