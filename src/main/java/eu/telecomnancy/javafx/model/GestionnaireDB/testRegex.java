package eu.telecomnancy.javafx.model.GestionnaireDB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.telecomnancy.javafx.controller.Erreurs.InsertionException;

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



    public void validateMail(String emailStr) throws InsertionException {
        Matcher matcher = patternMail.matcher(emailStr);
        if (!matcher.find()){
            throw new InsertionException("Mail");
        }

    }
    public void validateTel(String telStr) throws InsertionException {
        Matcher matcher = patternTel.matcher(telStr);
        if (!matcher.find()) {
            throw new InsertionException("Numéro de téléphone");
        }
    }
    public void validateAddress(String addressStr) throws InsertionException{
        Matcher matcher = patternAddress.matcher(addressStr);
        if (!matcher.find()) {
            throw new InsertionException("Adresse");
        }
    }
    public void validateSexe (String sexe) throws InsertionException{
        Matcher matcher = patternSexe.matcher(sexe);
        if (!matcher.find()) {
            throw new InsertionException("Genre","(M, F ou ND)");
        }
    }
    public void validateNom (String nom) throws InsertionException{
        Matcher matcher = patternNom.matcher(nom);
        if (!matcher.find()) {
            throw new InsertionException("Nom");
        }
    }
}