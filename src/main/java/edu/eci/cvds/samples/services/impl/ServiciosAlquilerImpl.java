package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;

import edu.eci.cvds.sampleprj.dao.TipoItemDAO;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;


@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;
   @Inject
   private ItemRentadoDAO itemRentadoDAO;
   @Inject
    private TipoItemDAO tipoItemDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
           Optional<Cliente> optionalCliente= Optional.ofNullable( clienteDAO.load(docu) );
           optionalCliente.orElseThrow(() -> new ExcepcionServiciosAlquiler(ExcepcionServiciosAlquiler.NO_ITEM));
           return optionalCliente .get();
        } 
        catch (PersistenceException persistenceException) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el Cliente  con id:"+docu, persistenceException);
       }
       
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try{
           consultarCliente( idcliente);
           return itemRentadoDAO.consultarItemsRentados(idcliente);
       }
       catch (PersistenceException persistenceException) {
           throw new ExcepcionServiciosAlquiler("Error al consultar items rentados por el id:"+ idcliente, persistenceException);
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try{
            return clienteDAO.consultarClientes();
        }
        catch (PersistenceException persistenceException){
            throw new ExcepcionServiciosAlquiler("Error al consultar clientes.",persistenceException);
        }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           Optional<Item> optionalItem= Optional.ofNullable(itemDAO.load(id) );
           optionalItem.orElseThrow(() -> new ExcepcionServiciosAlquiler(ExcepcionServiciosAlquiler.NO_ITEM));
           return optionalItem.get();
       } catch (PersistenceException persistenceException) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item con id:"+ id,persistenceException);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
       try{
            return itemDAO.load();
        }
        catch (PersistenceException persistenceException){
            throw new ExcepcionServiciosAlquiler("Error al consultar Items disponibles.",persistenceException);
        }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       try {
            Optional<TipoItem> optionalTipoItem = Optional.ofNullable( tipoItemDAO.load(id) );
            optionalTipoItem.orElseThrow(() -> new ExcepcionServiciosAlquiler(ExcepcionServiciosAlquiler.NO_TIPOITEM));
            return optionalTipoItem.get();
        } catch (PersistenceException persistenceException) {
            throw new ExcepcionServiciosAlquiler("Error al consultar Tipo Item con id " + id, persistenceException);
        }
   }
   
   
   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       try {
            return tipoItemDAO.loadTiposItems();
        } catch (PersistenceException persistenceException) {
            throw new ExcepcionServiciosAlquiler("Error al consultar Tipo Items.", persistenceException);
        }
   }
   
   @Transactional
   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        try {
            Cliente cliente = consultarCliente( docu );
            consultarItem( item.getId());
            if( numdias < 0 ){
                throw new ExcepcionServiciosAlquiler( ExcepcionServiciosAlquiler.DIAS_INVALIDOS);
            }
            clienteDAO.agregarItemRentado(docu,item.getId(),date, Date.valueOf(date.toLocalDate().plusDays(numdias)));
        }
            catch (PersistenceException persistenceException) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el alquiler", persistenceException);
        }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
            clienteDAO.save(c);
        }
        catch (PersistenceException persistenceException) {
            throw new ExcepcionServiciosAlquiler("Error al agregar cliente.", persistenceException);
        }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       Item item = consultarItem( iditem );
        if( numdias < 0){
            throw new ExcepcionServiciosAlquiler( ExcepcionServiciosAlquiler.DIAS_INVALIDOS);
        }
        return item.getTarifaxDia() * numdias;
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try{
            consultarItem( id );
            if( tarifa < 0 ){
                throw new ExcepcionServiciosAlquiler(ExcepcionServiciosAlquiler.TARIFA_INVALIDA);
            }
            itemDAO.actualizarTarifa(id,tarifa);
        }
        catch(PersistenceException persistenceException){
            throw new ExcepcionServiciosAlquiler("Error al cambiar tarifa del item con id: " + id,persistenceException);
        }
   }
   
   @Transactional
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try{
           itemDAO.save(i);
       }
       catch (PersistenceException persistenceException){
           throw new ExcepcionServiciosAlquiler("Error al registrar item.",persistenceException);
       }
   }
   
   
   @Transactional
   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try{
            consultarCliente( docu );
            clienteDAO.vetar(docu,estado);
        }
        catch(PersistenceException persistenceException){
            throw new ExcepcionServiciosAlquiler("Error al vetar al cliente con id: " + docu ,persistenceException);
        }
   }
  
}

