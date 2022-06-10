package jpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jpa/person/query")
public class JPAQueryPersonServlet extends HttpServlet {

	private JPAService jpaService;

	@Override
	public void init() throws ServletException {
		jpaService = new JPAService();
	}

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(jpaService.getPersonById(1) + "<p />");
		out.print(jpaService.queryAllPerson() + "<p />");
		out.print(jpaService.queryPersonByAge(20) + "<p />");

		out.print(jpaService.findAll() + "<p />");
		//out.print(jpaService.findByName("%el") + "<p />");
		//out.print(jpaService.findByName("Be%") + "<p />");
		out.print(jpaService.findByName("%sh%") + "<p />");
		out.print(jpaService.findByAgeBetween(18, 25) + "<p />");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

}
