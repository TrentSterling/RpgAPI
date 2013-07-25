package com.vartala.soulofw0lf.rpgapi.entityapi.exceptions;

@SuppressWarnings("serial")
public class NotAnAnimalException extends RuntimeException
{
	public NotAnAnimalException()
	{
		super("Entity is not an animal.");
	}
}