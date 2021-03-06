package ds.gae.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

import ds.gae.ConfirmQuotesDeferredTask;
import ds.gae.entities.Quote;
import ds.gae.view.JSPSite;

@SuppressWarnings("serial")
public class ConfirmQuotesServlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		HashMap<String, ArrayList<Quote>> allQuotes = (HashMap<String, ArrayList<Quote>>) session.getAttribute("quotes");

		ArrayList<Quote> qs = new ArrayList<Quote>();
		
		for (String crcName : allQuotes.keySet()) {
			qs.addAll(allQuotes.get(crcName));
		}
		
		session.setAttribute("quotes", new HashMap<String, ArrayList<Quote>>());
		
		Queue queue = QueueFactory.getDefaultQueue();			
		TaskOptions options = TaskOptions.Builder.withPayload(new ConfirmQuotesDeferredTask(qs));
		queue.add(options);
		resp.sendRedirect(JSPSite.CONFIRM_QUOTES_RESPONSE.url());

	}
	

	
}
