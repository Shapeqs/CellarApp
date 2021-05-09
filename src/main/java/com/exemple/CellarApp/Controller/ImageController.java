package com.exemple.CellarApp.Controller;
import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Service.Bottle.BottleService;
import com.exemple.CellarApp.URLs;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.util.HashMap;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private BottleService bottleService;

    private final HashMap<String, Integer> LOCATIONS = new HashMap<>() {{
        put("VintagePosXMin", pixelsToMetrics(410));
        put("VintagePosXMax", pixelsToMetrics(595));
        put("VintagePosYMin", pixelsToMetrics(430));
        put("VintagePosYMax", pixelsToMetrics(550));
        put("YearPosXMin", pixelsToMetrics(410));
        put("YearPosXMax", pixelsToMetrics(595));
        put("YearPosYMin", pixelsToMetrics(600));
        put("YearPosYMax", pixelsToMetrics(645));
        put("CastelPosXMin", pixelsToMetrics(420));
        put("CastelPosXMax", pixelsToMetrics(585));
        put("CastelPosYMin", pixelsToMetrics(560));
        put("CastelPosYMax", pixelsToMetrics(588));
        put("NamingPosXMin", pixelsToMetrics(420));
        put("NamingPosXMax", pixelsToMetrics(585));
        put("NamingPosYMin", pixelsToMetrics(590));
        put("NamingPosYMax", pixelsToMetrics(605));
        put("AlcoolPosXMin", pixelsToMetrics(560));
        put("AlcoolPosXMax", pixelsToMetrics(570));
        put("AlcoolPosYMin", pixelsToMetrics(740));
        put("AlcoolPosYMax", pixelsToMetrics(750));
    }};

    private final HashMap<String, Font> FONTS = new HashMap<>() {{
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font vintage = Font.createFont(Font.TRUETYPE_FONT, new File(URLs.VintageFont.url)).deriveFont(140f);
            ge.registerFont(vintage);
            put("Vintage", vintage);

            Font alcool = Font.createFont(Font.TRUETYPE_FONT, new File(URLs.AlcoolFont.url)).deriveFont(50f);
            ge.registerFont(alcool);
            put("Alcool", alcool);
            put("Year", alcool.deriveFont(70f));
            put("Castel", alcool.deriveFont(70f));
            put("Naming", alcool.deriveFont(50f));
        } catch (FontFormatException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }};

    @GetMapping("/{id}")
    public byte[] getImage(@PathVariable Integer id) {
        Bottle bottle = bottleService.findOne(id);
        BufferedImage image = null;
        try {
            // Get the bottle image base

            String url = getURL(bottle);
            image = ImageIO.read(new File(url));

            //image = ImageIO.read(new File(URLs.WhiteWineImage.url));
            addVintageToImage(image, WordUtils.wrap("Le Saint-sauveur", 14));
            addAttributToImage("Castel", "Chateau Morton", image, Color.BLACK);
            addAttributToImage("Year", String.valueOf(2020), image, Color.RED);
            addAttributToImage("Naming", "Appelation " + "Cote du rhone", image, Color.DARK_GRAY);
            addAttributToImage("Alcool", 12.3+"% vol", image, Color.BLACK);

            // Create the new image
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //File file = new File("src/main/resources/Database/WhiteWine1.png");
            ImageIO.write(image, "png", bos);

            //ImageIO.write(image, "png", file);
            // Send this new image
            return bos.toByteArray();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private void addAttributToImage(String attribut, String string, BufferedImage image, Color color) {
        Font font = FONTS.get(attribut);
        int posXC = getCentrePos(string, attribut, image, font, true);
        int posYC = getCentrePos(string, attribut, image, font, false);

        addTextToImage(image, font, string, posXC, posYC, color);
    }

    private int getCentrePos(String string, String attribut, BufferedImage image, Font font, boolean isPosX) {
        Graphics2D graphics = image.createGraphics();

        int widthOfRectangle = LOCATIONS.get(attribut+"PosXMax") - LOCATIONS.get(attribut+"PosXMin");
        int heightOfRectangle = LOCATIONS.get(attribut+"PosYMax") - LOCATIONS.get(attribut+"PosYMin");

        FontMetrics ruler = graphics.getFontMetrics(font);
        GlyphVector vector = font.createGlyphVector(ruler.getFontRenderContext(), string);
        Shape outline = vector.getOutline(0, 0);

        double expectedWidth = outline.getBounds().getWidth();
        double expectedHeight = outline.getBounds().getHeight();

        int posX = LOCATIONS.get(attribut+"PosXMin");
        int lenghX = widthOfRectangle;
        double expectedX = expectedWidth;
        int posY = LOCATIONS.get(attribut+"PosYMin");
        int lenghY = heightOfRectangle;
        double expectedY = expectedHeight;

        return (isPosX) ?  getCentredPos(posX,lenghX, expectedX) : getCentredPos(posY,lenghY, expectedY);

    }

    private int getCentredPos(Integer pos, Integer lengh, double expected) {
        return (int) (pos + (lengh - expected) / 2);
    }

    private void addVintageToImage(BufferedImage image, String vintage) {
        // Create a font for our text
        Font fontTesting = FONTS.get("Vintage");
        double i;
        for (String line : vintage.split("\r\n")) {
            int widthOfVintageRectangle = LOCATIONS.get("VintagePosXMax") - LOCATIONS.get("VintagePosXMin");

            // Getting expected width and height
            Graphics graphics = image.getGraphics();
            FontMetrics ruler = graphics.getFontMetrics(fontTesting);
            GlyphVector vector = fontTesting.createGlyphVector(ruler.getFontRenderContext(), line);
            Shape outline = vector.getOutline(0, 0);

            double expectedWidth = outline.getBounds().getWidth();

            int posX = getCentredPos(LOCATIONS.get("VintagePosXMin"), widthOfVintageRectangle, expectedWidth);
            int posY = LOCATIONS.get("VintagePosYMin");

            if (line.matches("[LlDd][eEaA][s]?")) {
                Font font = fontTesting.deriveFont(fontTesting.getStyle(), fontTesting.getSize() / 2);
                i=0.5;
                posY += graphics.getFontMetrics(font).getHeight()*i;
                addTextToImage(image, font, line, posX, posY, Color.BLACK);
            } else {
                i=1;
                posY += graphics.getFontMetrics(fontTesting).getHeight()*i;
                addTextToImage(image, fontTesting, line, posX, posY, Color.BLACK);
            }
        }

    }

    private void addTextToImage(BufferedImage image, Font font, String text, Integer posX, Integer posY, Color color) {
        AttributedString attributedText = new AttributedString(text);
        attributedText.addAttribute(TextAttribute.FONT, font);
        attributedText.addAttribute(TextAttribute.FOREGROUND, color);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int ascent = g.getFontMetrics(font).getAscent();
        g.drawString(attributedText.getIterator(), posX, posY + ascent/3);
    }

    /**
     * Math calculus to pass from pixels counting to actual image metrics
     *
     * @param pixels pixel you count
     * @return the metric which match the pixel
     */
    private int pixelsToMetrics(Integer pixels) {
        int metrics = 4167;
        float pixelsOnIllustrator = 1000;
        float i = (pixels / pixelsOnIllustrator) * metrics;
        return (int) i;
    }

    private String getURL(Bottle bottle) {
        switch (bottle.getColor()) {
            case Red:
                return URLs.RedWineImage.url;
            case Pink:
                return URLs.PinkWineImage.url;
            case White:
                return URLs.WhiteWineImage.url;
            case Yellow:
                return URLs.YellowWineImage.url;
            default:
                return null;
        }
    }

}
