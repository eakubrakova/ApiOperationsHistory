package ru.netology.kubrakova.response;

import org.springframework.stereotype.Component;
import ru.netology.kubrakova.DTO.CustomerDTO;

import java.util.List;
import java.util.Objects;

@Component
public class CustomersGetResponse {

    private final List<CustomerDTO> customers;


    public CustomersGetResponse(List<CustomerDTO> customers) {
        this.customers = customers;
    }


    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "CustomersGetResponse{" +
                "customers=" + customers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersGetResponse that = (CustomersGetResponse) o;
        return Objects.equals(customers, that.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customers);
    }

}