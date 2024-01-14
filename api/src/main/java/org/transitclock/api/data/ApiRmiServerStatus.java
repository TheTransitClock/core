/* (C)2023 */
package org.transitclock.api.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.transitclock.ipc.rmi.RmiCallInvocationHandler;

/**
 * @author SkiBu Smith
 */
@XmlRootElement(name = "rmiServerStatus")
public class ApiRmiServerStatus {

    @XmlElement(name = "agency")
    private List<ApiAgencyRmiServerStatus> agenciesData;

    /** Sub API class that actually contains all data for each agency */
    private static class ApiAgencyRmiServerStatus {
        @XmlAttribute(name = "id")
        private String agencyId;

        @XmlAttribute
        private int rmiCallsInProcess;

        @XmlAttribute
        private long rmiTotalCalls;

        @SuppressWarnings("unused")
        protected ApiAgencyRmiServerStatus() {}

        public ApiAgencyRmiServerStatus(String agencyId, int rmiCallsInProcess, long rmiTotalCalls) {
            this.agencyId = agencyId;
            this.rmiCallsInProcess = rmiCallsInProcess;
            this.rmiTotalCalls = rmiTotalCalls;
        }
    }

    /********************** Member Functions **************************/

    /**
     * Constructors a ApiRmiServerStatus object
     *
     * @param agencyId
     * @param ipcServerStatus
     */
    public ApiRmiServerStatus() {
        agenciesData = new ArrayList<ApiAgencyRmiServerStatus>();

        // For each agency that has had RMI calls
        Set<String> agencyIds = RmiCallInvocationHandler.getAgencies();
        for (String agencyId : agencyIds) {
            // Create an API object for this agency
            ApiAgencyRmiServerStatus agencyStatus = new ApiAgencyRmiServerStatus(
                    agencyId,
                    RmiCallInvocationHandler.getCount(agencyId),
                    RmiCallInvocationHandler.getTotalCount(agencyId));
            agenciesData.add(agencyStatus);
        }
    }
}
