package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;


public interface ItemDAO {

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;

}
