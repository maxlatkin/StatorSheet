package ru.ruselprom.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.wfc.wfcSession.WSession;

public final class AppProperties {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppProperties.class);
	public static final String PART_TEMP;
    public static final String DRW_TEMP;
    public static final String DXF_TEMP;
    
    private AppProperties() {}
    
    static {
    	Properties prop = readPropertiesFile(getFileName());
        PART_TEMP = prop.getProperty("part.temp");
        DRW_TEMP = prop.getProperty("drw.temp");
        DXF_TEMP = prop.getProperty("dxf.temp");
    }
    
    private static String getFileName() {
    	try {
    		WSession wSession = (WSession)pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
    		return wSession.GetResourcePath() + "\\app.properties";
    	} catch (jxthrowable e) {
    		LOG.error("Error getting path of app.properties", e);
    		return null;
		}
    }
    
    private static Properties readPropertiesFile(String fileName) {
        Properties properties = null;
        try(FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            LOG.error("app.properties not found", e);
        } catch (IOException e) {
        	LOG.error("Error reading app.properties", e);
		}
        return properties;
    }
}
