package com.vartala.soulofw0lf.rpgapi.util;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.vartala.soulofw0lf.rpgapi.RpgAPI;


public final class PlayerUtil {
	
	private static RpgAPI RPG;
	
	public PlayerUtil(RpgAPI rpg)
	{
		RPG = rpg;
	}
    //
	public static Player getPlayer(String name)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(player.getName().equals(name))
				
				return player;
		}
		return null;
	}
	
	public static boolean isPlayerInRegion(String playername, String regionname)
	{
		Player player = getPlayer(playername);
		Iterator<ProtectedRegion> itr = RPG.WG.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).iterator();
		while(itr.hasNext())
		{
			ProtectedRegion pr = itr.next();
			if(pr.getId().equals(regionname))
			{
				return true;
			}
		}
		return false;
	}

}
