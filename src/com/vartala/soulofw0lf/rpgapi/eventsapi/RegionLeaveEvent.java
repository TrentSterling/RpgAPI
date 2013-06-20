package com.vartala.soulofw0lf.rpgapi.eventsapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;


import com.vartala.soulofw0lf.rpgapi.enumapi.Movement;

public class RegionLeaveEvent extends RegionEvent implements Cancellable
{

	private boolean Cancelled = false;
	private boolean Cancellable = true;
	
/*	public RegionLeaveEvent(ProtectedRegion region, Player player, Movement movement) {
		super(region, player, movement);
		
		if(movement == Movement.SPAWN || movement == Movement.DISCONNECT)
		{
			this.Cancellable = false;
		}
	}*/
    //
	@Override
	public boolean isCancelled() {
		return this.Cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		if(!this.Cancellable) return;
		this.Cancelled = arg0;
	}

}
