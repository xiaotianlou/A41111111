package featureRenderer.Shape;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import transformation.builtinADT.MeshADT;

public interface Generable {

    MeshADT Genering(MeshADT m, Seed seed);
    default double Simulink(double input){
        return 1/(1+Math.exp(-input));
    }

}