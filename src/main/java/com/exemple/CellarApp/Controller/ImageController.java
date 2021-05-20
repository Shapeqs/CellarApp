package com.exemple.CellarApp.Controller;
import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Service.Bottle.BottleService;
import com.exemple.CellarApp.EnumUtils.URLs;
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
@RequestMapping("/api/images")
public class ImageController {

    private final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private BottleService bottleService;

    private final HashMap<String, Integer> LOCATIONS = new HashMap<>() {{
        put("VintagePosXMin", 900);
        put("VintagePosXMax", 1000);
        put("VintagePosYMin", 1800);
        put("VintagePosYMax", 1900);
        put("CastelPosXMin", 900);
        put("CastelPosXMax", 1000);
        put("CastelPosYMin", 2400);
        put("CastelPosYMax", 2450);
        put("NamingPosXMin", 900);
        put("NamingPosXMax", 1000);
        put("NamingPosYMin", 2500);
        put("NamingPosYMax", 2550);
        put("YearPosXMin", 900);
        put("YearPosXMax", 1000);
        put("YearPosYMin", 2600);
        put("YearPosYMax", 2650);
        put("AlcoolPosXMin", 1150);
        put("AlcoolPosXMax", 1250);
        put("AlcoolPosYMin", 2900);
        put("AlcoolPosYMax", 3000);
    }};

    private final HashMap<String, Font> FONTS = new HashMap<>() {{
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(URLs.Font.url)).deriveFont(120f);
            ge.registerFont(font);
            put("Vintage", font);
            put("Alcool", font.deriveFont(50f));
            put("Year", font.deriveFont(80f));
            put("Castel", font.deriveFont(80f));
            put("Naming", font.deriveFont(60f));
        } catch (FontFormatException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }};

    @GetMapping("/{id}")
    public byte[] getImage(@PathVariable Integer id) {
        Bottle bottle = bottleService.findOne(id);
        try {
            // Get the bottle image base
            String url = getURLForTheRightTemplate(bottle);
            assert url != null;
            BufferedImage image = ImageIO.read(new File(url));
            addVintageToImage(image, WordUtils.wrap(bottle.getVintage(), 14));
            addAttributToImage("Castel", bottle.getCastel().getName(), image, Color.BLACK);
            addAttributToImage("Year", String.valueOf(bottle.getYear()), image, Color.RED);
            addAttributToImage("Naming", "Appelation " + bottle.getNaming().getName(), image, Color.DARK_GRAY);
            addAttributToImage("Alcool", bottle.getAlcool()+"% vol", image, Color.BLACK);

            // Create the new image
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            // Send this new image
            LOGGER.info("Get image from " + bottle.toString());
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
        int posY = LOCATIONS.get(attribut+"PosYMin");

        return (isPosX) ?  getCentredPos(posX, widthOfRectangle, expectedWidth) : getCentredPos(posY, heightOfRectangle, expectedHeight);

    }

    private int getCentredPos(Integer pos, Integer lengh, double expected) {
        return (int) (pos + (lengh - expected) / 2);
    }

    private void addVintageToImage(BufferedImage image, String vintage) {
        // Create a font for our text
        Font fontTesting = FONTS.get("Vintage");
        double i;
        int posY = LOCATIONS.get("VintagePosYMin");
        for (String line : vintage.split("\r\n")) {
            int widthOfVintageRectangle = LOCATIONS.get("VintagePosXMax") - LOCATIONS.get("VintagePosXMin");

            // Getting expected width and height
            Graphics2D graphics = image.createGraphics();
            FontMetrics ruler = graphics.getFontMetrics(fontTesting);
            GlyphVector vector = fontTesting.createGlyphVector(ruler.getFontRenderContext(), line);
            Shape outline = vector.getOutline(0, 0);

            double expectedWidth = outline.getBounds().getWidth();

            int posX = getCentredPos(LOCATIONS.get("VintagePosXMin"), widthOfVintageRectangle, expectedWidth);
            if (line.matches("[àÀLlDd][eéEaàA]?[s]?")) {
                float div = 2;
                Font font = fontTesting.deriveFont(fontTesting.getStyle(), fontTesting.getSize() / div);
                posY += graphics.getFontMetrics(font).getHeight();
                addTextToImage(image, font, line, posX, posY, Color.BLACK);
            } else {
                posY += graphics.getFontMetrics(fontTesting).getHeight();
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

    private String getURLForTheRightTemplate(Bottle bottle) {
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
