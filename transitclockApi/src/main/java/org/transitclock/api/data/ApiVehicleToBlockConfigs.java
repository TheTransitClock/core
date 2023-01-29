/*
 * This file is part of Transitime.org
 * 
 * Transitime.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL) as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Transitime.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Transitime.org .  If not, see <http://www.gnu.org/licenses/>.
 */

package org.transitclock.api.data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.transitclock.api.rootResources.TransitimeApi.UiMode;
import org.transitclock.db.structs.Agency;
import org.transitclock.db.webstructs.WebAgency;
import org.transitclock.ipc.data.IpcVehicle;
import org.transitclock.ipc.data.IpcVehicleToBlockConfig;
import org.transitclock.utils.Time;

/**
 * For when have list of VehicleDetails. By using this class can control the
 * element name when data is output.
 *
 * @author SkiBu Smith
 *
 */
@XmlRootElement
public class ApiVehicleToBlockConfigs {

	@XmlElement(name = "vehicleToBlock")
	private List<ApiVehicleToBlockConfig> vehiclesData;

	/********************** Member Functions **************************/

	/**
	 * Need a no-arg constructor for Jersey. Otherwise get really obtuse
	 * "MessageBodyWriter not found for media type=application/json" exception.
	 */
	protected ApiVehicleToBlockConfigs() {
	}

	/**
	 * For constructing a ApiVehiclesDetails object from a Collection of Vehicle
	 * objects.
	 * 
	 * @param vehicles
	 * @param agencyId
	 * @param uiTypesForVehicles
	 *            Specifies how vehicles should be drawn in UI. Can be NORMAL,
	 *            SECONDARY, or MINOR
	 * @param assigned 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ApiVehicleToBlockConfigs(Collection<IpcVehicleToBlockConfig> vehicles) throws IllegalAccessException, InvocationTargetException {
		vehiclesData = new ArrayList<ApiVehicleToBlockConfig>();

		for (IpcVehicleToBlockConfig vehicleToBlock : vehicles) {
			vehiclesData.add(new ApiVehicleToBlockConfig(vehicleToBlock));
		}
	}

}
