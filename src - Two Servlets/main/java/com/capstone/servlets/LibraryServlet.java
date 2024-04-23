package com.capstone.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capstone.dao.*;
import com.capstone.proxy.libraryProxy;
import com.capstone.proxy.userProxy;
import com.capstone.LoginSingleton.Login;
import com.capstone.beans.ActivityLog;
import com.capstone.beans.Books;
import com.capstone.beans.User;
import com.capstone.beans.UserRegistration;

/**
 * Servlet implementation class LibraryServlet
 */
@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibraryInterface dao; 
	private UserInterface user;
	@Override
	public void init() throws ServletException {
		super.init();
		user = new userProxy();
		dao = new libraryProxy(); // Initialize LibraryDao in the init method
	}
	public LibraryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "login":
			performLogin(request, response);
			break;
		case "userSettings":
			updateUserSettings(request, response);
			break;
		case "adminDashboard":
			showAdminDashboard(request, response);
			break;
		case "books":
			showBooks(request, response);
			break;
		case "userRegistration":
			userRegistration(request, response);
			break;
		case "reserveConfirm":
			reserveConfirm(request, response);
			break;
		case "returnConfirm":
			returnConfirm(request, response);
			break;
		case "add":
			addBook(request, response);
			break;
		case "updateStock":
			updateStock(request, response);
			break;
		case "forgotPassword":
			forgotPassword(request, response);
			break;
		case "changePassword":
			changePassword(request, response);
			break;
		case "rateBook":
		    rateBook(request, response);
		    break;
		default:
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("changePassword".equals(action)) {
			showChangePasswordForm(request, response);
		}else if ("reserveConfirm".equals(action)) {
			reserveConfirm(request, response);	
		}else if ("returnConfirm".equals(action)) {
			returnConfirm(request, response);
		}
	}
	private void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		user.updatePassword(userID, password);
		ActivityLog log = new ActivityLog(userID , "1", "Changed Password");
		user.logUser(log);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	private void showChangePasswordForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		UserInterface dao = new userProxy();
		List<User> list = dao.searchUser(userID);
		for (User user : list) {
			request.setAttribute("userID", user.getUserID());
			request.setAttribute("password", user.getPassword());
		}
		request.getRequestDispatcher("/change_password.jsp").forward(request, response);
	}
	private void userRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page  = "";
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");


		if(user.checkUserExists(userID, email)) {
			request.setAttribute("errorMessage", "userID or email already registered");
			request.getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
			return;
		}  
		if(user.createUser(userID, password, email, fName, lName)) {
			UserRegistration newUser  = new UserRegistration(new User(userID, password, email, fName, lName));
			ActivityLog log = new ActivityLog(userID, "1", "UserCreated");
			user.logUser(log);
			page = "RegistrationSuccess.jsp";
			response.sendRedirect(page);
		}else {
			page = "RegistrationFailed.jsp";
			response.sendRedirect(page);
		}
	}
	private void performLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		boolean isAdmin = false;
		boolean isAuthenticated = false;
		String dbMessage = "";
		Login login = Login.getInstance();

		if (login.login(userID, password)) {
			isAuthenticated = true;
			ActivityLog log = new ActivityLog(userID, "1", "login");
			user.logUser(log);

			if (login.checkUserAdmin(userID)) {
				isAdmin = true;
			}
		} else {
			dbMessage = "Invalid userID or password.";
		}

		if (isAuthenticated) {
			if (isAdmin) {
				showAdminDashboard(request, response);
			} else {
				showBooks(request, response);
			}
		} else {
			request.setAttribute("dbMessage", dbMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}
	private void updateUserSettings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		boolean isAdmin  = false;
		boolean isAuthenticated = false;
		String dbMessage = "";
		Login login =  Login.getInstance();  // calling singleton class
		if (login.login(userID, password)) {
			isAuthenticated = true;
		} else {
			dbMessage = "Invalid userID or password.";
		}
		if(isAuthenticated) {
			ActivityLog log = new ActivityLog(userID, "1", "login");
			user.logUser(log);
			if(login.checkUserAdmin(userID)) {
				isAdmin = true;	
			}
		}	
		if(isAuthenticated) {
			if(isAdmin) {
				List<User> users = user.showAll();
				request.setAttribute("userInfo", users);
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			} else {
				List<User> users = user.searchUser(userID);
				request.setAttribute("userInfo", users);
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("dbMessage", dbMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard_login.jsp");
			dispatcher.forward(request, response);
		}
	}
	private void showAdminDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Books> booksList = dao.booklist();
		String userID = request.getParameter("userID");
		request.setAttribute("userID", userID);
		request.setAttribute("booksList", booksList);
		request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
	}
	private void showBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Books> booksList = dao.booklist();
		String userID = request.getParameter("userID");
		request.setAttribute("userID", userID);
		request.setAttribute("buk", booksList);
		request.getRequestDispatcher("/library.jsp").forward(request, response);
	}
	private void reserveConfirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		System.out.println(userID);
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		dao.reserveBook(bookId);
		ActivityLog log = new ActivityLog(userID, "1", "Reserved BookId: " + bookId);
		user.logUser(log);

		// Redirect back to the library page
		request.getRequestDispatcher("LibraryServlet?action=books").forward(request, response);
	}
	private void returnConfirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId1 = request.getParameter("bookId");
		String userID = request.getParameter("userID");
		ActivityLog log = new ActivityLog(userID, "1", "Returned BookId: " + bookId1);
		user.logUser(log);
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		dao.returnBook(bookId);

		// Redirect back to the library page
		request.setAttribute("successMessage", "Book returned successfully!");
		request.getRequestDispatcher("LibraryServlet?action=books").forward(request, response);
	}
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters from the form
		String title = request.getParameter("title");
		String userID = request.getParameter("userID");
		String author = request.getParameter("author");
		String genre = request.getParameter("genre");
		String summary = request.getParameter("summary");
		PrintWriter writer=response.getWriter(); 
		Books book = new Books();  // create object and set  values for storage
		book.setTitle(title);
		book.setAuthor(author);
		book.setGenre(genre);
		book.setSummary(summary);
		int rowCount = dao.addBook(book);
		if(rowCount>0){  
			writer.print("<p>Record saved successfully!</p>"); 
			request.setAttribute("successMessage", "New book added successfully!");
			ActivityLog log = new ActivityLog(userID, "1", "Added Book: " + title);
			user.logUser(log);
			showAdminDashboard(request, response);     
		}else{  
			writer.println("<p>Sorry! unable to save record</p>");  
			request.getRequestDispatcher("AdminDashboard.jsp").include(request, response);  
		}       
		writer.close();  
	}  
	private void updateStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String userID = request.getParameter("userID");
		System.out.println("Book ID: " + bookId);  // Debugging 
		int newStock;   
		try {
			newStock = Integer.parseInt(request.getParameter("stock"));
			System.out.println("New Stock: " + newStock);  // Debugging
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Invalid stock value provided.");
			doGet(request, response);  // Fetch the list of books before forwarding
			return;
		}
		System.out.println("Updating stock for bookId: " + bookId + " to " + newStock);
		dao.updateStock(bookId,newStock);
		ActivityLog log = new ActivityLog(userID, "1", "Updated stock bookid: " + bookId);
		user.logUser(log);
		showAdminDashboard(request, response);
	}
	private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		List<User> forUser  = user.forgotPassword(email);
		for(User usr: forUser) {
			System.out.println("UserID: " + usr.getUserID() + " Password is: "  + usr.getPassword());
			ActivityLog log = new ActivityLog(usr.getUserID() , "1", "Used Forgot Password ");
			user.logUser(log);
		}
		response.sendRedirect("login.jsp");

	}
	private void rateBook(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String userID = request.getParameter("userID");
	    String bookId = request.getParameter("bookId");
	    int rating = Integer.parseInt(request.getParameter("rating"));
	    dao.updateBookRating(bookId, rating);
		ActivityLog log = new ActivityLog(userID, "1", "Book: " + bookId + " Rated" + rating);
		user.logUser(log);
	    request.getRequestDispatcher("LibraryServlet?action=books").forward(request, response);
	}
}
