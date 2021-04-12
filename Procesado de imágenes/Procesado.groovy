def project = getProject()
def path = 'D:/Tfm/Proyectos/LumA/'

for (entry in project.getImageList()) {
    def imageData = entry.readImageData()
    def hierarchy = imageData.getHierarchy()
    def annotations = hierarchy.getAnnotationObjects()
    def name = entry.getImageName()

    resetSelection();
    createSelectAllObject(true);
    selectAnnotations();

    setImageType('BRIGHTFIELD_H_E');
    setColorDeconvolutionStains('{"Name" : "H&E default", "Stain 1" : "Hematoxylin", "Values 1" : "0.65111 0.70119 0.29049 ", "Stain 2" : "Eosin", "Values 2" : "0.2159 0.8012 0.5581 ", "Background" : " 255 255 255 "}');
    runPlugin('qupath.imagej.detect.cells.WatershedCellDetection', '{"detectionImageBrightfield": "Hematoxylin OD",  "requestedPixelSizeMicrons": 0.5,  "backgroundRadiusMicrons": 8.0,  "medianRadiusMicrons": 0.0,  "sigmaMicrons": 1.5,  "minAreaMicrons": 10.0,  "maxAreaMicrons": 400.0,  "threshold": 0.4,  "maxBackground": 2.0,  "watershedPostProcess": true,  "cellExpansionMicrons": 5.0,  "includeNuclei": true,  "smoothBoundaries": true,  "makeMeasurements": true}');
    removeMeasurements(qupath.lib.objects.PathCellObject, "Cytoplasm: Hematoxylin OD mean", "Cytoplasm: Hematoxylin OD std dev", "Cytoplasm: Hematoxylin OD max", "Cytoplasm: Hematoxylin OD min", "Cytoplasm: Eosin OD mean", "Cytoplasm: Eosin OD std dev", "Cytoplasm: Eosin OD max", "Cytoplasm: Eosin OD min");
    saveDetectionMeasurements(path+name+'_measures.csv')
}