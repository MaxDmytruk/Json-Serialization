package modifyObject;

import models.Human;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public interface ModifyObject  {

    String serialize(List<Human> objectsToSerialize);
    List<Human> deserialize();
}
