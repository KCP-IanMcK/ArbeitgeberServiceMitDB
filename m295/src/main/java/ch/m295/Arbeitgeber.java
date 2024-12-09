package ch.m295;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Arbeitgeber {
	@NotNull
	private String name;
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Das Datum muss im Format yyyy-MM-dd sein")
	private LocalDate date;
	
	private boolean aktiv;
	
	public Arbeitgeber() {
		
	}
	
	public Arbeitgeber(String name) {
		super();
		this.name = name;
	}

	public Arbeitgeber(@NotNull String name, LocalDate date, boolean aktiv) {
		super();
		this.name = name;
		this.date = date;
		this.aktiv = aktiv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	
	
	
}
