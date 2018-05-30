package be.vdab.personeel.web;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OpslagForm {

	@NotNull
	@Min(1)
	private BigDecimal bedrag;
	
	public BigDecimal getBedrag() {
		return bedrag;
	}
	
	public void setBedrag(BigDecimal bedrag) {
		this.bedrag = bedrag;
	}
}
