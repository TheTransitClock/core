/* (C)2023 */
package org.transitclock.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.Module;
import org.transitclock.config.IntegerConfigValue;
import org.transitclock.configData.AgencyConfig;
import org.transitclock.configData.MonitoringConfig;
import org.transitclock.utils.IntervalTimer;
import org.transitclock.utils.Time;

/**
 * A module that runs in a separate thread and repeatedly uses AgencyMonitor to monitor a core
 * project to determine if there are any problems. Since AgencyMonitor is used notification e-mails
 * are automatically sent.
 *
 * <p>To use with a core project use:
 * -Dtransitclock.modules.optionalModulesList=org.transitclock.monitor.MonitoringModule
 *
 * @author SkiBu Smith
 */
@Slf4j
public class MonitoringModule extends Module {

    public MonitoringModule(String agencyId) {
        super(agencyId);
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // Log that module successfully started
        logger.info("Started module {} for agencyId={}", getClass().getName(), getAgencyId());

        AgencyMonitor agencyMonitor = AgencyMonitor.getInstance(agencyId);

        // Run forever. Sleep before monitoring since don't want to monitor
        // immediately at startup
        IntervalTimer timer = new IntervalTimer();
        while (true) {
            try {
                // Wait appropriate amount of time till poll again
                long elapsedMsec = timer.elapsedMsec();
                long sleepTime = MonitoringConfig.secondsBetweenMonitorinPolling.getValue() * Time.MS_PER_SEC - elapsedMsec;
                if (sleepTime < 0) {
                    logger.warn("For monitoring module upposed to have a polling "
                            + "rate of "
                            + MonitoringConfig.secondsBetweenMonitorinPolling.getValue() * Time.MS_PER_SEC
                            + " msec but processing previous data took "
                            + elapsedMsec
                            + " msec so polling again immediately.");
                } else {
                    Time.sleep(sleepTime);
                }
                timer.resetTimer();

                // Actually do the monitoring
                String resultStr = agencyMonitor.checkAll();

                if (resultStr != null) {
                    logger.error("MonitoringModule detected problem. {}", resultStr);
                }
            } catch (Exception e) {
                logger.error("Errror in MonitoringModule for agencyId={}", AgencyConfig.getAgencyId(), e);
            }
        }
    }
}