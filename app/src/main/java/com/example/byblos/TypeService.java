package com.example.byblos;

public class TypeService {
    private String name, rate, fName, lName, birthD, email,licenseT, pickD, pickT, returnD,returnT,maxKM,areaT,movStartLoc, movEndLoc,nMovers,nBoxes,carT;

    public TypeService(String serviceName, String serviceRate, String firstN, String lastN, String birthD, String emailC, String licenseT, String pickD, String pickT, String returnD, String returnT, String maxKM, String areaT, String movStartLoc, String movEndLoc, String nMovers, String nBoxes, String carT) {
        this.name = serviceName;
        this.rate = serviceRate;
        this.fName = firstN;
        this.lName = lastN;
        this.birthD = birthD;
        this.email = emailC;
        this.licenseT = licenseT;
        this.pickD = pickD;
        this.pickT = pickT;
        this.returnD = returnD;
        this.returnT = returnT;
        this.maxKM = maxKM;
        this.areaT = areaT;
        this.movStartLoc = movStartLoc;
        this.movEndLoc = movEndLoc;
        this.nMovers = nMovers;
        this.nBoxes = nBoxes;
        this.carT = carT;
    }

    public String getSerName(){
        return name;
    }
    public String getRate(){
        return rate;
    }
    public String getFirstName(){
        return fName;
    }
    public String getLastName(){
        return lName;
    }
    public String getBirthDate(){
        return birthD;
    }
    public String getEmail(){
        return email;
    }
    public String getLicenseType(){
        return licenseT;
    }
    public String getPickUpDate(){
        return pickD;
    }
    public String getPickUpTime(){
        return pickT;
    }
    public String getReturnDate(){
        return returnD;
    }
    public String getReturnTime(){
        return returnT;
    }
    public String getMaxNumOfKM(){
        return maxKM;
    }
    public String getAreaOfTrucks(){
        return areaT;
    }
    public String getMovingStartLoc(){
        return movStartLoc;
    }
    public String getMovingEndLoc(){
        return movEndLoc;
    }
    public String getNumberOfMovers(){
        return nMovers;
    }
    public String getNumberOfBoxes(){
        return nBoxes;
    }
    public String getTypeOfCar(){
        return carT;
    }
    public void setSerName(String name){
        this.name = name;
    }
    public void setRate(String rate){
        this.rate = rate;
    }
    public void setFirstName( String fName){
        this.fName = fName;
    }
    public void setLastName(String lName){
        this.lName = this.lName;
    }
    public void setBirthDate(String birthD){
        this.birthD = birthD;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setLicenseType(String licenseT){
        this.licenseT = licenseT;
    }
    public void setPickUpDate(String pickD){
        this.pickD = pickD;
    }
    public void setPickUpTime(String pickT){
        this.pickT = pickT;
    }
    public void setReturnDate(String returnD){
        this.returnD = returnD;
    }
    public void setReturnTime(String returnT){
        this.returnT =returnT;
    }
    public void setMaxNumOfKM(String maxKM){
        this.maxKM = maxKM;
    }
    public void setAreaOfTrucks(String areaT){
        this.areaT = areaT;
    }
    public void setMovingStartLoc(String movStartLoc){
        this.movStartLoc = movStartLoc;
    }
    public void setMovingEndLoc(String movEndLoc){
        this.movEndLoc = movEndLoc;
    }
    public void setNumberOfMovers(String nMovers){
        this.nMovers = nMovers;
    }
    public void setNumberOfBoxes(String nBoxes){
        this.nBoxes = nBoxes;
    }
    public void setTypeOfCar(String carT){
        this.carT = carT;
    }
}
