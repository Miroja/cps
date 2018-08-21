package nl.ict.psa.cps.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumber {

    public int getRandomNumber() {
        Random r = new Random();
        int low = 10;
        int high = 100;
        return r.nextInt(high-low) + low;
    }

    public String getRandomDriver(){

        List<String> listOfDrivers  = new ArrayList<>();
        listOfDrivers.add("John Doe");
        listOfDrivers.add("Jane Doe");
        listOfDrivers.add("James Brown");
        listOfDrivers.add("Richard Hart");
        listOfDrivers.add("Roberto blalbla");

        Random r = new Random();
        int low = 1;
        int high = listOfDrivers.size();
        int result = r.nextInt(high-low) + low;

        return listOfDrivers.get(result);
    }

    public Float getRandomTirePressure() {
        Float max = 4.5f;
        Float min = 2.0f;

        Random rand = new Random();
        return rand.nextFloat() * (max - min) + min;
    }
}
