package com.stschools.api;

import com.stschools.dto.OrderDTO;
import com.stschools.dto.UserDTO;
import com.stschools.entity.Order;
import com.stschools.export_file.orders.OrderCsvExporter;
import com.stschools.export_file.orders.OrderExcelExporter;
import com.stschools.export_file.orders.OrderPdfExporter;

import com.stschools.mapper.UserMapper;
import com.stschools.repository.OrderRepository;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;
    @GetMapping("/list")
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
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO, @CurrentUser UserPrincipal user){
        try{
            UserDTO userDTO = userMapper.findUserById(user.getId());
            orderDTO.setUser(userDTO);
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


    @GetMapping(path = "export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Order> orders = orderRepository.findAll();
        OrderExcelExporter exporter = new OrderExcelExporter();
        exporter.export(orders, response);
    }

    @GetMapping("/export/csv")
    public void exportToCSV( HttpServletResponse response) throws IOException {
        List<Order> orders = orderRepository.findAll();

        OrderCsvExporter exporter = new OrderCsvExporter();
        exporter.export(orders, response);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF( HttpServletResponse response) throws IOException {
        List<Order> orders = orderRepository.findAll();

        OrderPdfExporter exporter = new OrderPdfExporter();
        exporter.export(orders, response);
    }
}
