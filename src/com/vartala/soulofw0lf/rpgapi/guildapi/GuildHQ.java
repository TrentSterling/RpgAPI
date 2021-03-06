package com.vartala.soulofw0lf.rpgapi.guildapi;

import org.bukkit.Location;

/**
 * Created by: soulof
 * Date: 6/4/13
 * Time: 2:39 AM
 * <p/>
 * This file is part of the Rpg Suite Created by Soulofw0lf and Linksy.
 * <p/>
 * The Rpg Suite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * The Rpg Suite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with The Rpg Suite Plugin you have downloaded.  If not, see <http://www.gnu.org/licenses/>.
 */
public class GuildHQ {
    //

    //name of the guild headquarters
    private String HQName = "";

    //guild headquarters location
    private Location HQLoc;

    /*
     * All getters and setters
     */

    public GuildHQ() {

    }

    /**
     *
     * @return
     */
    public String getHQName() {
        return HQName;
    }

    /**
     *
     * @param HQName
     */
    public void setHQName(String HQName) {
        this.HQName = HQName;
    }

    /**
     *
     * @return
     */
    public Location getHQLoc() {
        return HQLoc;
    }

    /**
     *
     * @param HQLoc
     */
    public void setHQLoc(Location HQLoc) {
        this.HQLoc = HQLoc;
    }
}
