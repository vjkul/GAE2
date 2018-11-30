package ds.gae;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.DeferredTask;

import ds.gae.entities.CarRentalCompany;
import ds.gae.entities.Quote;

public class ConfirmQuotesDeferredTask implements DeferredTask {

	

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CarRentalCompany.class.getName());
	
	public List<Quote> quotes = new ArrayList<Quote>();
	
	public ConfirmQuotesDeferredTask(List<Quote> quotes) {
		this.quotes = quotes;
	}
	@Override
	public void run() {
		try {
			CarRentalModel.get().confirmQuotes(quotes);
		} catch (ReservationException e) {
			logger.log(Level.INFO,  e.getMessage());
		}
	}
	
}
