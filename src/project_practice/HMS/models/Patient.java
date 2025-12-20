package project_practice.HMS.models;

import java.util.List;

public class Patient extends Person{
    private String patientId;
    private List<String> medicalHistory;
    private AdmissionDetails admissionDetails;

    public Patient(String Id, String name, int age ,String gender,String contactNumber){
        super(Id, name, age, gender, contactNumber);
        this.patientId = patientId;
//        this.AdmissionDetails = admissionDetails;
        this.medicalHistory= medicalHistory;

    }

    public void admitpatient(Patient patient){
        //
    }

    @Override
    void displayInfo() {
        System.out.println("=== Patient Information");
        System.out.println("ID: "+ getId());
        System.out.println("Patient ID: "+patientId);
        System.out.println("Name: "+ getName());
        System.out.println("Age: "+ getAge());
        System.out.println("Gender: "+ getGender());
        System.out.println("Contact No: "+ getContactNumber());
        System.out.println("Admission Details: "+admissionDetails);
        System.out.println("Medical History: "+ medicalHistory);
    }


}
