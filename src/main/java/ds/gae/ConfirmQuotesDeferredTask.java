package ds.gae;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.DeferredTask;

import ds.gae.entities.Quote;


public class ConfirmQuotesDeferredTask implements DeferredTask {

//	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ConfirmQuotesDeferredTask.class.getName());
	
	public List<Quote> quotes = new ArrayList<Quote>();

	
	public ConfirmQuotesDeferredTask(List<Quote> quotes) {
		this.quotes = quotes;
	}
	@Override
	public void run(){
		try {
			CarRentalModel.get().confirmQuotes(quotes);
			logger.log(Level.INFO, "Confirming: All of your qoutes are successfully confirmed!");
		} catch (ReservationException e) {
			logger.log(Level.INFO, "Confirming: One of your qoutes failed to be confirmed. As a result every quote is cancelled.");
		}
	}
	
	/* LogService ls = LogServiceFactory.getLogService();
    LogQuery query = withIncludeAppLogs(true).minLogLevel(LogService.LogLevel.FATAL);
    LogQuery query = LogQuery.Builder.withDefaults();
    query.includeAppLogs(true).minLogLevel(LogService.LogLevel.INFO);
    query.majorVersionIds(Arrays.asList("1"));
    PrintWriter writer = new PrintWriter(out);
    for (RequestLogs logs : ls.fetch(query)) {
      for (AppLogLine logLine : logs.getAppLogLines()) {
        if (logLine.getLogLevel().equals(LogService.LogLevel.INFO)) {
          	writer.println(logLine);
        }
      }
    } */


	
}
