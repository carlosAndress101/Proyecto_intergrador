package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //relaciones.
    @FXML private ImageView UserArrow;
    @FXML private AnchorPane userPane;

    @FXML private AnchorPane ViewPane;
    @FXML private ImageView ViewArrow;

    @FXML private JFXButton agregar;
    @FXML private JFXTextField nametext;
    @FXML private JFXTextField textApellido;
    @FXML private JFXTextField IDtext;

    @FXML private TableView<Persona> tblPersonas;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn coluApellido;
    @FXML private TableColumn colCodigo;
    private ObservableList<Persona> personas;



    //salir de la aplicacion
    public void onExitButtonClicked(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }
    public void onUserClicked(MouseEvent event){
        if (userPane.isVisible()){
            userPane.setVisible(false);
            UserArrow.setVisible(false);
            return;
        }
        userPane.setVisible(true);
        UserArrow.setVisible(true);
        ViewPane.setVisible(false);
        ViewArrow.setVisible(false);

    }
    public void onViewButtonClicked(MouseEvent event){
        if (ViewPane.isVisible()){
            ViewPane.setVisible(false);
            ViewArrow.setVisible(false);
            return;
        }
        ViewPane.setVisible(true);
        ViewArrow.setVisible(true);
        userPane.setVisible(false);
        UserArrow.setVisible(false);
    }


    public void initialize(URL location, ResourceBundle resources) {
        //creamos el observable.
        personas = FXCollections.observableArrayList();
        //asignamos las columnas con los atributos persona.
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.coluApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
    }

    public void agregar(ActionEvent event){

        try {
            //Datos del formulario.
            String nombre = this.nametext.getText();
            String apellido = this.textApellido.getText();
            long codigo = Long.parseLong(this.IDtext.getText());


            //creo una persona.
            Persona perso = new Persona(nombre,apellido,codigo);
            //Verificamos si la persona esta en la lista.
            if (!this.personas.contains(perso)){
                //lo agrego a la lista.
                this.personas.add(perso);
                //set los itens
                this.tblPersonas.setItems(personas);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato erroneo");
            alert.showAndWait();
        }
    }
}