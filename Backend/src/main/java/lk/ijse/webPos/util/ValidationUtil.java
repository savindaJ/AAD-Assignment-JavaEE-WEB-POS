package lk.ijse.webPos.util;

import lk.ijse.webPos.dto.CustomerDTO;
import lk.ijse.webPos.dto.ItemDTO;

import java.util.ArrayList;

/**
 * @author : savindaJ
 * @date : 1/31/2024
 * @since : 0.1.0
 **/
public class ValidationUtil {

    private static final ArrayList<String> customerRegex = new ArrayList<>();
    private static final ArrayList<String> itemRegex = new ArrayList<>();

    static {
        customerRegex.add("^(C00-)[0-9]{3}$");
        customerRegex.add("^[A-Za-z ]{5,}$");
        customerRegex.add("^[A-Za-z0-9 ]{5,}$");
        customerRegex.add("^-?\\d+(\\.\\d+)?$");

        itemRegex.add("^(I00-)[0-9]{3}$");
        itemRegex.add("^[A-Za-z ]{5,}$");
        itemRegex.add("^-?\\d+(\\.\\d+)?$");
        itemRegex.add("^[0-9 ]{2,}$");
    }

    public static boolean validate(Object... dtos) {
        try{
            for (Object dto : dtos) {
                if (dto instanceof CustomerDTO) {
                    System.out.println("Customer");
                    CustomerDTO customerDTO = (CustomerDTO) dto;
                    if (customerDTO.getCusId().matches(customerRegex.get(0))
                            && customerDTO.getCusName().matches(customerRegex.get(1))
                            && customerDTO.getAddress().matches(customerRegex.get(2))
                            && customerDTO.getSalary().toString().matches(customerRegex.get(3))
                    ) {
                        return false;
                    }
                } else if (dto instanceof ItemDTO) {
                    ItemDTO itemDTO = (ItemDTO) dto;
                    if (itemDTO.getItemCode().matches(itemRegex.get(0))
                            && itemDTO.getDescription().matches(itemRegex.get(1))
                            && itemDTO.getPrice().toString().matches(itemRegex.get(2))
                            && itemDTO.getQuantity().toString().matches(itemRegex.get(3))

                    ) {
                        return false;
                    }
                }
            }
        }catch (Exception e){
            return true;
        }
        return true;
    }

    public static boolean validateCusID(String id){
        return !id.matches(customerRegex.get(0));
    }

    public static boolean validateItemID(String id){
        return !id.matches(itemRegex.get(0));
    }

    public static boolean validateOrderID(String id){
        return !id.matches( "^ORD-\\d{3}$");
    }
}
