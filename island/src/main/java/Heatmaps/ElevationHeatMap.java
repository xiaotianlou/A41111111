package Heatmaps;

import transformation.builtinADT.MeshADT;

import java.awt.*;

public class ElevationHeatMap extends HeatMap{
//    public ElevationHeatMap(MeshADT m) {
//        super(m);
//    }

    public ElevationHeatMap(MeshADT meshADT) {
        super(meshADT);
        heatMapInitialization();
        build(this.meshADT);
    }

    @Override
    public void build(MeshADT meshADT) {
        double max=0;
        for (var p: meshADT.getPolygons()){
            if (p.getElevation()>max){
                max = p.getElevation();
            }
        }
        for (var p: meshADT.getPolygons()){
            double n = p.getElevation()/max;
            Color color=new Color((int)(50*n), (int)(50*n), 255);
            p.getInfoSet().setColor(color);
        }
    }


}
