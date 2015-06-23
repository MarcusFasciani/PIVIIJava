import static org.junit.Assert.assertEquals;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.controller.Medico;
import com.controller.MedicoApplication;
import com.controller.SeleniumExample;

public class MedicoTest
{
	@Test
	public void TestQuandoPesoMaiorQue5000RodasDevemSer8()
	{
		MedicoApplication caminhaoApplication = new MedicoApplication();
		
		Medico m = new Medico();
<<<<<<< HEAD
		
=======
		//Mudanças na Classe teste
>>>>>>> eb613a4157fe1eb8666acc29c0199dfb38bec9d7
		m.setNome("");
		m.setEspecializacao("Ortopedista");
		m.setSalario(10000);
		StringBuffer sb = new StringBuffer();
		boolean valido = caminhaoApplication.validar(m.getNome(), m.getSalario() + "", m.getEspecializacao(),new StringBuffer());
		
		assertEquals(sb.toString(), "");
	}
<<<<<<< HEAD
}
=======
	@Test
	public void TestCriando()
	{
		MedicoApplication medicoApplication = new MedicoApplication();
		
		Medico m = new Medico();
		m.setEspecializacao("Ortopedista");
		m.setNome("Marcus");
		m.setSalario(1000);
		List<Medico> medicosAntes = medicoApplication.todos();
		medicoApplication.create(m);
		List<Medico> medicosDepois = medicoApplication.todos();
		assertEquals(medicosAntes.size() + 1 ,medicosDepois.size());
		
	}
}
>>>>>>> eb613a4157fe1eb8666acc29c0199dfb38bec9d7
