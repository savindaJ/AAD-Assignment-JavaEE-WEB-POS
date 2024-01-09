package lk.ijse.webPos.bo.custom;

import lk.ijse.webPos.bo.SuperBO;
import lk.ijse.webPos.dto.ItemDTO;

import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/9/2024
 * @since : 0.1.0
 **/
public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems();

    boolean saveItem(ItemDTO itemDTO) throws Exception;

    boolean updateItem(ItemDTO itemDTO) throws Exception;

    boolean deleteItem(String itemCode);
}
