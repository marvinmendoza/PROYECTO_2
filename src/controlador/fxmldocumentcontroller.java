/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.OrdenCompra;

/**
 *
 * @author hp
 */
public class fxmldocumentcontroller implements Initializable {

    @FXML
    private TextField txtNit;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txttotal;
    @FXML
    private Label LblNit;
    @FXML
    private Label LblNombre;
    @FXML
    private Label LblDes;
    @FXML
    private Label LblMonto;
    @FXML
    private Button btnCrearOrden;
    @FXML
    private TableView<OrdenCompra> TblOrdenComp;
    @FXML
    private TableColumn ColNit;
    @FXML
    private TableColumn ColNombre;
    @FXML
    private TableColumn ColDescripcion;
    @FXML
    private TableColumn ColMonto;
    @FXML
    private TextField txtIdOrden;
    @FXML
    private Label LblId;
    
    private ObservableList<OrdenCompra> Ocompra;
    @FXML
    private TableColumn<?, ?> ColIdOC;
    
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Ocompra = FXCollections.observableArrayList();
       
        this.ColIdOC.setCellValueFactory(new PropertyValueFactory("idcompra"));
        this.ColNit.setCellValueFactory(new PropertyValueFactory("nit"));
        this.ColNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
         this.ColDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.ColMonto.setCellValueFactory(new PropertyValueFactory("monto"));
              
    } 
    
    /*............................................................*/
    
    
     public void cerrarVentana(){
    
      try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
        
        Parent root = loader.load();
        
        menucontroller controlador = loader.getController();
        
        
        Scene scene = new Scene (root);
        Stage stage = new Stage ();
        
        stage.setScene(scene);
        stage.show();
        
        stage.setTitle("Sistema Menu");
        
        
        Stage miStage = (Stage) this.btnCrearOrden.getScene().getWindow();
        miStage.close();
     
        
        
        }catch (IOException ex){
        
            Logger.getLogger(clientecontroller.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
    
    
    /*----------------------------------------------------------*/
    @FXML
    private void crearOrdenCompra(ActionEvent event) {
         try {
            int idcompra = Integer.parseInt(this.txtIdOrden.getText());
            String nit = this.txtNit.getText();
            String nombre = this.txtDescripcion.getText();
            String descripcion = this.txtDescripcion.getText();
            int monto = Integer.parseInt(this.txttotal.getText());
            
        OrdenCompra n = new OrdenCompra (idcompra, nit, nombre, descripcion, monto);
        
        if (!this.Ocompra.contains(n)) {
                // Lo añado a la lista
                this.Ocompra.add(n);
                // Seteo los items
                this.TblOrdenComp.setItems(Ocompra);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Se agrego Orden de compra correctamente");
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La orden ya existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
}
        
    }
    

