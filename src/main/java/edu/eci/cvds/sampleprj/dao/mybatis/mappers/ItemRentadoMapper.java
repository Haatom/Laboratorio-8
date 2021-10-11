package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ItemRentadoMapper {
    
    public List<ItemRentado> consultarItemsRentados( long id);


    
    public List<ItemRentado> consultarItemRentados();

    
    public ItemRentado consultarItemRentado( int idItem,  long idCliente );


}
