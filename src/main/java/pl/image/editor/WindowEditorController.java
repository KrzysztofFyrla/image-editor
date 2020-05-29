package pl.image.editor;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.plaf.FileChooserUI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Krzysztof
 * @project ImageEditor
 */
public class WindowEditorController implements Initializable {

    @FXML
    public CheckBox shadow;
    @FXML
    public CheckBox blur;
    @FXML
    public StackPane root;
    @FXML
    public ImageView myImageView;
    @FXML
    public Button buttonLoadImage;
    @FXML
    public Button buttonSaveImage;
    @FXML
    public Slider bright;
    @FXML
    public Slider contrast;
    @FXML
    public Slider saturation;
    @FXML
    public Slider hue;
    @FXML
    public Slider sepia;
    @FXML
    public Slider glow;
    @FXML
    public Slider gauss;
    @FXML
    public Slider bloom;
    @FXML
    public CheckBox innshadow;
    @FXML
    public CheckBox light;
    @FXML
    public Slider motionAngle;
    @FXML
    public Slider motionRadius;
    @FXML
    public Button resetButton;

    private EditImage Images = new EditImage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Images.Blur(blur);
        Images.DropShadow(shadow);
        Images.BrightSlider(bright);
        Images.ContrastSlider(contrast);
        Images.SaturationSlider(saturation);
        Images.HueSlider(hue);
        Images.SepiaSlider(sepia);
        Images.GlowSlider(glow);
        Images.GaussSlider(gauss);
        Images.BloomSlider(bloom);
        Images.InnerShadow(innshadow);
        Images.Light(light);
        Images.MotionAngleEvent(motionAngle);
        Images.MotionRadiusEvent(motionRadius);
    }

    @FXML
    public void LoadImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);

        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            myImageView.setImage(image);
            reset();
            Images.setImage(myImageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SaveImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(myImageView.snapshot(null, null), null);
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void reset() {
        contrast.setValue(0);
        hue.setValue(0);
        saturation.setValue(0);
        bright.setValue(0);
        shadow.setSelected(false);
        blur.setSelected(false);
        sepia.setValue(0);
        glow.setValue(0);
        gauss.setValue(0);
        bloom.setValue(1);
        motionAngle.setValue(0);
        motionRadius.setValue(0);
        innshadow.setSelected(false);
        light.setSelected(false);
    }
}
