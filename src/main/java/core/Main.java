package core;

import data.HumanDAO;
import enums.Country;
import models.Club;
import models.Human;
import modify_object.GoogleGSON;
import modify_object.Jackson;
import modify_object.ModifyObject;
import modify_object.OrgJSON;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;


public class Main {

    private static final double BYTES_IN_MEGABYTE = 1048576.0;

    public static int main(String args[]){
        try {
            HumanDAO humanDAO = new HumanDAO();
            initWithData();
            List<Human> humans = humanDAO.getHumans();

            measure(humans);
            return 0;
        } catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    private static void measure(List<Human> humans){
        ModifyObject target = new Jackson();

        long startTime;
        long finishTime;
        long measureTimeInNanoSeconds;
        long startMemory;
        long finishMemory;
        long resultMemory;
        double measureTimeInSeconds;
        double resultMBytes;

        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
        String jsonDataJackson = target.serialize(humans);
        writeJsonToFile(jsonDataJackson, Jackson.path);
        finishTime = System.nanoTime();
        measureTimeInNanoSeconds = finishTime - startTime;
        measureTimeInSeconds = nanoSecondsInSeconds(measureTimeInNanoSeconds);
        System.out.println("Serialize using Jackson: " + measureTimeInSeconds + " s");
        finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        resultMemory = finishMemory - startMemory;
        resultMBytes = bytesToMBytes(resultMemory);
        System.out.println("Serialize using Jackson: " + resultMBytes + " Mb");

        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
        target.deserialize();
        finishTime = System.nanoTime();
        measureTimeInNanoSeconds = finishTime - startTime;
        measureTimeInSeconds = nanoSecondsInSeconds(measureTimeInNanoSeconds);
        System.out.println("Deserialize using Jackson: " + measureTimeInSeconds + " s");
        finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        resultMemory = finishMemory - startMemory;
        resultMBytes = bytesToMBytes(resultMemory);
        System.out.println("Deserialize using Jackson: " + resultMBytes + " Mb");

        target = new OrgJSON();

        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
        String jsonDataORG = target.serialize(humans);
        writeJsonToFile(jsonDataORG, OrgJSON.path);
        finishTime = System.nanoTime();
        measureTimeInNanoSeconds = finishTime - startTime;
        measureTimeInSeconds = nanoSecondsInSeconds(measureTimeInNanoSeconds);
        System.out.println("Serialize using Json.ORG: " + measureTimeInSeconds + " s");
        finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        resultMemory = finishMemory - startMemory;
        resultMBytes = bytesToMBytes(resultMemory);
        System.out.println("Serialize using Json.ORG: " + resultMBytes + " Mb");

        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
        target.deserialize();
        finishTime = System.nanoTime();
        measureTimeInNanoSeconds = finishTime - startTime;
        measureTimeInSeconds = nanoSecondsInSeconds(measureTimeInNanoSeconds);
        System.out.println("Deserialize using Json.ORG: " + measureTimeInSeconds);
        finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        resultMemory = finishMemory - startMemory;
        resultMBytes = bytesToMBytes(resultMemory);
        System.out.println("Deserialize using Json.ORG: " + resultMBytes + " Mb");

        target = new GoogleGSON();

        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
        String jsonDataGSON = target.serialize(humans);
        writeJsonToFile(jsonDataGSON, GoogleGSON.path);
        finishTime = System.nanoTime();
        measureTimeInNanoSeconds = finishTime - startTime;
        measureTimeInSeconds = nanoSecondsInSeconds(measureTimeInNanoSeconds);
        System.out.println("Serialize using GSON Google: " + measureTimeInSeconds);
        finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        resultMemory = Math.abs(finishMemory - startMemory);
        resultMBytes = bytesToMBytes(resultMemory);
        System.out.println("Serialize using GSON Google: " + resultMBytes + " Mb");

        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
        target.deserialize();
        finishTime = System.nanoTime();
        measureTimeInNanoSeconds = finishTime - startTime;
        measureTimeInSeconds = nanoSecondsInSeconds(measureTimeInNanoSeconds);
        System.out.println("Deserialize using GSON Google: " + measureTimeInSeconds);
        finishMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        resultMemory = finishMemory - startMemory;
        resultMBytes = bytesToMBytes(resultMemory);
        System.out.println("Deserialize using GSON Google: " + resultMBytes + " Mb");
    }

    public static double bytesToMBytes(long bytes){
        return bytes / BYTES_IN_MEGABYTE;
    }

    public static double nanoSecondsInSeconds(long nano){
        return nano*Math.pow(10, -9);
    }

    public static int writeJsonToFile(String humans, Path path){
        byte[] humansData = humans.getBytes();

        try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, WRITE ))) {
            out.write(humansData);
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void initWithData(){
        Club bvb = new Club("Borussia Dortmund", Country.Germany);
        Club schalke = new Club("Schalke 04", Country.Germany);
        Club tottenham = new Club("Tottenham", Country.England);
        Club mu = new Club("Manchester United", Country.England);
        Club mc = new Club("Manchester City", Country.England);

        Human yarmolenko = new Human("Andrew", "Yarmolenko", 5000, Country.Ukraine, bvb);
        Human kono = new Human("Eugene", "Konoplianka", 4500, Country.Ukraine, schalke);
        Human ali = new Human("Dele", "Ali", 7000, Country.England, tottenham);
        Human deGea = new Human("David", "De Gea", 8000, Country.Spain, mu);
        Human guardiola = new Human("Josep", "Guardiola", 9000, Country.Spain, mc);

        HumanDAO humanDAO = new HumanDAO();
        humanDAO.addHuman(yarmolenko);
        humanDAO.addHuman(kono);
        humanDAO.addHuman(ali);
        humanDAO.addHuman(deGea);
        humanDAO.addHuman(guardiola);
    }
}
