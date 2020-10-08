


package controlador;

import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Eder Rodríguez
 */
public class LoginController {
    
    public TextField txtUsuario;
    public PasswordField txtPassword;
   
    
    //accion del button 
    public void validaringreso(ActionEvent e){
    
    //1. usuario = eder, password: eder0
    if(txtUsuario.getText().equals("eder") && txtPassword.getText().equals("eder0")){
    // le dammos acceso
        //JOptionPane.showMessageDialog(null, "Acceso Correcto");
        try {
        
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/ClienteVista.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
            
              Pane mipane  = (Pane) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Sistema Menu");
                stage.setScene(new Scene(mipane, 600, 500));
                stage.show();

                //cerrar el login
                // get a handle to the stage
                Stage stage2 = (Stage) txtUsuario.getScene().getWindow();
                // do what you have to do
                stage2.close();
                
        }catch (Exception ee){
        
        ee.printStackTrace();
        }
      
                
    
    }else{
        
        
        
    //denegamos el acceso
    
     //JOptionPane.showMessageDialog(null, "Acceso Denegado");
     
      Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setTitle("ErrorLogin");
            alert.setContentText("ERROR LOGIN");
            alert.showAndWait();

     
   
    
    }
    
    }
    
  
    
    
}
