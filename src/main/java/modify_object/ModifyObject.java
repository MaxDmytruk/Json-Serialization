package modify_object;

import models.Human;
import java.util.List;

public interface ModifyObject  {
    String serialize(List<Human> objectsToSerialize);
    List<Human> deserialize();
}