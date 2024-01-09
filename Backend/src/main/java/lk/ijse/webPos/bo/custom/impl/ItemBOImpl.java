package lk.ijse.webPos.bo.custom.impl;

import lk.ijse.webPos.bo.custom.ItemBO;
import lk.ijse.webPos.config.Configure;
import lk.ijse.webPos.dao.DAOFactory;
import lk.ijse.webPos.dao.custom.ItemDAO;
import lk.ijse.webPos.dto.ItemDTO;
import lk.ijse.webPos.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : savindaJ
 * @date : 1/9/2024
 * @since : 0.1.0
 **/
public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMDAO);
    @Override
    public ArrayList<ItemDTO> getAllItems() {
        itemDAO.setSession(Configure.getInstance().getSession());
        List<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> list = new ArrayList<>();
        for (Item item : all) {
            list.add(new ItemDTO(item.getItemCode(),item.getDescription(),item.getUnitPrice(),item.getQty()));
        }
        return list;
    }
}
