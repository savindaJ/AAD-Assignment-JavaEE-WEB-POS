function setStatus(){
    $('#totOfCustomers').text(customerDB.length);
    $('#totOfItems').text(itemDB.length);
    $('#totOfOrders').text(orderDB.length);
    $('#finishOrders').text(orderDB.length);



    $('#income').text('55000');
}

setStatus();