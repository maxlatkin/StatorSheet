<h1 align="center">StatorSheet</h1>

# Description

StatorSheet is an auxiliary application to Creo Parametric 5 that generates stator segments (or sheet) based on information from Windchill (PDM) documents. These segments are used in electrical machines of various sizes.

## Graphical interface
The application has its own graphical interface, in which the necessary documents from Windchill are selected, as well as the type of segment.

![](media/GUI.gif)

## Generating segments
After entering the data, the normal and ventilation stator segments are automatically created. Also, information is filled in for them in the template drawings.

![](media/main_part.gif)

## Saving
After generation, the models and drawings are saved in Windchill, and dxf files are generated for the models. These dxf files are loaded into CNC laser machines to make segments.
