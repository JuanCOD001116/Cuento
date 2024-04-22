package com.mycompany.pruebapdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *s
 * @liz 
 */
public class PruebaPDF {
    static final String excelFilePath = "C:\\Users\\juanc\\OneDrive\\Documentos\\NetBeansProjects\\Cuento\\src\\main\\java\\archivos\\Archivo_Fasecolda.xlsx";  
    static String id;
    static String marcaMoto;
    static String tipo;
    static String estadoMoto;
    static String referenciaSimple;
    static String precioMoto;
    static String referencia1;
    static String referenciaTotal;
    static String referencia2;
    static String precioTotalMoto;
    static String precioIva;
    static String valorTotal;
    static int cantidad;
    static String mostrarCantidad;
    
    
    public static void Informacion(String excelFilePath, int cantidad){
        try(Workbook libro = new XSSFWorkbook(new FileInputStream(excelFilePath))){
            Sheet hoja = libro.getSheetAt(1);
            Row row = hoja.getRow(4782);
            
            id = row.getCell(3).getStringCellValue();
            marcaMoto = row.getCell(1).getStringCellValue();
            tipo = row.getCell(2).getStringCellValue();
            referencia1 = row.getCell(5).getStringCellValue();
            referencia2 = row.getCell(7).getStringCellValue();
            estadoMoto = row.getCell(0).getStringCellValue();
            referenciaSimple = row.getCell(6).getStringCellValue();
            referenciaTotal = referencia1 + " " + referenciaSimple + " " + referencia2;
            
          
            
            double precio = Double.parseDouble(row.getCell(66).getStringCellValue());
            double precioUnidadMoto = precio * 1000;
            double precioMoto = precioUnidadMoto * cantidad;
            double iva = precioMoto * 0.19;
            double total = precioMoto + iva;

            precioTotalMoto = Double.toString(precioMoto);
            
           
            precioIva = Double.toString(iva);
            
            valorTotal = Double.toString(total);
            
            mostrarCantidad = String.valueOf(cantidad);
            
            

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void crearBlog(String titulo, String ID, String email, String celular, String direccionC, int cantidad) {
      
         Informacion(excelFilePath, cantidad);
        try{
        Document doc = new Document();
        
        //fondo
        PdfWriter.getInstance(doc, new FileOutputStream("post.pdf"));
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("post.pdf"));
         writer.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                try {
                    Image background = Image.getInstance("/vista/Imagen de WhatsApp 2024-04-16 a las 18.25.37_be116bc3.jpg");
                    PdfContentByte canvas = writer.getDirectContentUnder();
                    background.scaleAbsolute(document.getPageSize());
                    background.setAbsolutePosition(0, 0);
                    canvas.addImage(background);
                } catch (DocumentException | IOException e) {
                }
            }
        });
         

         
        doc.open();
        
        //Tablas
       
        
        PdfPTable table = new PdfPTable(2);
        PdfPTable table2 = new PdfPTable(5);
        PdfPTable table3 = new PdfPTable(3);
        PdfPTable table4 = new PdfPTable(2);
        
        table.setWidthPercentage(80); 
        table2.setWidthPercentage(100);
        table3.setWidthPercentage(100);
        table4.setWidthPercentage(66.6666667f);
        
        table.setHorizontalAlignment(Element.ALIGN_RIGHT); 
        table3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table4.setHorizontalAlignment(Element.ALIGN_RIGHT);
       
        
        //Fuentes

        com.itextpdf.text.Font fontInfoEmpresa = FontFactory.getFont(BaseFont.HELVETICA, 15, BaseColor.GRAY);
        com.itextpdf.text.Font fontTitulo = FontFactory.getFont(BaseFont.TIMES_ROMAN, 30, BaseColor.BLACK);
        com.itextpdf.text.Font fontTitulosVerdes = FontFactory.getFont(BaseFont.HELVETICA, 15, BaseColor.DARK_GRAY);
        com.itextpdf.text.Font fontGeneral = FontFactory.getFont(BaseFont.HELVETICA, 10, BaseColor.BLACK);
        com.itextpdf.text.Font fontAnexo = FontFactory.getFont(BaseFont.TIMES_ROMAN, 15, BaseColor.GRAY);
        
        
        //Strings 
        

        String pagina = "www.liznacks.com.co";
        String nombreEmpresa = "Liznacks";
        String direccion = "Calle 7b N.n-75 Toledo/N.S";
        String cel = "3222376878";
        String correo = "lizbethespinosa0609@gmail.com";
        String datosDelCliente = "Datos del cliente";
        String pedirNombre = "Nombre:";
        String pedirID = "ID:";
        String pedirCelular = "Celular:";
        String pedirEmail = "Email:";
        String pedirDireccion = "Direccion:";
        String producto = "Producto";
        String facturacion = "Facturación";
        String agradecimiento = "¡Gracias por tu compra!";
        String frase = "¡Quiero, puedo y me lo merezco!";
        String facebook = "Liznacks.es";
        String instagram = "@Lizznackss";
        String categoria = "Categoría";
        String estado = "Estado";
        String modelo = "ID";
        String marca = "Marca";
        String referencia = "Referencia";
        String nombreCantidad = "Cantidad";
        String valor = "Valor";
     

       
        String iva = "IVA (19%)";
        String total = "TOTAL";
   

        
                
        //Paragraphs

        
        Paragraph espacioNombre = new Paragraph(titulo, fontGeneral);
        Paragraph espacioId = new Paragraph(ID, fontGeneral);
        Paragraph espacioEmail = new Paragraph(email, fontGeneral);
        Paragraph espacioCelular = new Paragraph(celular, fontGeneral);
        Paragraph espacioDireccionC = new Paragraph(direccionC, fontGeneral);
        Paragraph web = new Paragraph(pagina, fontInfoEmpresa);
        Paragraph nombreEmpresaP = new Paragraph(nombreEmpresa, fontTitulo);
        Paragraph direccionP = new Paragraph(direccion, fontInfoEmpresa);
        Paragraph celP = new Paragraph(cel, fontInfoEmpresa);
        Paragraph correoP = new Paragraph(correo, fontInfoEmpresa);
        Paragraph datosDelClienteP = new Paragraph(datosDelCliente, fontTitulosVerdes);
        Paragraph PedirNombreP = new Paragraph(pedirNombre, fontGeneral);
        Paragraph pedirIDP = new Paragraph(pedirID, fontGeneral);
        Paragraph pedirCelularP = new Paragraph(pedirCelular, fontGeneral);
        Paragraph pedirEmailP = new Paragraph(pedirEmail, fontGeneral);
        Paragraph pedirDireccionP = new Paragraph(pedirDireccion, fontGeneral);
        Paragraph productoP = new Paragraph(producto, fontTitulosVerdes);
        Paragraph facturacionP = new Paragraph(facturacion, fontTitulosVerdes);
        Paragraph agradecimientoP = new Paragraph(agradecimiento, fontAnexo);
        Paragraph fraseP = new Paragraph(frase, fontAnexo);
        Paragraph facebookP = new Paragraph(facebook, fontInfoEmpresa);
        Paragraph instagramP = new Paragraph(instagram, fontInfoEmpresa);

        Paragraph categoriaP = new Paragraph (categoria, fontGeneral);
        Paragraph estadoP = new Paragraph (estado, fontGeneral);
        Paragraph idP = new Paragraph (modelo, fontGeneral);
        
        Paragraph marcaP = new Paragraph (marca, fontGeneral);
        Paragraph referenciaP = new Paragraph (referencia, fontGeneral);
        Paragraph motoP = new Paragraph (tipo, fontGeneral);
        Paragraph nuevoP = new Paragraph (estadoMoto, fontGeneral);
        Paragraph idMoto = new Paragraph (id, fontGeneral);
        Paragraph datoMarcaP = new Paragraph (marcaMoto, fontGeneral);
        Paragraph datoReferenciaP = new Paragraph (referenciaSimple, fontGeneral);
        
        Paragraph cantidadP = new Paragraph (nombreCantidad, fontGeneral);
        Paragraph valorP = new Paragraph (valor, fontGeneral);
        Paragraph numCantidadP = new Paragraph (mostrarCantidad, fontGeneral);
        Paragraph refTotalP = new Paragraph (referenciaTotal, fontGeneral);
        Paragraph precioMotoP = new Paragraph (precioTotalMoto, fontGeneral);
        
        Paragraph ivaP = new Paragraph (iva, fontGeneral);
        Paragraph totalP = new Paragraph (total, fontGeneral);
        Paragraph precioIvaP = new Paragraph (precioIva, fontGeneral);
        Paragraph precioTotalP = new Paragraph (valorTotal, fontGeneral);
       
        
        
        //Alineamientos strings
        
        web.setAlignment(Element.ALIGN_RIGHT);
        datosDelClienteP.setAlignment(Element.ALIGN_RIGHT);
        facebookP.setAlignment(Element.ALIGN_RIGHT);
        instagramP.setAlignment(Element.ALIGN_RIGHT);
        
        
        //Creacion de las tablas 
       
        table.addCell(PedirNombreP);
        table.addCell(espacioNombre); 
        table.addCell(pedirIDP); 
        table.addCell(espacioId);
        table.addCell(pedirEmailP);
        table.addCell(espacioEmail);
        table.addCell(pedirCelularP);
        table.addCell(espacioCelular);
        table.addCell(pedirDireccionP);
        table.addCell(espacioDireccionC);
        
        table2.addCell(categoriaP);
        table2.addCell(estadoP);
        table2.addCell(idP);
        table2.addCell(marcaP);
        table2.addCell(referenciaP);
        table2.addCell(motoP);
        table2.addCell(nuevoP);
        table2.addCell(idMoto);
        table2.addCell(datoMarcaP);
        table2.addCell(datoReferenciaP);
        
        table3.addCell(cantidadP);
        table3.addCell(referenciaP);
        table3.addCell(valorP);
        table3.addCell(numCantidadP);
        table3.addCell(refTotalP);
        table3.addCell(precioMotoP);
        
        table4.addCell(ivaP);
        table4.addCell(precioIvaP);
        table4.addCell(totalP);
        table4.addCell(precioTotalP);
        
        
        //Alineamiento logo y nombre empresa con tabla
        
       
       Image imagen1 = Image.getInstance("/vista/principal.png");
       imagen1.scaleToFit(40, 40);
       
       PdfPTable tablaImagen1 = new PdfPTable(2);
       tablaImagen1.setWidthPercentage(45);
       PdfPCell celdaImagen = new PdfPCell(imagen1);
       celdaImagen.setBorder(Rectangle.NO_BORDER); 
       celdaImagen.setHorizontalAlignment(Element.ALIGN_RIGHT);
       tablaImagen1.addCell(celdaImagen);
       PdfPCell celdaTexto = new PdfPCell(new Phrase(nombreEmpresaP));
       celdaTexto.setBorder(Rectangle.NO_BORDER); 
       celdaTexto.setHorizontalAlignment(Element.ALIGN_LEFT); 
       tablaImagen1.addCell(celdaTexto);
       tablaImagen1.setHorizontalAlignment(Element.ALIGN_LEFT);
       
       
       //Alineamiento facebook con tabla

       Image imagen2 = Image.getInstance("/vista/facebook.png");
       imagen2.scaleToFit(20, 20);
       
       PdfPTable tablaImagen2 = new PdfPTable(2);
       tablaImagen2.setWidthPercentage(38);
       PdfPCell celdaImagen2 = new PdfPCell(imagen2);
       celdaImagen2.setBorder(Rectangle.NO_BORDER); 
       celdaImagen2.setHorizontalAlignment(Element.ALIGN_RIGHT);
       tablaImagen2.addCell(celdaImagen2);
       PdfPCell celdaTexto2 = new PdfPCell(new Phrase(facebookP));
       celdaTexto2.setBorder(Rectangle.NO_BORDER); 
       celdaTexto2.setHorizontalAlignment(Element.ALIGN_LEFT); 
       tablaImagen2.addCell(celdaTexto2);
       tablaImagen2.setHorizontalAlignment(Element.ALIGN_RIGHT);
       
       
       //Alineamiento instagram con tabla
       
       Image imagen3 = Image.getInstance("/vista/instagram.png");
       imagen3.scaleToFit(18, 18);
       
       PdfPTable tablaImagen3 = new PdfPTable(2);
       tablaImagen3.setWidthPercentage(38);
       PdfPCell celdaImagen3 = new PdfPCell(imagen3);
       celdaImagen3.setBorder(Rectangle.NO_BORDER); 
       celdaImagen3.setHorizontalAlignment(Element.ALIGN_RIGHT);
       tablaImagen3.addCell(celdaImagen3);
       PdfPCell celdaTexto3 = new PdfPCell(new Phrase(instagramP));
       celdaTexto3.setBorder(Rectangle.NO_BORDER); 
       celdaTexto3.setHorizontalAlignment(Element.ALIGN_LEFT); 
       tablaImagen3.addCell(celdaTexto3);
       tablaImagen3.setHorizontalAlignment(Element.ALIGN_RIGHT);

    
        //agregar 
        
        doc.add(web);
        doc.add(new Paragraph("\n"));
        doc.add(tablaImagen1);
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(direccionP);
        doc.add(celP);
        doc.add(correoP);
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(datosDelClienteP);
        doc.add(new Paragraph("\n"));
        doc.add(table);
        doc.add(new Paragraph("\n"));
        doc.add(productoP);
        doc.add(new Paragraph("\n"));
        doc.add(table2);
        doc.add(new Paragraph("\n"));
        doc.add(facturacionP);
        doc.add(new Paragraph("\n"));
        doc.add(table3);
        doc.add(table4);
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(agradecimientoP);
        doc.add(fraseP);
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(tablaImagen2);
        doc.add(tablaImagen3);
     
        
        
        doc.close();
        
        
        }
       
        catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

    
    }
