package nl.ict.psa.cps.configuration;

import nl.ict.psa.cps.entities.*;
import nl.ict.psa.cps.services.interfaces.CarrierService;
import nl.ict.psa.cps.services.interfaces.HeatMapService;
import nl.ict.psa.cps.services.interfaces.TerminalService;
import nl.ict.psa.cps.services.interfaces.TransportContainerService;
import nl.ict.psa.cps.utils.RandomNumber;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * This class is used to setup the application right after it started
 */
@Configuration
@EnableAutoConfiguration
public class ApplicationStartup {

    private static final Logger LOGGER = Logger.getLogger(ApplicationStartup.class);
    private static final int TIMER_DELAY = 1000;
    private static final int TIMER_PERIOD = 5000;

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private HeatMapService heatMapService;

    @Autowired
    private TransportContainerService transportContainerService;


    /**
     * Update carriers from hardcode instead of XML file
     */
    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabaseWithCarriers() {
        carrierService.deleteAllCarriers();
        terminalService.deleteAllTerminals();
        heatMapService.deleteAllHeatMaps();

        Terminal terminalAntwerp1 = new Terminal("Antwerp-Noordzee", "51.353077","4.268553", "51.34594702702519", "4.27818775177002", "51.36205438310597" ,"4.2539405822753915");
        Terminal terminalAntwerp2 = new Terminal("Antwerp-Deurganckdok", "51.291388","4.261615","51.295920", "4.253850","51.285461", "4.263911");
        Terminal terminalSines = new Terminal("Sines", "37.936240","-8.843922","37.9235297736402","-8.83620929613244","37.947141823604255","-8.861520770878997");
        terminalService.saveTerminal(terminalAntwerp1);
        terminalService.saveTerminal(terminalAntwerp2);
        terminalService.saveTerminal(terminalSines);

        HeatMap heatMap = new HeatMap("heatmap");
        HeatMap filteredHeatMap = new HeatMap("filteredHeatMap");
        heatMapService.saveHeatMap(heatMap);
        heatMapService.saveHeatMap(filteredHeatMap);


        createTestDataCarrier();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateCarriers();
            }
        };

        timer.scheduleAtFixedRate(task,TIMER_DELAY,TIMER_PERIOD);

        LOGGER.info("all Carriers updated");
    }


    public void createTestDataCarrier(){
        /*
         * import GPS coordinates from text file
         * */

        File file = null;

        try {

            List<String> resources = new  ArrayList<>();
            URL urlResource = getClass().getClassLoader().getResource("route1.txt");
            URL urlResource2 = getClass().getClassLoader().getResource("route2.txt");
            URL urlResource3 = getClass().getClassLoader().getResource("route3.txt");
            URL urlResource4 = getClass().getClassLoader().getResource("route4.txt");

            if(urlResource != null) {
                String resource = urlResource.getPath();
                resources.add(resource);
            }

            if(urlResource2 != null) {
                String resource2 = urlResource2.getPath();
                resources.add(resource2);
            }

            if(urlResource3 != null) {
                String resource3 = urlResource3.getPath();
                resources.add(resource3);
            }

            if(urlResource4 != null) {
                String resource4 = urlResource4.getPath();
                resources.add(resource4);
            }

            for(int i = 0; i < resources.size(); i++) {

                URI u = new URI(resources.get(i).trim().replaceAll("\\u0020", "%20"));
                file = new File(u.getPath());


                String line = null;

                // FileReader reads text files in the default encoding.
                FileReader fileReader =
                        new FileReader(file);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader =
                        new BufferedReader(fileReader);

                List<CarrierRoutePoint> carrierRoutePointList = new ArrayList<>();

                while ((line = bufferedReader.readLine()) != null) {

                    String[] separatedLine = line.split(",");
                    CarrierRoutePoint carrierRoutePoint = new CarrierRoutePoint();
                    carrierRoutePoint.setLatitude(separatedLine[0]);
                    carrierRoutePoint.setLongitude(separatedLine[1]);

                    carrierRoutePointList.add(carrierRoutePoint);
                }

                for (CarrierRoutePoint carrierRoutePoint : carrierRoutePointList) {
                    System.out.println("carrier Route point ");
                    System.out.println(carrierRoutePoint.getLatitude());
                    System.out.println(carrierRoutePoint.getLongitude());
                    System.out.println("\n");
                }

                String startLat =  carrierRoutePointList.get(0).getLatitude();
                String startLon = carrierRoutePointList.get(0).getLongitude();

                RandomNumber randomNumber = new RandomNumber();
                int randomFuel = randomNumber.getRandomNumber();

                List<Float> tirePressureList = new ArrayList<>();
                for(int j = 0; j < 8; j++){
                    tirePressureList.add(randomNumber.getRandomTirePressure());
                }

                String randomDriver = randomNumber.getRandomDriver();

                LOGGER.info("randomFuel " +  randomFuel + "  randomDriver  " + randomDriver  );

                Carrier carrier = new Carrier("STR-0" + i, startLat, startLon, randomFuel, tirePressureList ,randomDriver,"STR-test-route " + i );
                carrier.setCarrierRoutePoints(carrierRoutePointList);

                //adding containers to carriers
                List<TransportContainer> transportContainers = new ArrayList<>();

                TransportContainer transportContainer1 = new TransportContainer("MSCU7803TC" + (i+2));
                TransportContainer transportContainer2 = new TransportContainer("MSCU7803TC" + (i+3));
                transportContainers.add(transportContainer1);
                transportContainers.add(transportContainer2);
                carrier.setTransportContainers(transportContainers);

                carrier.setHasContainer(true);

                carrierService.saveCarrier(carrier);

                // Always close files.
                bufferedReader.close();
            }
        }catch (URISyntaxException ex){
            System.out.println("Error in URI '" + file + "'");
            ex.printStackTrace();
        }catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + file + "'");
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Error reading file '" + file + "'");
        }
    }

    /**
     * Upload carriers from a xml file
     * Currently not used because YardCranes are not implemented
     */
    public void updateCarriers() {

        HeatMap heatMap = heatMapService.findHeatMapByName("heatmap");
        List<CarrierRoutePoint> heatMapRoutePointsList = new ArrayList<>();

        if(heatMap.getHeatMapRoutePoints().size() > 0){
            heatMapRoutePointsList = heatMap.getHeatMapRoutePoints();
        }


        List<Carrier> carrierList = carrierService.findAllCarriers();

        for (Carrier carrier : carrierList){

            List<CarrierRoutePoint> carrierRoutePointHistoryList = new ArrayList<>();

            if(carrier.getCarrierRoutePointsHistory().size() > 0){
                carrierRoutePointHistoryList = carrier.getCarrierRoutePointsHistory();
            }

          //  LOGGER.info("carrier Route list");
            if(carrier.getCarrierRoutePoints().size() > 0){
                //LOGGER.info("carrier Route list  > 0 ");
                List<CarrierRoutePoint> carrierRoutePointList = carrier.getCarrierRoutePoints();

                for(int i = 0; i < carrierRoutePointList.size(); i++){
                    if (carrier.getLatitude().equals(carrierRoutePointList.get(i).getLatitude()) &&
                            carrier.getLongitude().equals(carrierRoutePointList.get(i).getLongitude())){

                      //  LOGGER.info("Current carrier Route Point found ");

                        if((i+1) < carrierRoutePointList.size()) {

                            String newLatitude = carrierRoutePointList.get(i + 1).getLatitude();
                            String newLongitude = carrierRoutePointList.get(i + 1).getLongitude();

                            //set  new location of the carrier
                            carrier.setLatitude(newLatitude);
                            carrier.setLongitude(newLongitude);

                            //add current location to driven history list
                            CarrierRoutePoint carrierRoutePoint = new CarrierRoutePoint();
                            carrierRoutePoint.setDateTime(new Date());
                            carrierRoutePoint.setLatitude(newLatitude);
                            carrierRoutePoint.setLongitude(newLongitude);
                            carrierRoutePointHistoryList.add(carrierRoutePoint);

                            //save location data in heatMap
                            heatMapRoutePointsList.add(carrierRoutePoint);
                            heatMap.setHeatMapRoutePoints(heatMapRoutePointsList);
                            heatMapService.saveHeatMap(heatMap);

                            //save history in carrier
                            carrier.setCarrierRoutePointsHistory(carrierRoutePointHistoryList);
                            carrierService.saveCarrier(carrier);

                           // LOGGER.info("carrier Updated new location => Lat: " + carrier.getLatitude() + " Long: " + carrier.getLongitude());
                            break;
                        }else{
                            //reset route to first location in route list
                            i=0;
                            carrier.setLatitude(carrierRoutePointList.get(i).getLatitude());
                            carrier.setLongitude(carrierRoutePointList.get(i).getLongitude());

                            //reset drive history
                            carrier.getCarrierRoutePointsHistory().clear();

                            carrierService.saveCarrier(carrier);
                            //LOGGER.info("carrier Reset location => Lat: " + carrier.getLatitude() + " Long: " + carrier.getLongitude());
                            break;
                        }
                    }
                }
            }
        }
    }
}
