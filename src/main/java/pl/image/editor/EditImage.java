package pl.image.editor;

import javafx.scene.CacheHint;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Krzysztof
 * @project ImageEditor
 */
public class EditImage {

    private ImageView Image;
    private ColorAdjust AdjustEffect = new ColorAdjust();
    private DropShadow shadow = new DropShadow(5, Color.CRIMSON);
    private BoxBlur boxblur = new BoxBlur();
    private MotionBlur motion = new MotionBlur();
    private SepiaTone sepia = new SepiaTone();
    private Glow glow = new Glow();
    private GaussianBlur gauss = new GaussianBlur();
    private Bloom bloom = new Bloom();
    private Lighting light = new Lighting();
    private InnerShadow shad = new InnerShadow();

    public void setImage(ImageView myImage) {
        this.Image = myImage;
        Init();

    }

    public void Blur(CheckBox checkBox) {
        checkBox.selectedProperty().addListener((((observable, oldValue, newValue) -> {
            if(oldValue == false) {
                boxblur.setHeight(8);
                boxblur.setWidth(6);
                boxblur.setIterations(3);
            } else {
                boxblur.setIterations(0);
                boxblur.setWidth(0);
                boxblur.setHeight(0);
            }
        })));
    }

    public void DropShadow(CheckBox checkBox) {
        checkBox.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == false) {
                shadow.setHeight(40);
                shadow.setWidth(40);
            } else {
                shadow.setWidth(0);
                shadow.setHeight(0);
            }
        }));
    }

    public void BrightSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AdjustEffect.setBrightness(newValue.doubleValue());
        });
    }

    public void ContrastSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AdjustEffect.setContrast(newValue.doubleValue());

        });
    }

    public void SaturationSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AdjustEffect.setSaturation(newValue.doubleValue());

        });
    }

    public void HueSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AdjustEffect.setHue(newValue.doubleValue());

        });
    }

    public void SepiaSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sepia.setLevel(newValue.doubleValue());

        });
    }

    public void GlowSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            glow.setLevel(newValue.doubleValue());

        });

    }

    public void GaussSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gauss.setRadius(newValue.doubleValue());

        });
    }

    public void BloomSlider(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bloom.setThreshold(newValue.doubleValue());

        });
    }

    public void InnerShadow(CheckBox myCheckbox) {
        myCheckbox.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == false) {
                shad.setBlurType(BlurType.GAUSSIAN);
                shad.setColor(Color.WHEAT);
                shad.setRadius(70);
                shad.setHeight(90);
                shad.setWidth(90);

            } else {
                shad.setHeight(0);
                shad.setWidth(0);
                shad.setRadius(0);
            }
        }));
    }

    public void Light(CheckBox myCheckbox) {
        myCheckbox.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == false) {
                light.setDiffuseConstant(2.0);
                light.setSpecularConstant(2.0);
                light.setSpecularExponent(40);
                light.setSurfaceScale(6);

            } else {
                light.setDiffuseConstant(2.0);
                light.setSpecularConstant(0.3);
                light.setSpecularExponent(20);
                light.setSurfaceScale(1.5);
            }
        }));
    }

    public void MotionRadiusEvent(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            motion.setRadius(newValue.doubleValue());

        });
    }

    public void MotionAngleEvent(Slider mySlider) {
        mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            motion.setAngle(newValue.doubleValue());

        });
    }

    private void Init() {
        shadow.setWidth(0);
        shadow.setHeight(0);
        light.setDiffuseConstant(2.0);
        light.setSpecularConstant(0.3);
        light.setSpecularExponent(20);
        light.setSurfaceScale(1.5);
        boxblur.setIterations(0);
        boxblur.setWidth(0);
        boxblur.setHeight(0);
        motion.setAngle(0);
        motion.setRadius(0);
        glow.setLevel(0);
        sepia.setLevel(0);
        gauss.setRadius(0);
        bloom.setThreshold(1);
        shad.setHeight(0);
        shad.setWidth(0);
        shad.setRadius(0);
        light.setContentInput(shad);
        bloom.setInput(light);
        gauss.setInput(bloom);
        sepia.setInput(gauss);
        glow.setInput(sepia);
        motion.setInput(glow);
        boxblur.setInput(motion);
        shadow.setInput(boxblur);
        AdjustEffect.setInput(shadow);
        Image.setEffect(AdjustEffect);
        Image.setCache(true);
        Image.setCacheHint(CacheHint.SPEED);
    }
}
