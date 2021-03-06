package runner;

import com.cucumber.listener.ExtentCucumberFormatter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CucumberOptions(
        plugin = {"json:target/RunCuke/cucumber.json","pretty", "html:target/RunCuke/cucumber.html", "com.cucumber.listener.ExtentCucumberFormatter"},
        features = "src/test/resources/features",
        glue = "steps"
)

public class RunCuke extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void setup() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
        Date curDate = new Date();
        String strDate = sdf.format(curDate);
        String fileName = "target/Extent_Reports/" + strDate+".html";
        File newFile = new File(fileName);
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(newFile,false);
        ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));
        ExtentCucumberFormatter.addSystemInfo("Browser Name", "Chrome");
        ExtentCucumberFormatter.addSystemInfo("Browser version", "v85.0");
        ExtentCucumberFormatter.addSystemInfo("Selenium version", "v3.1.0");

        Map systemInfo = new    HashMap();
        systemInfo.put("Cucumber version", "v1.2.3");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.0");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);
    }


}
