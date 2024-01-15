let item={
    code:"",
    description:"",
    qtyOnHand:0,
    unitPrice:0
}

let customer = new Customer("C001","Kasun","Galle",25000);
console.log(customer.getAddress());
let json = JSON.stringify(customer);
console.log(json);
