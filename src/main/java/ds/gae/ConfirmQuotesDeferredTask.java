package ds.gae;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.DeferredTask;

import ds.gae.entities.CarRentalCompany;
import ds.gae.entities.Quote;

public class ConfirmQuotesDeferredTask implements DeferredTask {

	

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CarRentalCompany.class.getName());
	
	public List<Quote> quotes = new ArrayList<Quote>();
	public HttpServletResponse response;
	
	public ConfirmQuotesDeferredTask(List<Quote> quotes) {
		this.quotes = quotes;
	}
	@Override
	public void run(){
		try {
			CarRentalModel.get().confirmQuotes(quotes);
			logger.log(Level.INFO,  "Your quotes are all successfully confirmed");
		} catch (ReservationException e) {
			logger.log(Level.WARNING,  "One of your quotes couldn't be confirmed! As a result all your quotes have been cancelled.");
		}
	}
	
}
