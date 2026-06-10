package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.RendezVousDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.entity.RendezVous;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {
    public byte[] generatePatientListPdf(List<Patient> patients) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Liste des Patients").setBold().setFontSize(20));

        for (Patient p : patients) {
            document.add(new Paragraph("Nom: " + p.getNom() + " - Prenom: " + p.getPrenom()));
        }

        document.close();
        return out.toByteArray();
    }
    public byte[] generateAppointmentListPdf(Patient patient, List<RendezVousDTO> rdvList) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document doc = new Document(pdf);

        doc.add(new Paragraph("Liste des rendez-vous pour: " + patient.getNom() + " " + patient.getPrenom()).setBold());

        Table table = new Table(3);
        table.addHeaderCell("Date");
        table.addHeaderCell("Médecin");
        table.addHeaderCell("Statut");

        for (RendezVousDTO rdv : rdvList) {
            table.addCell(rdv.getDateRendezVous() != null ? rdv.getDateRendezVous().toString() : "N/A");
            table.addCell(rdv.getMedecinId().toString());
            table.addCell(rdv.getStatut().toString());
        }

        doc.add(table);
        doc.close();
        return out.toByteArray();
    }
    public byte[] generateMDossierMedicalPdf(Patient patient) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document doc = new Document(pdf);

        doc.add(new Paragraph("Dossier Médical du Patient").setFontSize(20).setBold());
        doc.add(new Paragraph("--------------------------------------------------"));
        doc.add(new Paragraph("Nom: " + patient.getNom()));
        doc.add(new Paragraph("Prénom: " + patient.getPrenom()));
        doc.add(new Paragraph("Date de naissance: " + patient.getDateNaissance()));

        doc.close();
        return out.toByteArray();
    }
    public byte[] generateSimpleReport(String title, String content) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document doc = new Document(pdf);

        doc.add(new Paragraph(title).setFontSize(20).setBold());
        doc.add(new Paragraph("--------------------------------------------------"));
        doc.add(new Paragraph(content));

        doc.close();
        return out.toByteArray();
    }
}
