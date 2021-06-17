package ca.ntro.jj.services;

import ca.ntro.jj.date.NtroDate;
import ca.ntro.jj.date.NtroTimeOfDay;

public abstract class CalendarService {

	public abstract NtroDate fromString(String dateString, String dateFormat);
	public abstract String format(long epochSeconds, String dateFormat);
	public abstract void setTimeOfDay(NtroDate ntroDate, NtroTimeOfDay time);
	public abstract NtroDate now();

}
