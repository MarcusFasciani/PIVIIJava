package com.controller;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
public class MedicoApplication
{	
	public StringBuffer create(Medico j)
    {
    	StringBuffer mensagem = new StringBuffer();
    	try
    	{  //Conexão Mongo
    		if(validar(j.getNome(), j.getSalario() + "", j.getEspecializacao(),mensagem))
    		{
	            MongoClientURI uri = new MongoClientURI("mongodb://marcusfasciani:senha123@ds043002.mongolab.com:43002/marcusfasciani");
	            MongoClient mongo = new MongoClient(uri);
	            DB db = mongo.getDB("marcusfasciani");
		        DBCollection col = db.getCollection("marcusfasciani");
		        
		        //create user
		        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		        ObjectId id = new ObjectId();
		        docBuilder.add("_id", id);
		        docBuilder.append("nome", j.getNome());
		        docBuilder.append("salario", j.getSalario());
		        docBuilder.append("especializacao", j.getEspecializacao());
		        
		        DBObject doc = docBuilder.get();
		        WriteResult result = col.insert(doc);
		        System.out.println(result.getUpsertedId());
		        System.out.println(result.getN());
		        System.out.println(result.isUpdateOfExisting());
		        System.out.println(result.getLastConcern());
		        j.set_id(id);
		        mongo.close();
    		}
    	}
    	catch(Exception ex)
    	{
    		mensagem.append(ex.getMessage());
    	}
    	return mensagem;
    }

	public List<Medico> todos()
    {
    	try
    	{
            MongoClientURI uri = new MongoClientURI("mongodb://marcusfasciani:senha123@ds043002.mongolab.com:43002/marcusfasciani");
            MongoClient mongo = new MongoClient(uri);
            DB db = mongo.getDB("marcusfasciani");
	        DBCollection col = db.getCollection("marcusfasciani");
	        
	  	  	DBCursor cursor = col.find();
	  	  	List<Medico> users = new ArrayList<Medico>();
	  	  	while (cursor.hasNext()) {
	  	  		DBObject current = cursor.next();
	  	  		Gson gson = new Gson();
	  	  		Medico ca = gson.fromJson(current.toString(), Medico.class);
	  	  		users.add(ca);
	  	  	}
	        mongo.close();
	        
	    	return users;
    	}
    	
    	catch(Exception ex)
    	{
    		return null;
    	}
    }
	
	public boolean validar(String nome, String salario, String especializacao ,StringBuffer mensagem)
	{
		if(salario == null || salario.equals(""))
		{
			mensagem.append("Favor preencher o campo salario.");
			return false;
		}
		
		else if(especializacao == null || especializacao.equals(""))
		{
			mensagem.append("Favor digite uma especializacao.");
			return false;
		}
		else if(nome == null || nome.equals(""))
		{
			mensagem.append("Favor digitar o nome do médico.");
			return false;
		}
		else if(Integer.parseInt(salario) > 100000)
		{
			mensagem.append("Salario de médico não pode ser acima de 100000");
			return false;
		}
		else
		{
			return true;
		}
	}
}
