package ds.gae;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.DeferredTask;

import ds.gae.entities.CarRentalCompany;
import ds.gae.entities.Quote;
import ds.gae.servlets.ReportErrorServlet;

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
			new ReportErrorServlet().connectToServer();
		} catch (ReservationException e) {
			new ReportErrorServlet();
		}
	}
	
	


	
}
