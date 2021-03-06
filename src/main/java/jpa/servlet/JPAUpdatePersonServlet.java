package jpa.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.entity.Person;

@WebServlet("/jpa/person/update")
public class JPAUpdatePersonServlet extends HttpServlet {

	private JPAService jpaService;

	@Override
	public void init() throws ServletException {
		jpaService = new JPAService();
	}

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = jpaService.getEntityManager();
		// Person person = em.find(Person.class, 1);
		Person person = new Person();
		person.setId(1);
		person.setName("Hoppe");
		person.setAge(77);

		EntityTransaction etx = em.getTransaction();
		etx.begin();
		// em.persist(person);
		em.merge(person);
		etx.commit();

		resp.getWriter().println(person);
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
