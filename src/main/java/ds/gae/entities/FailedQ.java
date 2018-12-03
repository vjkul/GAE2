package ds.gae.entities;

import javax.persistence.Entity;

@Entity
public class FailedQ extends Quote {
	
	public FailedQ(Quote quote) {
		super(quote.getCarRenter(), quote.getStartDate(), quote.getEndDate(), 
				quote.getRentalCompany(), quote.getCarType(), quote.getRentalPrice());
	}
}
