package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.python.util.PythonInterpreter;
import org.sapphon.minecraft.modding.base.JavaFileIOHelper;
import org.sapphon.minecraft.modding.minecraftpython.ScriptLoaderConstants;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.PythonProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

import java.io.File;

public class SpellInterpreter {

	private PythonInterpreter interpreter;

	String scriptBasePath = ScriptLoaderConstants.SCRIPTS_PATH + File.separatorChar + "base";
	String basePathWithTrailingSeparator = scriptBasePath + File.separatorChar;
	String blockScriptPath = basePathWithTrailingSeparator + "blocks.py";
	String entitiesScriptPath = basePathWithTrailingSeparator + "entities.py";
	String itemScriptPath = basePathWithTrailingSeparator + "items.py";
	String baseSpellPath = basePathWithTrailingSeparator + "baseSpell.py";
	String particleScriptPath = basePathWithTrailingSeparator + "particles.py";
	String colorsScriptPath = basePathWithTrailingSeparator + "colors.py";
	String adminScriptPath = basePathWithTrailingSeparator + "admin.py";
	

	public SpellInterpreter() {
		this.interpreter = new PythonInterpreter();
		this.interpreter.getSystemState().path.add(scriptBasePath);
		String[] paths = new String[] { colorsScriptPath, blockScriptPath,
				entitiesScriptPath, itemScriptPath, particleScriptPath,
				baseSpellPath, adminScriptPath };
		for (String path : paths) {
			if (!executePythonFile(path)) {
				PythonProblemHandler
						.printErrorMessageToDialogBox(new Exception(
								"Could not add necessary base script at "
										+ path
										+ " to the Jython interpreter's consciousness."));
			}

		}
	}

	private boolean executePythonFile(String pythonFilePath) {
		synchronized (interpreter) {
			try {
				String textContentOfFile = JavaFileIOHelper.SINGLETON
						.getTextContentOfFile(new File(pythonFilePath));
				interpreter.exec(textContentOfFile);
			} catch (Exception e) {
				PythonProblemHandler.printErrorMessageToDialogBox(e);
				return false;
			}
			return true;
		}
	}

	public boolean interpretSpell(ISpell spell) {
		synchronized (interpreter) {
			try {
				interpreter.exec(spell.getCompiledPythonCode(interpreter));
			} catch (Exception e) {
				PythonProblemHandler.printErrorMessageToDialogBox(e);
				return false;
			}
			return true;
		}
	}

	public boolean interpretPython(String python) {
		synchronized (interpreter) {
			try {
				interpreter.exec(python);
			} catch (Exception e) {
				PythonProblemHandler.printErrorMessageToDialogBox(e);
				return false;
			}
			return true;
		}
	}

	public void setupImpactVariablesInPython(Vec3d positionOfImpact) {
		synchronized (interpreter) {
			if ((positionOfImpact != null)) {
				try {
					this.interpreter.set("impact_x", positionOfImpact.x);
					this.interpreter.set("impact_y", positionOfImpact.y);
					this.interpreter.set("impact_z", positionOfImpact.z);
				} catch (Exception e) {
					PythonProblemHandler.printErrorMessageToDialogBox(e);
				}
			} else {
				JavaProblemHandler
						.printErrorMessageToDialogBox(new Exception(
								"Problem setting impact position variables to provided vector."));
			}
		}
	}

	public void setupRayVariablesInPython(RayTraceResult rayTrace) {
		synchronized (interpreter) {
			if ((rayTrace != null)) {
				try {
					this.interpreter.set("ray_x", rayTrace.hitVec.x);
					this.interpreter.set("ray_y", rayTrace.hitVec.y);
					this.interpreter.set("ray_z", rayTrace.hitVec.z);
				} catch (Exception e) {
					PythonProblemHandler.printErrorMessageToDialogBox(e);
				}
			} else {
				JavaProblemHandler
						.printErrorMessageToDialogBox(new Exception(
								"Problem casting ray to get player's looked-at block."));
			}
		}
	}
	
}