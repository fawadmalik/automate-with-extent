package com.fmzm.automatewithextent.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);

        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester", "Fawad Malik");

        return extent;
    }
}
