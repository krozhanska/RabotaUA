package ua.rabota;


//import org.apache.commons.io.FileUtils;
//import org.apache.commons.*;
//import org.openqa.selenium.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotCreator extends TestListenerAdapter {
    @Override
   public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if (!result.isSuccess()){
            File scrFile = ((TakesScreenshot)SingletonWB.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String path = "./target/screenshots/"+methodName +"_"+format.format(calendar.getTime())+".png";
                File fileOutput  = new File (path);
                org.apache.commons.io.FileUtils.copyFile(scrFile, fileOutput);
                Reporter.log("<br>" + " screenshot saved at "+fileOutput.getAbsolutePath());
                        //+"\\reports\\"+result.getName()+".jpg");
                Reporter.log("<p><img src=\""+fileOutput.getAbsolutePath()+"\" alt=\""+path+"\"></p>");
                Reporter.log("<a href='"+fileOutput.getAbsolutePath()+"' <img src='"+fileOutput.getAbsolutePath()+"' hight='100' width='100'/> </a>");
                //Reporter.log("<a href='../"+result.getName()+".jpg' <img src='../"+result.getName()+".jpg' hight='100' width='100'/> </a>");
               // Reporter.log("<a href='.."+ path +"'"+"><img scr='.."+ path+" height = '100' widh = '100' />screenshot</a>");
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}