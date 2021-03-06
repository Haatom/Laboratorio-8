
package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.samples.entities.Cliente;
import java.sql.Date;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import org.mybatis.guice.transactional.Transactional;
import javax.inject.Inject;
import java.sql.Date;
import java.util.List;

public class MyBATISClienteDAO  implements ClienteDAO{
    
    @Inject
    private ClienteMapper ClienteMapper;

    @Override
    public Cliente load(long id) throws PersistenceException {
       try{
            return ClienteMapper.consultarCliente(id);
        }
        catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar cliente con id " + id,e);
        }
    }

    @Override
    public void save(Cliente cli) throws PersistenceException {
        try{
            ClienteMapper.agregarCliente(cli);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al agregar cliente.",e);
        }
    }

    @Override
    public void agregarItemRentado(long docu, int id, Date fechaini, Date fechafin) throws PersistenceException {
         try{
            ClienteMapper.agregarItemRentadoACliente(docu,id,fechaini,fechafin);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al agregar item rentado con id " + id + " al cliente con documento " + docu +".",e);
        }
    }
    

    @Override
    public List<Cliente> consultarClientes() throws PersistenceException {
        try{
            return ClienteMapper.consultarClientes();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar.",e);
        }
        
    }

    @Override
    public void vetar(long docu, boolean estado) throws PersistenceException {
         try{
            ClienteMapper.vetar(docu,estado);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al vetar.",e);
        }
    }
    
}
