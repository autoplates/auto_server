package org.auto.plate.utils;

import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.ExecuteCaseLog;
import org.openqa.selenium.WebDriver;

import java.util.List;

public interface ElementOperate {

    ExecuteCaseLog excuteTestCase(String projectaddress, List<AutoElement> elementList,
                                  List<String> paramList, int model, ExecuteCaseLog executeCaseLog, String browserType) throws Exception;

}
