/* (C)2023 */
package org.transitclock.core.predictiongenerator.datafilter;

import org.transitclock.ipc.data.IpcArrivalDeparture;

/**
 * @author scrudden Interface to implement to filter out unwanted dwell time data.
 */
public interface DwellTimeDataFilter {

    boolean filter(IpcArrivalDeparture arrival, IpcArrivalDeparture departure);
}
