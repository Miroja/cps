package nl.ict.psa.cps.controller;

import com.google.gson.Gson;
import nl.ict.psa.cps.entities.Carrier;
import nl.ict.psa.cps.repository.interfaces.CarrierDao;
import nl.ict.psa.cps.repository.interfaces.HeatMapDao;
import nl.ict.psa.cps.repository.interfaces.TerminalDao;
import nl.ict.psa.cps.requests.HeatMapRequest;
import nl.ict.psa.cps.services.interfaces.HeatMapService;
import nl.ict.psa.cps.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * MainController
 *
 * Everything about this class is used to set the paths in the application.
 * It provides:
 *     * index() = the main overview after login
 *     * api() = simple json page to let the javascript load in the updated data about the carriers
 *     * storeNote() = this method stores notes about the carriers and cranes
 */
@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("terminals", terminalDao.findAll());
        model.addAttribute("carriers", carrierDao.findAll());
        return "overview/index";
    }

    @RequestMapping("/api")
    @ResponseBody
    public String api() {
        LOGGER.info("Carriers has been send");
        return new Gson().toJson(carrierDao.findAll());
    }

    @RequestMapping("/terminal")
    @ResponseBody
    public String terminal(@RequestParam String terminalName) {
        LOGGER.info("terminal has been send: " + terminalName);
        return new Gson().toJson(terminalDao.findOneByName(terminalName));
    }

    @RequestMapping("/checkCarrierHistory")
    @ResponseBody
    public void checkCarrierHistory(@RequestParam String carrierId, @RequestParam Boolean checkShowHistory) {
        LOGGER.info("carrierId " + carrierId);
        Carrier carrier = carrierDao.findOne(Integer.parseInt(carrierId));
        checkShowHistory = !checkShowHistory;
        carrier.setCheckedRoutePointHistory(checkShowHistory);
        carrierDao.save(carrier);
        LOGGER.info("Carrier has been saved");
    }

    @RequestMapping("/heatmap")
    @ResponseBody
    public String getHeatMap(@RequestBody String jsonString) {
        LOGGER.info("getHeatMap()" + jsonString);

        final HeatMapRequest heatMapRequest = (HeatMapRequest) JsonHelper.fromJsonJackson(jsonString, HeatMapRequest.class.getName());

    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        if(heatMapRequest != null){
            LOGGER.info(heatMapRequest.getDateStart() + " " + heatMapRequest.getTimeStart());

            Date parsedStartDate = dateFormat.parse(heatMapRequest.getDateStart() + " " + heatMapRequest.getTimeStart());
            Date parsedEndDate =  dateFormat.parse(heatMapRequest.getDateEnd() + " " + heatMapRequest.getTimeEnd());

            LOGGER.info(" parsedStartDate " + parsedStartDate + " parsedEndDate " + parsedEndDate);
            heatMapService.filterHeatMap(parsedStartDate, parsedEndDate);
        }
        }catch ( Exception e){
            LOGGER.info("Could not convert json to DateTimeStamp");
        }

        return new Gson().toJson(heatMapDao.findOneByName("filteredHeatMap"));
    }

    @Autowired
    private CarrierDao carrierDao;

    @Autowired
    private TerminalDao terminalDao;

    @Autowired
    private HeatMapDao heatMapDao;

    @Autowired
    private HeatMapService heatMapService;

}
