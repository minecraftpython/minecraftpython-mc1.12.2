package org.sapphon.minecraft.modding.minecraftpython.io.file;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JavaFileIOHelper {

    public static JavaFileIOHelper SINGLETON = new JavaFileIOHelper();

    private JavaFileIOHelper() {
    }

    public String getTextContentOfFile(File file) {
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            JavaProblemHandler.printErrorMessageToDialogBox(new Exception(
                    "Could not get contents of file " + file.getAbsolutePath(),
                    e));
            return "";
        }
    }

    public void setTextContentOfFile(File file, String content) {
        try {
            FileUtils.writeStringToFile(file, content);
        } catch (IOException e) {
            JavaProblemHandler.printErrorMessageToDialogBox(new Exception(
                    "Could not write contents of file " + file.getAbsolutePath(),
                    e));
        }

    }

    public void appendStringToFile(File file, String toAppend) {
        try {
            FileUtils.write(file, toAppend, true);
        } catch (IOException e) {
            JavaProblemHandler.printErrorMessageToDialogBox(new Exception(
                    "Could not append to file " + file.getAbsolutePath(), e));
        }
    }

    public void emplaceDefaultResources() {
        List<ResourceLocation> defaultResourceLocations = new ArrayList<ResourceLocation>();
        try {

            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "admin.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "baseSpell.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "blocks.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "colors.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "entities.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "minecraftpythonvec3.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "items.py"));
            defaultResourceLocations.add(new ResourceLocation(
                    "minecraftpython", "scripts" + File.separator + "base"
                    + File.separator + "particles.py"));
            defaultResourceLocations.add(new ResourceLocation("minecraftpython",
                    "scripts" + File.separator + "mp" + File.separator
                            + "your_code_here.py"));

            for (ResourceLocation location : defaultResourceLocations) {

                InputStream locationInputStream = Minecraft.getMinecraft()
                        .getResourceManager().getResource(location)
                        .getInputStream();
                if (locationInputStream != null) {
                    FileUtils.copyInputStreamToFile(locationInputStream,
                            new File(ScriptLoaderConstants.RESOURCES_PATH + File.separator + location.getResourcePath()));
                } else {
                    throw new Exception() {
                    };
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
