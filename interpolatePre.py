#coding=gbk
# Import system modules  
import sys, string, os  
# Import arcpy module  
import arcpy

def removeFilesInDir(targetDir):
    for file in os.listdir(targetDir):
        targetFile = targetDir + os.sep + file
        if os.path.isfile(targetFile):
            try:
                os.remove(targetFile)
            except Exception, detail:
                print "删除失败", targetFile
                print deatil

def arcpyFun():
    try:  
        dir = 'D:\\Project\\interpolate\\Anuspl43\\bin\\GRD\\TEM\\MAX'  
        #reload(sys)
        #sys.setdefaultencoding('utf-8')

        files = os.listdir(dir)  
        for f in files:  
           if os.path.splitext(f)[1] == '.grd' and cmp(f[0:3], "GRD") == 0:
                # Script arguments...  
                Input_raster_file = dir + os.sep + f
                # Local variables...  
                Raster_Format = "TIFF"  
                Output_Workspace = "D:\\Project\\interpolate\\Anuspl43\\bin\\GRD\\TEM\\MAX\\TIFF"  
  
                # =============== file name process ======================  
                basename = os.path.splitext(f)[0];  
                Output_raster = Output_Workspace + os.sep + basename + ".tif";  
                #格式转换
                if os.path.exists(Output_raster) == False:  
                    print Input_raster_file  
                    # Process: Raster To Other Format (multiple)...  
                    arcpy.RasterToOtherFormat_conversion(Input_raster_file, Output_Workspace, Raster_Format)
                    print Output_raster, "格式转换成功"
                    removeFilesInDir(dir)
                    print dir, "删除成功"
                #裁剪
                clipMask = "D:\\Project\\interpolate\\云南.shp";
                outTifPath = Output_Workspace + os.sep + "Clip"
                outTif = outTifPath + os.sep + basename + "_clip.tif"
                if os.path.exists(outTifPath) == False:
                    os.makeddirs(outTifPath)
                arcpy.Clip_management(Output_raster, "#", outTif, clipMask, "#", "ClippingGeometry")
                print outTif, "裁剪成功"
                removeFilesInDir(Output_Workspace)
                print Output_Workspace, "删除成功"
                #转换
                finalFile = dir + os.sep + "ASC" + os.sep + basename + ".txt"
                arcpy.RasterToASCII_conversion(outTif, finalFile)
                print finalFile, "转换成功"
                removeFilesInDir(outTifPath)
                print outTifPath, "删除成功"
    except Exception, detail:
        print "catch exception"
        print detail


if __name__ == "__main__":
    arcpyFun()


