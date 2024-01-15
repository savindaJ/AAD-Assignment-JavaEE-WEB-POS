class Customer {
    #cusId;
    #name;
    #address;
    #salary;

    constructor(cusId, name, address, salary) {
        this.#cusId = cusId;
        this.#name = name;
        this.#address = address;
        this.#salary = salary;
    }

    getCusId() {
        return this.#cusId;
    }

    getName() {
        return this.#name;
    }

    getAddress() {
        return this.#address;
    }

    getSalary() {
        return this.#salary;
    }
}