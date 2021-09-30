package com.stschools.api;

import com.stschools.dto.OrderDTO;
import com.stschools.dto.SectionDTO;
import com.stschools.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/list/")
    public ResponseEntity<?> getListOrder(){
        try {
            List<OrderDTO> orders  = orderService.getAll();
            if(orders == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(orders);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Found",ex);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO){
        try{
            return ResponseEntity.ok().body(orderService.save(orderDTO));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't save", ex);
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(name="id") Long id){
        try {
            Map<String, Boolean> response = new HashMap<>();
            try {
                orderService.deleteById(id);
                response.put("deleted", Boolean.TRUE);

            } catch (Exception exception) {
                response.put("deleted", Boolean.FALSE);
            }
            return response;
        }  catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found", exc);
        }
    }
}
