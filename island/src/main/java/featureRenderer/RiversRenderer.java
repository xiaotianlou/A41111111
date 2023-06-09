package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

public class RiversRenderer implements Renderable {

    @Override
    public MeshADT Rendering(MeshADT meshADT, Seed seed) {
        int i = seed.getRiverNumber();

        for (var p : meshADT.getPolygons()) {
            if (p.isIsland()) {
                for (var v : p.getVertices()) {
                    v.setAroundWater(false);
                }
            }
        }
        for (var p : meshADT.getPolygons()) {
            if (!p.isIsland()) {
                for (var v : p.getVertices()) {
                    v.setAroundWater(true);
                }
            }
        }

        for (int n = i; n > 0; n--) {
            VertexADT vertexADT = meshADT.getVertices().get(n * 101 % 3000);
            int id = vertexADT.getId();

            boolean flag = true;
            while (meshADT.getVertices().get(id).isCentroid() || meshADT.getVertices().get(id).isAroundWater()) {
                if (id - 1 < 10 && flag) {
                    id += 2950;
                } else {
                    id -= 3;
                }
            }

            vertexADT = meshADT.getVertices().get(id);

            VertexADT next_vertexADT = vertexADT;

            boolean end = false;

            vertexADT.setColor(105 + "," + 200 + "," + 225);

            while (!vertexADT.isAroundWater() && !end) {

                end = true;

                double min = vertexADT.getElevation();

                for (var v : vertexADT.getVertices()) {

                    if (!v.isCentroid()) {
                        if (min > v.getElevation()) {
                            min = v.getElevation();
                            next_vertexADT = meshADT.getVertices().get(v.getId());
                            end = false;
                        }
                    }
                }
                if (!end) {
                    SegmentADT segmentADT = meshADT.getSegment(next_vertexADT,vertexADT);
                    if (segmentADT.getThickness() == 1) {
                        segmentADT.setThickness(5);
                    }
                    segmentADT.setColor(100 + "," + 155 + "," + 255);
                    vertexADT.setRiver(true);
                    next_vertexADT.setRiver(true);
                    vertexADT.setColor(255 + "," + 100 + "," + 100);
                    float temp = segmentADT.getThickness();
                    segmentADT.setThickness(5 + temp);
                    vertexADT = next_vertexADT;
                }

            }

        }
        return meshADT;
    }

}
