package com.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet
{
	private static final long serialVersionUID = 6365446292797964305L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		MedicoApplication jsa = new MedicoApplication();
		String id = request.getParameter("_id");
		String nome = request.getParameter("nome");
		String salario = request.getParameter("salario");
		String especializacao = request.getParameter("especializacao");
		
		
		StringBuffer mensagem = new StringBuffer();
		
		try
		{
			boolean valido = jsa.validar(nome, salario, especializacao, mensagem);
			if(valido)
			{
				Medico m = new Medico();
				m.setNome(nome);
				m.setEspecializacao(especializacao);
				m.setSalario(Integer.parseInt(salario));
				mensagem.append(jsa.create(m));
			}
			response.setContentType("text/html");
			List<Medico> cl = jsa.todos();
			request.setAttribute("medicos", cl);
			mensagem = mensagem.equals("") ? mensagem.append("Salvo com sucesso!") : mensagem;
			request.setAttribute("mensagem", mensagem);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		catch(Exception ex)
		{
			mensagem.append(ex.getMessage());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		MedicoApplication jsa = new MedicoApplication();
		response.setContentType("text/html");
		List<Medico> cl = jsa.todos();
		
		request.setAttribute("medicos", cl);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}	
}
