
package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.TipoItem;

import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
/**
 *
 * @author david
 */
public interface TipoItemDAO {
    
    public void save(TipoItem ti) throws PersistenceException;

    public TipoItem load(int id) throws PersistenceException;

    public List<TipoItem> loadTiposItems() throws PersistenceException;
    
}
