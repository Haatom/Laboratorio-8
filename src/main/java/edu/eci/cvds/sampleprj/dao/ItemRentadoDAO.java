
package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ItemRentadoDAO {
    
    public List<ItemRentado> load()throws PersistenceException;
    public List<ItemRentado> consultarItemsRentados(long idCliente) throws PersistenceException;
    public ItemRentado consultarItemRentado( int  idItem ,long idCliente) throws PersistenceException;
    
}
