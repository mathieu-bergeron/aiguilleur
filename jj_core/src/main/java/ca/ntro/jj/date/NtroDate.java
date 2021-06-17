package ca.ntro.jj.date;

import ca.ntro.jj.services.Ntro;
import ca.ntro.jj.trace.T;

public class NtroDate {
	
	private long epochSeconds = -1;

	public NtroDate() {
	}

	public NtroDate(long epochSeconds) {
		this.epochSeconds = epochSeconds;
	}

	public NtroDate deltaSeconds(long i) {
		return new NtroDate(epochSeconds + i);
	}

	public NtroDate deltaMinutes(int i) {
		return deltaSeconds(i*60);
	}

	public NtroDate deltaHours(int i) {
		return deltaMinutes(i*60);
	}

	public NtroDate deltaDays(int i) {
		return deltaHours(i*24);
	}

	public long getEpochSeconds() {
		return epochSeconds;
	}

	public void setEpochSeconds(long epochSeconds) {
		this.epochSeconds = epochSeconds;
	}

	public void updateEpochMiliseconds(long epochMiliseconds) {
		setEpochSeconds(epochMiliseconds / 1000);
	}

	public long epochMiliseconds() {
		T.call(this);

		return getEpochSeconds() * 1000;
	}

	public String format(String dateFormat) {
		return Ntro.calendar().format(epochSeconds, dateFormat);
	}
	
	public boolean isDefined() {
		return epochSeconds >= 0;
	}

	public boolean biggerThan(NtroDate other) {
		T.call(this);
		
		if(other == null || !other.isDefined()) {

			return true;

		}else {
			
			return epochSeconds > other.epochSeconds;
		}
	}

	public boolean smallerThan(NtroDate other) {
		T.call(this);

		if(other == null || !other.isDefined()) {

			return false;

		}else {
			
			return epochSeconds < other.epochSeconds;
		}
	}

	public NtroDate adjustTime(NtroTimeOfDay time) {
		T.call(this);

		Ntro.calendar().setTimeOfDay(this, time);
		
		return this;
	}




}
