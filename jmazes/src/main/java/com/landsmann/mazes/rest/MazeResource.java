package com.landsmann.mazes.rest;

import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.MazeCreator;
import com.landsmann.mazes.util.MazeCreator.MazeType;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONArray;
import spark.QueryParamsMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static spark.Spark.before;
import static spark.Spark.get;

public class MazeResource {

    public static void main(String[] args) {

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");

            response.header("Cache-Control", "no-cache, no-store, must-revalidate");
            response.header("Pragma", "no-cache");
            response.header("Expires", "0");
        });

        get("/mazetypes", ((request, response) -> {
            JSONArray json = new JSONArray();
            Arrays.stream(MazeType.values()).forEach(t -> json.put(t.getName()));
            return json;
        }));

        get("/maze", (request, response) -> {
            QueryParamsMap params = request.queryMap();

            MazeType type = MazeType.getFromName(params.get("type").value());
            int rows = Integer.parseInt(params.get("rows").value());
            int columns = Integer.parseInt(params.get("columns").value());
            boolean color = Boolean.valueOf(params.get("color").value());

            Grid grid = MazeCreator.create(type, rows, columns, color);
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