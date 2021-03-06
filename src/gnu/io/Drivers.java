package gnu.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Drivers {

    public String getPath() {
        String osArch = System.getProperty("sun.arch.data.model");
        String path = "";
        if (osArch.equals("32")) {
            path = "C:/JavaRXTX/x86/rxtxSerial.dll";
        } else if (osArch.equals("64")) {
            path = "C:/JavaRXTX/x64/rxtxSerial.dll";
        }

        return path;

    }

    public void buildDriverStructure() {
        createDirectoryStructure();
        createFiles();
    }

    public boolean checkDrivers() {
        boolean Output = false;
        String osArch = System.getProperty("sun.arch.data.model");
        String path = "";
        if (osArch.equals("32")) {
            path = "C:/JavaRXTX/x86/rxtxSerial.dll";
            if (new File(path).exists()) {
                Output = true;
            } else {
                Output = false;
            }
        } else if (osArch.equals("64")) {
            path = "C:/JavaRXTX/x64/rxtxSerial.dll";
            if (new File(path).exists()) {
                Output = true;
            } else {
                Output = false;
            }
        }
        return Output;
    }

    private void createDirectoryStructure() {
        System.out.print("Creando directorios... ");

        if (!new File("C:/JavaRXTX").exists()) {
            new File("C:/JavaRXTX").mkdir();
        }
        if (!new File("C:/JavaRXTX/x64").exists()) {
            new File("C:/JavaRXTX/x64").mkdir();
        }
        if (!new File("C:/JavaRXTX/x86").exists()) {
            new File("C:/JavaRXTX/x86").mkdir();
        }
        System.out.println("OK");
    }

    private void createFiles() {
        System.out.print("Copiando ficheros... ");
        InputStream dllInFile64 = Drivers.class.getResourceAsStream("/rxtxDrivers_x64/rxtxSerial.dll");
        InputStream dllInFile86 = Drivers.class.getResourceAsStream("/rxtxDrivers_x86/rxtxSerial.dll");

        byte[] buf = new byte[2048];
        int r;
        FileOutputStream dllOutFile;
        try {

            if (!new File("C:/JavaRXTX/x64/rxtxSerial.dll").exists()) {
                dllOutFile = new FileOutputStream("C:/JavaRXTX/x64/rxtxSerial.dll");

                r = dllInFile64.read(buf);
                while (r != -1) {
                    dllOutFile.write(buf, 0, r);
                    r = dllInFile64.read(buf);
                }
                dllOutFile.close();
            }
            if (!new File("C:/JavaRXTX/x86/rxtxSerial.dll").exists()) {
                dllOutFile = new FileOutputStream("C:/JavaRXTX/x86/rxtxSerial.dll");

                r = dllInFile86.read(buf);
                while (r != -1) {
                    dllOutFile.write(buf, 0, r);
                    r = dllInFile86.read(buf);
                }
                dllOutFile.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("OK");

    }
}
