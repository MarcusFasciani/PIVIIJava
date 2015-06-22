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
		
		m.setNome("");
		m.setEspecializacao("Ortopedista");
		m.setSalario(10000);
		StringBuffer sb = new StringBuffer();
		boolean valido = caminhaoApplication.validar(m.getNome(), m.getSalario() + "", m.getEspecializacao(),new StringBuffer());
		
		assertEquals(sb.toString(), "");
	}
}