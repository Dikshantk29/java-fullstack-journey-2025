package project_practice.HMS.models;

abstract class Person {
 private String Id;
 private String name;
 private int age;
 private String gender;
 private String contactNumber;

 public Person(String Id, String name,int age,String gender,String contactNumber){
     this.Id = Id;
     this.name = name;
     this.age = age;
     this.gender = gender;
     this.contactNumber = contactNumber;
 }

 //getter
    public String getId(){
     return Id;
    }
    public String getName(){
     return name;

    }
    public int getAge(){
     return age;
    }
    public String getGender(){
     return gender;
    }
    public String getContactNumber(){
     return  contactNumber;
    }

    //setter
    //i want to set only age and contact Number
    public void setAge(int age){
     this.age = age;
    }
    public void setContactNumber(String contactNumber){
     this.contactNumber=contactNumber;
    }

    //abstarct method
    abstract  void  displayInfo();


}
