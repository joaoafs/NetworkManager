package prr.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;
import prr.core.exception.UnavailableFileException;
import prr.core.exception.UnrecognizedEntryException;


/**
 * Manage access to network and implement load/save operations.
 */

public class NetworkManager {

  private String _filename = "";
  private String _loadFile = new String();
  private Network _network = new Network();

  /**
   * @return Returns the network
  */

  public Network getNetwork() {
    return _network;
  }

  /**
   * Sets a file name
   * 
   * @param loadFile "filename"
  */

  public void setLoadFile(String loadFile) {
    _loadFile = loadFile;
  }

  public String getLoadFile(){
    return _loadFile;
  }

  /**
   * See if the file already has name.
   * 
   * @return true if the file has name and false if not
  */


  public boolean hasFileName() {
    return !"".equals(_filename);
  } 


  /**
   * Gets the filename.
   * 
   * @return file name
  */

  public String getFilename() {
    return _filename;
  }
  

  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {

    try {
      ObjectInputStream textfile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      Network n = (Network)textfile.readObject();
      textfile.close();
      _network = n;
      _filename = filename;

    } catch (ClassNotFoundException cnfe) {
      System.out.println(cnfe.getMessage()) ;
    } catch (IOException e) {
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */


  public void save(String filename) throws IOException, FileNotFoundException{
    if (!hasFileName())
        _filename = filename;
    try {
      ObjectOutputStream objOut = new ObjectOutputStream(
          new BufferedOutputStream(
              new FileOutputStream(_filename)));
      objOut.writeObject(_network);
      objOut.close();

    } catch (FileNotFoundException fnfe) {
      System.out.println(fnfe.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  

  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save(filename);
  }
  

  /**
   * Read text input file and create domain entities..
   * 
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try {
      _network.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  }  

}
