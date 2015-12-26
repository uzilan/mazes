package com.landsmann.mazes.rest;

import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.MazeCreator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import static com.landsmann.mazes.util.MazeCreator.MazeType.ALDOUS_BRODER;
import static spark.Spark.get;

public class MazeResource {

    public static void main(String[] args) {
        get("/maze", (request, response) -> {
            Map<String, String> params = request.params();

            String type = params.get("type");

            Grid grid = MazeCreator.create(ALDOUS_BRODER, 20, 20, true);
            BufferedImage img = grid.toImage();

            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            ImageIO.write(img, "png", baos);
            baos.flush();

            String base64String = Base64.encode(baos.toByteArray());
            baos.close();

            byte[] bytes = Base64.decode(base64String);

            HttpServletResponse raw = response.raw();

            raw.getOutputStream().write(bytes);
            raw.getOutputStream().flush();
            raw.getOutputStream().close();

            return response.raw();
        });
    }
}
