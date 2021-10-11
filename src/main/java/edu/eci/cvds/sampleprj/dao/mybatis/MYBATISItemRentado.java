
package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

import java.util.List;

/**
 *
 * @author david
 */
public class MYBATISItemRentado implements ItemRentadoDAO{
    @Inject
    private ItemRentadoMapper ItemRentadoMapper;
    
    
    @Override
    public List<ItemRentado> load() throws PersistenceException {
        try{
            return ItemRentadoMapper.consultarItemRentados();
        }
        catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el los items rentados;" ,e);
        }
        
    }

    @Override
    public List<ItemRentado> consultarItemsRentados(long idCliente) throws PersistenceException {
         try{
            return ItemRentadoMapper.consultarItemsRentados(idCliente);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items rentados del cliente con id:" + idCliente, e);
        }
    
    }

    @Override
    public ItemRentado consultarItemRentado(int idItem, long idCliente) throws PersistenceException {
        try{
            return ItemRentadoMapper.consultarItemRentado( idItem,idCliente );
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item rentado con id:"+idItem, e);
        }
    }
    
    
}
