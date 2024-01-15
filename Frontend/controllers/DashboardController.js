function setStatus(){
    $.ajax({
        method: 'GET',
        url: baseUrl,
        dataType: 'json',
        success: function(res){

            for (const re of res.data) {
                $('#income').text(re.income);
                $('#totOfCustomers').text(re.cusCount);
                $('#totOfItems').text(re.itemCount);
                $('#totOfOrders').text(re.orderCount);
                $('#finishOrders').text(re.orderDetailCount);
            }
        },
        error: function(err){
            let error = JSON.parse(err.responseText);
            console.log(error.message);
        }
    })

}

setStatus();