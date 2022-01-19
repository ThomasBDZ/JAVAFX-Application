package eu.telecomnancy.javafx.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testRegex {

    public testRegex(){

    }

    public static final Pattern patternMail =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern patternTel =
            Pattern.compile("^(\\\\+33|0|0033)[1-9][0-9]{8}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern patternAddress =
            Pattern.compile("[A-Z0-9'\\.\\-\\s\\,]", Pattern.CASE_INSENSITIVE); //On enlève - &(%#$^
    public static final Pattern patternSexe =
            Pattern.compile("M|F|ND", Pattern.CASE_INSENSITIVE);
    public static final Pattern patternNom =
            Pattern.compile("\\p{L}{1,20}", Pattern.CASE_INSENSITIVE);



    public void validateMail(String emailStr) {
        Matcher matcher = patternMail.matcher(emailStr);
        if (!matcher.find()){
            System.out.println("Mail invalide ! Réessayer.");
        }

    }
    public void validateTel(String telStr) {
        Matcher matcher = patternTel.matcher(telStr);
        if (!matcher.find()) {
            System.out.println("Numéro de téléphone invalide ! Réessayer.");
        }
    }
    public void validateAddress(String addressStr) {
        Matcher matcher = patternAddress.matcher(addressStr);
        if (!matcher.find()) {
            System.out.println("adresse invalide ! Réessayer.");
        }
    }
    public void validateSexe (String sexe){
        Matcher matcher = patternSexe.matcher(sexe);
        if (!matcher.find()) {
            System.out.println("Genre invalide ! Réessayer. (M, F ou ND)");
        }
    }
    public void validateNom (String nom){
        Matcher matcher = patternNom.matcher(nom);
        if (!matcher.find()) {
            System.out.println("Nom invalide ! Réessayer.");
        }
    }
}