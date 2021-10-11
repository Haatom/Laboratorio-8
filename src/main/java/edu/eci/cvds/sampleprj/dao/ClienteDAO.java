
package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.samples.entities.Cliente;
import java.util.List;
import java.sql.Date;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ClienteDAO {
    
    public Cliente load(long id) throws PersistenceException;

    public void save(Cliente cli) throws PersistenceException;

    public void agregarItemRentado(long docu, int id, Date fechaini, Date fechafin) throws PersistenceException;

    public List<Cliente> consultarClientes() throws PersistenceException;

    public void vetar(long docu, boolean estado) throws PersistenceException;
    
}
