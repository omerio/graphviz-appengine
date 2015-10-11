package info.dawelbeit;

import static com.google.appengine.api.urlfetch.URLFetchServiceFactory.getURLFetchService;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;

@SuppressWarnings("serial")
public class DotGraphicsTestServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		URL url = new URL("http://108.59.83.98:8080/svg");
		HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST, FetchOptions.Builder.withDeadline(10.0));
		
		String dot = req.getParameter("dot");
		
		resp.setContentType("application/svg+xml");
		
		if(StringUtils.isNotBlank(dot)) {

			request.setPayload(dot.getBytes());

			HTTPResponse response = getURLFetchService().fetch(request);
			if (resp != null) {
				int responseCode = response.getResponseCode();

				List<HTTPHeader> headers = response.getHeaders();

				byte[] content = response.getContent();

				
				resp.getOutputStream().write(content);
				resp.flushBuffer();
			}
		} else {
			resp.getWriter().print("<svg></svg>");
		}
	}
}
