/* (C)2023 */
package org.transitclock.core.predictiongenerator.datafilter;

import org.transitclock.config.StringConfigValue;
import org.transitclock.utils.ClassInstantiator;

public class DwellTimeFilterFactory {
    private static DwellTimeDataFilter singleton = null;

    // The name of the class to instantiate
    private static final StringConfigValue className = new StringConfigValue(
            "transitclock.core.predictiongenerator.datafilter.dwelltime",
            "org.transitclock.core.predictiongenerator.datafilter.DwellTimeDataFilterImpl",
            "Specifies the name of the class used to filter dwell times.");

    public static DwellTimeDataFilter getInstance() {
        if (singleton == null)
            singleton = ClassInstantiator.instantiate(className.getValue(), DwellTimeDataFilter.class);
        return singleton;
    }
}