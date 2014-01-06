package org.summer.cli2web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.summer.cli2web.action.CurrentProgressAction;
import org.summer.cli2web.action.InputDataAction;
import org.summer.cli2web.action.StartProcessAction;
import org.summer.cli2web.viewmodel.ProcessStatusModel;

/**
 * Servlet implementation class Cli2WebServlet
 */
@WebServlet("/cli/*")
public class Cli2WebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cli2WebServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);

	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println(request.getRequestURI());
		response.setCharacterEncoding("UTF-8");
		System.out.println(request.getRequestURI());
		if (request.getRequestURI().endsWith("start")) {
			StartProcessAction action = new StartProcessAction();
			Process process = action
					.startProcess(
							"C:\\Users\\ajing\\Documents\\GitHub\\cli2web\\Cli2Web\\build\\classes",
							"java org.summer.cli2web.example.ProgressExample");
			request.getSession().setAttribute("currentProcess", process);
			request.getSession().setAttribute("formerModel", null);
		} else if (request.getRequestURI().endsWith("current")) {
			Process proc = (Process) request.getSession().getAttribute(
					"currentProcess");
			if (proc != null) {
				ProcessStatusModel formerModel = (ProcessStatusModel) request
						.getSession().getAttribute("formerModel");
				if (formerModel != null && formerModel.isNeedSubmitData()) {

					String data = request.getParameter("data");
					if (data == null || data.isEmpty()) {
						response.getWriter().println(
								"{\"error\": \"the data is wrong!\"}");
					} else {
						new InputDataAction().input(proc, data);
					}
				}

				ProcessStatusModel model = new CurrentProgressAction()
						.currentProgress(proc, 2000).toViewModel();
				request.getSession().setAttribute("formerModel", model);
				response.getWriter().println(model.toJson());

			}

		}

		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
