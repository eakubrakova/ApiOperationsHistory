package ru.netology.kubrakova.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.kubrakova.DTO.CustomerDTO;
import ru.netology.kubrakova.model.Customer;
import ru.netology.kubrakova.response.CustomersGetResponse;
import ru.netology.kubrakova.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomersGetResponse getCustomers() {
        List<Customer> customers = customerService.getAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable int id) {
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(), customer.getName());
    }

    @PostMapping("/post/{name}/{age}")
    public String addCustomer(@PathVariable String name, @PathVariable int age) {
        long customerId = customerService.countId();
        Customer customer = new Customer(customerId, name, age);
        customerService.add(customer);
        return customer + " added";
    }

    @PutMapping("/put/{id}/{name}")
    public String editCustomer(@PathVariable long id, @PathVariable String name) {
        Customer customer = customerService.getCustomer(id);
        Customer customerEdit = customerService.editCustomer(customerService.getCustomer(id), name);
        return customer + " rename to " + customerEdit;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable long id) {
        return customerService.deleteCustomer(id) + " is deleted";

    }
}
