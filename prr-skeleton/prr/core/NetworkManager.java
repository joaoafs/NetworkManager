package prr.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;
import prr.core.exception.UnavailableFileException;
import prr.core.exception.UnrecognizedEntryException;

//FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Manage access to network and implement load/save operations.
 */
public class NetworkManager {

  private String _filename = "";
  private String _loadFile = new String();

  private Network _network = new Network();
  //FIXME  addmore fields if needed

  
  public Network getNetwork() {
    return _network;
  }


  public void setLoadFile(String loadFile) {
    _loadFile = loadFile;
  }

  public boolean hasFileName() {
    return !"".equals(_filename);
  } 

  public String getFilename() {
    return _filename;
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement serialization method
  }
  
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */


  /* ALTERADO EM 15/10 */

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
    //FIXME implement serialization method
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
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  }  

}