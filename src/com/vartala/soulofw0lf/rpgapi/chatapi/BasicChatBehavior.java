package com.vartala.soulofw0lf.rpgapi.chatapi;

import com.vartala.soulofw0lf.rpgapi.RpgAPI;
import com.vartala.soulofw0lf.rpgapi.playerapi.RpgPlayer;
import com.vartala.soulofw0lf.rpgapi.util.ChatColors;
import org.bukkit.Bukkit;

/**
 * Created by: soulofw0lf
 * Date: 6/28/13
 * Time: 2:13 AM
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
public class BasicChatBehavior implements ChatBehavior {
    /**
     *
     * @param chatName
     * @param receiveName
     * @param sendName
     * @param language
     * @param message
     * @param chatSpy
     * @return
     */
    @Override
    public Boolean chatChannel(String chatName, String receiveName, String sendName, String language, String message, Boolean chatSpy) {
        RpgPlayer rp = RpgAPI.getRp(receiveName);
        if (rp.getIgnoreList().contains(sendName)) {
            return false;
        }
        if (chatSpy && rp.isSpyingOnChats()) {
            return true;
        }
        Boolean isInChannel = false;
        for (String inChannel : rp.getChannelColor().keySet()) {
            if (inChannel.equalsIgnoreCase(chatName)) {
                isInChannel = true;
            }
        }
        if (!(isInChannel)) {
            return false;
        }

        return true;
    }
}
