package nl.finalist.course.rest.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.finalist.course.rest.model.User;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author aziz
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/users",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Users.Rest"
	},
	service = Application.class
)
public class FinalistCourseRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {

		return Response.ok(users).type(MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById( @PathParam("id") int id) {
		return users.stream().filter(user ->  user.getId() == id).findFirst().get();
	}

	@GET
	@Path("/{id}/name")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserNameById( @PathParam("id") int id) {
		return users.stream().filter(user ->  user.getId() == id).findFirst().get().getName();
	}

	@GET
	@Path("/{id}/email")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserEmailById( @PathParam("id") int id) {
		return users.stream().filter(user ->  user.getId() == id).findFirst().get().getEmail();
	}

	@GET
	@Path("/{id}/fullname")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserFullnameById( @PathParam("id") int id) {
		return users.stream().filter(user ->  user.getId() == id).findFirst().get().getFullName();
	}

	private static List<User> users = retrieveUserList();

	private static List<User> retrieveUserList() {
		List<User> users = new ArrayList<>();
		users.add(new User(
				"agarcia",
				15293,
				"Ana Garcia",
				"ana.garcia@techcorp.com"
		));

		users.add(new User(
				"mwilliams",
				78641,
				"Michael Williams",
				"m.williams@emailpro.net"
		));

		users.add(new User(
				"skumar",
				92347,
				"Sanjay Kumar",
				"sanjay.kumar@devops.io"
		));

		users.add(new User(
				"ejohnson",
				31456,
				"Emily Johnson",
				"emily.j@workplace.com"
		));

		users.add(new User(
				"rchen",
				67829,
				"Robert Chen",
				"robert.chen@company.org"
		));

		users.add(new User(
				"lmartinez",
				44512,
				"Laura Martinez",
				"l.martinez@business.co"
		));

		users.add(new User(
				"dthompson",
				88903,
				"David Thompson",
				"dthompson@services.com"
		));

		users.add(new User(
				"nnguyen",
				23745,
				"Nina Nguyen",
				"nina.nguyen@startup.tech"
		));

		users.add(new User(
				"jbrown",
				55678,
				"James Brown",
				"james.brown@enterprise.net"
		));

		users.add(new User(
				"patel",
				19834,
				"Priya Patel",
				"priya.patel@digital.com"
		));
		return users;
	}
}