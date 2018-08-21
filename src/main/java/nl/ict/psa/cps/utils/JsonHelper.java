package nl.ict.psa.cps.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;

public class JsonHelper
{
    private static final Logger logger = LogManager.getLogger(JsonHelper.class);
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));

    /**
     * Convert object to JSON string
     *
     * @param obj object to convert
     * @return JSON string
     */
    public static String toJson(final Object obj)
    {
        return gson.toJson(obj);
    }

    public static String toJsonJackson(final Object object)
    {
        try
        {
            return objectMapper.writeValueAsString(object);
        }
        catch (final JsonProcessingException exc)
        {
            logger.error("Error while creating json string: " + exc.getMessage());
            return "";
        }
    }

    public static Object fromJson(final String json, final String className)
    {
        final Class<?> cls;
        try
        {
            cls = Class.forName(className);
            return gson.fromJson(json, cls);
        }
        catch (final Exception e)
        {
            logger.error("Could not create class {}" + className + json, e);
            return null;
        }
    }

    public static Object fromJsonJackson(final String json, final String className)
    {
        final Class<?> cls;
        try
        {
            cls = Class.forName(className);
            return objectMapper.readValue(json, cls);
        }
        catch (final Exception e)
        {
            logger.info("Could not create class {}" + className + json, e);
            return null;
        }
    }
}

