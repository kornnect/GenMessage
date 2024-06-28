/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmessagedigest;

import static com.homehug.text.Main.calcFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author CCS-BMA01
 */
public class GenMessageDigest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   // String[] opts = args.clone();
   String[] opts =new String [20];
   opts[0]="-w";
   opts[1]="C:\\QrGen\\filePdf\\สตน ขอข้อมูล.pdf";

    if (opts.length == 0) {
      System.err.println("usage: hash.jar [-w] <filename>\n");
      System.err.println("-w:\twrite the output to \"SHA256\"");
      System.exit(-1);
    }

    boolean writeFile = false;
    if (opts[0].equals("-w")) {
      File f = new File("C:\\QrGen\\SHA256.txt");
      if (f.exists()) {
        System.err.println("File " + f.getName() + " already exists.");
        System.exit(-1);
      }

      writeFile = true;
      opts = Arrays.copyOfRange(opts, 1, opts.length);
    }

    StringBuffer sb = new StringBuffer();
    Arrays.stream(opts).forEach(opt -> sb.append(calcFile(opt) + "\n"));
    if (writeFile) {
      File f = new File("C:\\QrGen\\SHA256.txt");
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
        bw.write(sb.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.print(sb);
    }

    System.exit(0);
  }
}
